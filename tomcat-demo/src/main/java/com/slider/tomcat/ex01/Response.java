package com.slider.tomcat.ex01;

import java.io.OutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

/*
 HTTP Response = Status-Line
 *(( general-header | response-header | entity-header ) CRLF)
 CRLF
 [ message-body ]
 Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
 */

public class Response {

	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;

	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if (file.exists()) {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			} else {
				// file not found
				String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
						+ "Content-Type: text/html\r\n"
					//	+ "Content-Length: 23\r\n" 
						//+ "Connection:close\r\n"
						+ "\r\n" 
						+ "<h2>this is the test case</h2>"				
						+ "<h1>File Not Found</h1>";
				for(int i =0;i<200;i++){
					errorMessage += "<h2 style='display:none;'>this is the test case</h2>"	;
				}
				output.write(errorMessage.getBytes());
				output.flush();//使用flush 加快页面的显示
				Thread.sleep(3000);
				output.write("<h1>end</h1>".getBytes());
				Thread.sleep(3000);
				output.write("<h1>end....</h1>".getBytes());
			}
		} catch (Exception e) {
			// thrown if cannot instantiate a File object
			System.out.println(e.toString());
		} finally {
			if (fis != null)
				fis.close();
		}
	}
}
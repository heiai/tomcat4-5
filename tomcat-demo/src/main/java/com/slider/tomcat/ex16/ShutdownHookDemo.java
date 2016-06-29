package com.slider.tomcat.ex16;

public class ShutdownHookDemo {
	
	public void start(){
		ShutDownHook shutdownHook = new ShutDownHook();
		Runtime.getRuntime().addShutdownHook(shutdownHook);
		System.out.println("run ...");
	}
	
	public static void main(String args[]){
		ShutdownHookDemo demo = new ShutdownHookDemo();
		demo.start();
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}
	
	class ShutDownHook extends Thread{
		public void run(){
			System.out.println("shutting down");
		}
	}
}

http://archive.apache.org/dist/tomcat 

Catalina:解析%cataline_home%/conf目录下的server.xml文件，cataline类还封装了一个server对象，service对象包含一个servet容器和一个或多个连接器。

HostConfig : 逐个部署并安装指定目录中的所有应用程序。（xml描述文件，war包，文件夹）


ContextConfig:解析%cataline_home%、conf目录和web应用程序自定义的web.xml文件，将xml元素转化为java对象，配置StandardContext实例
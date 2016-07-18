http://archive.apache.org/dist/tomcat 

Catalina:解析%cataline_home%/conf目录下的server.xml文件，cataline类还封装了一个server对象，service对象包含一个servet容器和一个或多个连接器。

HostConfig : 逐个部署并安装指定目录中的所有应用程序。（xml描述文件，war包，文件夹）
依赖于 StandardHostDeployer的install方法解析文件，调用addchild()添加并启动应用程序

ContextConfig:解析%cataline_home%、conf目录和web应用程序自定义的web.xml文件，将xml元素转化为java对象，配置StandardContext实例


引导类载入器(bootstrap class loader),扩展类载入器(extension class loader),系统类载入器(system class loader)



Manager session管理器
StandardManager 标准session管理器
PersistentManagerBase 持久化session管理器;
Store session存储器
FileStore;JdbcStore



loader 类加载器
WebappLoader :StrandardContext的属性在context调用start方法的时候初始化
WebappLoader类的start()方法会创建类加载器（WebappClassLoader）并设置仓库地址WEB-INF/lib,WEB-INF/classes
WebappLoader 会有一个线程去检查所加载的类是否有改变，如果有变化则会调用context的reload方法，reload方法会调用子容器和loader的stop()和start()方法，loader的stop和start方法会销毁当前的类加载器并创建新的类加载器
http://archive.apache.org/dist/tomcat 

Catalina:
解析%cataline_home%/conf目录下的server.xml文件，cataline类还封装了一个server对象，service对象包含一个servet容器和一个或多个连接器。
此时会创建engine,host容器,如果xml文件配置了<content>节点，也会创建content容器
依次调用server的initialize,start,await,stop方法，其中await方法会创建一个serverSocket阻塞stop()方法的执行，等待SHUTDOWN命令。

Server服务器组件和Service服务组件:
Server接口实例表示Catalina的整个servlet引擎，囊括了所有的组件。它使用一种优雅的方法启动/关闭整个系统，不需要再对连接器和容器分别启动。
服务组件是Service接口的实例，一个服务主键可以有一个servlet容器和多个连接器实例.可以自由的把连接器实例添加到服务主键中，所有的连接器都会与这个servlet容器关联。

Engine:唯一一个engine容器包含多个host容器，调用invoke方法时，会根据请求的host首部获取对应的host容器并调用invoke方法。

Host:一个host容器包含多个context容器，创建host容器时会自动添加HostConfig监听器。调用invoke方法时，会根据请求的contextpath获取对应的context容器并调用invoke方法。
HostConfig : 逐个部署并安装指定目录中(<host appBase="webapps">)的所有应用程序(xml描述文件，war包，文件夹),依赖于 StandardHostDeployer的install方法解析文件，调用addchild()添加并启动应用程序

Context:一个context容器包含多个wapper容器,代表一个应用程序,创建Context容器时会自动添加ContextConfig监听器。调用invoke方法时，会根据请求的路径获取对应的wapper容器并调用invoke方法。
ContextConfig:解析%cataline_home%、conf目录和web应用程序自定义的web.xml文件，将xml元素转化为java对象，配置StandardContext实例
每个context容器会添加默认的session管理器和类加载器。

Manager session管理器
StandardManager 标准session管理器
PersistentManagerBase 持久化session管理器;
Store session存储器
FileStore;JdbcStore

loader 类加载器
WebappLoader :StandardContext的属性在context调用start方法的时候初始化
WebappLoader类的start()方法会创建类加载器（WebappClassLoader）并设置仓库地址WEB-INF/lib,WEB-INF/classes
WebappLoader 会有一个线程去检查所加载的类是否有改变，如果有变化则会调用context的reload方法，reload方法会调用子容器和loader的stop()和start()方法，loader的stop和start方法会销毁当前的类加载器并创建新的类加载器

Wrapper:一个Wrapper容器包含一个servlet.

ContainerBase:容器抽象类实现Container, Lifecycle, Pipeline接口，包含children(子容器)，mapper(子容器映射)，pipeline(管道)属性
每一个容器实现类都有一个基础阀门(Value),调用容器的invoke方法时，会调用pipeline的invoke方法，最后执行基础阀门的invoke方法，基础阀门会掉用容器的map方法找到对应的子容器


引导类载入器(bootstrap class loader),扩展类载入器(extension class loader),系统类载入器(system class loader)


构建自己的web应用程序管理整个tomcat中的应用程序(ManagerServlet)
编写一个自己的servlet实现ContainerServlet接口，就可以获取到tomcat中的warpper容器。


BasicAuthenticator UserDatabaseRealm


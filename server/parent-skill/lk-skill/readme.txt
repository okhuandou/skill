4：项目 右键--》run as --》 maven bulid --》弹出对话框 --》在goals中输入mybatis-generator:generate 或者 点击select --》选择你的mybatis插件 --》apply --》run



5：选择项目 按 F5 刷新项目 出现生成的代码

------------------websocket

github demo : https://github.com/nl101531/JavaWEB
spring4 websocket+sockjs 遇到的坑.
最近做的某项目需要有一个聊天功能,技术选型中决定使用websocket
首先tomcat7.0.45,jetty8以上才能完美支持websocket
项目的使用场景会包括一些不支持websocket的浏览器,所以架构中需要兼容
最后选定websocket+sockjs,  使用的spring4的websocket组件
具体配置步骤请参考某大神写的文章 http://wiselyman.iteye.com/blog/2003336
 
下面是我的配置 wsk-applicationContext.xml
	 <websocket:handlers >
		<websocket:mapping path="/echo" handler="websocketEndPoint" />
		<websocket:handshake-interceptors>
			<bean class="cn.com.softvan.websocket.HandshakeInterceptor" />
		</websocket:handshake-interceptors>
	</websocket:handlers>
	<websocket:handlers >
		<websocket:mapping path="/echo" handler="websocketEndPoint" />
		<websocket:handshake-interceptors>
			<bean class="cn.com.softvan.websocket.HandshakeInterceptor" />
		</websocket:handshake-interceptors>
		<websocket:sockjs />
	</websocket:handlers>
  web.xml
<servlet>
        <servlet-name>appServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:config/spring/wsk-*.xml</param-value>
        </init-param>
	    <load-on-startup>1</load-on-startup>
        <async-supported>true</async-supported>
    </servlet>
    <servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/wsk/*</url-pattern>
	</servlet-mapping>
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/sockjs/*</url-pattern>
	</servlet-mapping>
   项目启动后打印日志
INFO [main] - Mapped URL path [/echo] onto handler of type [class org.springframework.web.socket.server.support.WebSocketHttpRequestHandler]
INFO [main] - Mapped URL path [/echo/**] onto handler of type [class org.springframework.web.socket.sockjs.support.SockJsHttpRequestHandler]
 
   使用以上配置
   websocket  访问地址:ws://127.0.0.1/wsk/echo
   sockjs         访问地址:http://127.0.0.1/sockjs/echo/**
  
我这里主要说一下遇到的坑
java.lang.IllegalArgumentException: No 'javax.websocket.server.ServerContainer' ServletContext attribute. Are you running in a Servlet container that supports JSR-356?
 这个异常代表 web容器对JSR-356(WebSocket规范)协议不支持,换个高版本的一般就正常了
已阻止跨源请求：同源策略禁止读取位于 http://127.0.0.1/sockjs/echo/info?t=1434541131338 的远程资源。（原因：CORS 头缺少 'Access-Control-Allow-Origin'）。
 这个异常是跨域引起的,配置中加上allowed-origins="*" 问题解决
将spring中配置改为
 
<websocket:handlers allowed-origins="*" >
		<websocket:mapping path="/echo" handler="websocketEndPoint" />
		<websocket:handshake-interceptors>
			<bean class="cn.com.softvan.websocket.HandshakeInterceptor" />
		</websocket:handshake-interceptors>
	</websocket:handlers>
	<websocket:handlers allowed-origins="*">
		<websocket:mapping path="/echo" handler="websocketEndPoint" />
		<websocket:handshake-interceptors>
			<bean class="cn.com.softvan.websocket.HandshakeInterceptor" />
		</websocket:handshake-interceptors>
		<websocket:sockjs />
	</websocket:handlers>
 
需要强调的是web.xml中的路径匹配问题 写为<url-pattern>/</url-pattern>全部匹配的这种我就不说了
如果使用路径匹配<url-pattern>/wsk/*</url-pattern>
spring中的地址不能写为<websocket:mapping path="/wsk/echo" 只需要写为 <websocket:mapping path="/echo"就可以了,不然无法访问,这个是很多人都会遇到的坑.需特别注意.

------------------websocket end-           ----------------
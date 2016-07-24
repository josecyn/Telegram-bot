package com.jose.test.bot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App
{
    /**
     * port number for the jetty server.
     */
    private static final int PORT = 7070;

    /**
     * Security scheme to use.
     */
    private static final String HTTPS_SCHEME = "https";
    
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main(String[] args) throws Exception
    {    	
    	Server server = new Server(PORT);

    	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloServlet()), "/hello");
    	
/*		Server server = new Server(8080);
		ServletContextHandler handler = new ServletContextHandler(server, "/example");
		handler.addServlet(HelloServlet.class, "/");
		server.start();*/
    	
    	/*Server server = new Server(8080);
 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr/*");
        
        //Integer serverPort = Integer.valueOf(System.getenv("PORT"));
    	//Integer serverPort = Integer.valueOf(PORT);
    	    	
        //Server server = new Server(8080);

        /*SslConnectionFactory sslConnectionFactory = new SslConnectionFactory();
        SslContextFactory sslContextFactory = sslConnectionFactory.getSslContextFactory();
        //sslContextFactory.setKeyStorePath(keyStore);
        //sslContextFactory.setKeyStorePassword(keyStorePassword);
        sslContextFactory.setIncludeCipherSuites(Sdk.SUPPORTED_CIPHER_SUITES);

        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(PORT);
        httpConf.setSecureScheme(HTTPS_SCHEME);
        httpConf.addCustomizer(new SecureRequestCustomizer());
        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConf);

        ServerConnector serverConnector =
                new ServerConnector(server, sslConnectionFactory, httpConnectionFactory);
        serverConnector.setPort(PORT);
        server.setConnectors(new Connector[] { serverConnector
        });*/

        server.start();
        server.join();
        log.info("Server running...");
    }
    
}
package com.jose.test.bot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App
{
    /**
     * port number for the jetty server.
     */
    private static final int PORT = 8888;

    /**
     * Security scheme to use.
     */
    private static final String HTTPS_SCHEME = "https";
    
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main(String[] args) throws Exception
    {
        Integer serverPort = Integer.valueOf(System.getenv("PORT"));
    	//Integer serverPort = Integer.valueOf(PORT);
    	    	
        Server server = new Server(serverPort);

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

        ContextHandler context = new ContextHandler();
        context.setContextPath("/hello");
        context.setResourceBase(".");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(context);
 
        context.setHandler(new HelloHandler());
       
        server.start();
        server.join();
        log.info("Server running...");
    }
    
}
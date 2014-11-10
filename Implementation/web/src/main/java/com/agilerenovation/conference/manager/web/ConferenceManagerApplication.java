package com.agilerenovation.conference.manager.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.jasper.servlet.JspServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.JavaUtilLog;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.webapp.WebAppContext;

public class ConferenceManagerApplication {
   private static final String APPLICATION_CONTEXT = "ConferencePlanner";
   public static final String WEBROOT_INDEX = "/webapp/";
   private URI baseUri;
   private ServerConnector connector;
   protected final static Logger logger = Logger.getLogger( ConferenceManagerApplication.class.getName() );
   private final int portNumber;
   private File scratchDir;
   private Server server;
   private URI serverURI;
   private WebAppContext webAppContext;

   // Constructors
   ConferenceManagerApplication( final int portNumber ) {
      this.portNumber = portNumber;
      configureLogging();
   }

   // Main
   public static void main( String[] args ) {
      ConferenceManagerApplication application = new ConferenceManagerApplication( 8000 );
      try{
         application.start();
         application.waitForInterrupt();
      }catch( Exception e ){
         e.printStackTrace();
      }
   }

   // Public accessors and mutators
   public void start() throws Exception {
      server = new Server();

      // Set connector
      configureServerConnectors();
      configureServerUris();
      configureWebAppContext();
      initializeWebAppContext();
      configureWebAppContextClassLoader();
      configureWebAppContextDefaultServlet();

      // Set JSP to use Standard JavaC always
      System.setProperty( "org.apache.jasper.compiler.disablejsr199", "false" );

      try{
         server.start();
      }catch( Exception e ){
         logger.log( Level.SEVERE, "Starting web application resulted in exception.", e );
      }

      if( logger.isLoggable( Level.FINE ) ){
         logger.fine( server.dump() );
      }

      establishServerUri();
   }

   public void stop() throws Exception {
      server.stop();
   }

   // Properties
   public int getServerPortNumber() {
      return portNumber;
   }

   public URI getServerURI() {
      return serverURI;
   }

   // Protected, private helper methods
   private static void configureLogging() {
      LoggingUtil.config();
      Log.setLog( new JavaUtilLog() );
   }

   private void configureServerConnectors() {
      connector = new ServerConnector( server );
      connector.setPort( portNumber );
      server.addConnector( connector );
   }

   private void configureServerUris() throws FileNotFoundException, URISyntaxException, IOException {
      URL indexUri = this.getClass().getResource( WEBROOT_INDEX );
      if( indexUri == null ){
         throw new FileNotFoundException( "Unable to find resource " + WEBROOT_INDEX );
      }

      baseUri = indexUri.toURI();

      // Establish Scratch directory for the servlet context (used by JSP compilation)
      File tempDir = new File( System.getProperty( "java.io.tmpdir" ) );
      scratchDir = new File( tempDir.toString(), "embedded-jetty-jsp" );

      if( !scratchDir.exists() ){
         if( !scratchDir.mkdirs() ){
            throw new IOException( "Unable to create scratch directory: " + scratchDir );
         }
      }
   }

   private void configureWebAppContext() {
      webAppContext = new WebAppContext();
      webAppContext.setContextPath( "/" + APPLICATION_CONTEXT );
      webAppContext.setResourceBase( baseUri.toASCIIString() );
      webAppContext.setDescriptor( WEBROOT_INDEX + "/WEB-INF/web.xml" );
      webAppContext.setAttribute( InstanceManager.class.getName(), new SimpleInstanceManager() );
      webAppContext.setAttribute( "javax.servlet.context.tempdir", scratchDir );

      HandlerList handlers = new HandlerList();
      ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
      contextHandlerCollection.addHandler( webAppContext );
      handlers.setHandlers( new Handler[] { contextHandlerCollection } );

      server.setHandler( handlers );
   }

   private void configureWebAppContextClassLoader() {
      ClassLoader jspClassLoader = new URLClassLoader( new URL[0], this.getClass().getClassLoader() );
      webAppContext.setClassLoader( jspClassLoader );

      // Add JSP Servlet (must be named "jsp")
      ServletHolder holderJsp = new ServletHolder( "jsp", JspServlet.class );
      holderJsp.setInitOrder( 0 );
      holderJsp.setInitParameter( "logVerbosityLevel", "DEBUG" );
      holderJsp.setInitParameter( "fork", "false" );
      holderJsp.setInitParameter( "xpoweredBy", "false" );
      holderJsp.setInitParameter( "compilerTargetVM", "1.7" );
      holderJsp.setInitParameter( "compilerSourceVM", "1.7" );
      holderJsp.setInitParameter( "keepgenerated", "true" );
      webAppContext.addServlet( holderJsp, "*.jsp" );
   }

   private void configureWebAppContextDefaultServlet() {
      ServletHolder holderDefault = new ServletHolder( "default", DefaultServlet.class );
      logger.info( "Base URI: " + baseUri );
      holderDefault.setInitParameter( "resourceBase", baseUri.toASCIIString() );
      holderDefault.setInitParameter( "dirAllowed", "true" );
      webAppContext.addServlet( holderDefault, "/" );
   }

   private void establishServerUri() throws URISyntaxException {
      String scheme = "http";
      for( ConnectionFactory connectFactory : connector.getConnectionFactories() ){
         if( connectFactory.getProtocol().equals( "SSL-http" ) ){
            scheme = "https";
         }
      }
      String host = connector.getHost();
      if( host == null ){
         host = "localhost";
      }
      int port = connector.getLocalPort();
      serverURI = new URI( String.format( "%s://%s:%d/", scheme, host, port ) );
      logger.info( "Server URI: " + serverURI + "/" + APPLICATION_CONTEXT );
   }

   private void initializeWebAppContext() {
      JettyJasperInitializer sci = new JettyJasperInitializer();
      ServletContainerInitializersStarter sciStarter = new ServletContainerInitializersStarter( webAppContext );
      ContainerInitializer initializer = new ContainerInitializer( sci, null );
      List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
      initializers.add( initializer );

      webAppContext.setAttribute( "org.eclipse.jetty.containerInitializers", initializers );
      webAppContext.addBean( sciStarter, true );
   }

   private void waitForInterrupt() throws InterruptedException {
      server.join();
   }
}

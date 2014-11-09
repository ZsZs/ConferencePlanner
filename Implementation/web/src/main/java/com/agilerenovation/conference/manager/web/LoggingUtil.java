package com.agilerenovation.conference.manager.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.LogManager;

public class LoggingUtil {
   private static final String LOGGING_PROPERTIES = "logging.properties";

   public static void config() {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      URL url = cl.getResource( LOGGING_PROPERTIES );
      if( url != null ){
         try( InputStream in = url.openStream() ){
            LogManager.getLogManager().readConfiguration( in );
         }catch( IOException e ){
            e.printStackTrace( System.err );
         }
      }
   }
}

package com.agilerenovation.conference.resource.integration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public abstract class Resource {
   public static final String FILE_PREFIX = "file:";
   public static final String CLASSPATH_PREFIX = "classpath:";
   protected final String resourceSpecifier;
   protected InputStream inputStream;

   // Constructors
   public Resource( final String resourceSpecifier ) {
      this.resourceSpecifier = resourceSpecifier;
   }

   // Factory methods
   public static Resource create( final String resourceSpecifier ) {
      Resource resource = null;
      if( resourceSpecifier.startsWith( CLASSPATH_PREFIX ) ){
         resource = new ClasspathResource( resourceSpecifier.substring( 10 ) );
      }else if( resourceSpecifier.startsWith( FILE_PREFIX ) ){
         resource = new FileSystemResource( resourceSpecifier.substring( 5 ));
      }

      return resource;
   }

   // Public accessors and mutators
   public Document getDomDocument(){
      if( inputStream == null ){
         inputStream = getInputStream();
      }
      
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setNamespaceAware( true );
      Document document = null;
      try{
         DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
         document = documentBuilder.parse( inputStream );
      }catch( ParserConfigurationException | SAXException | IOException e ){
         e.printStackTrace();
      }
      return document;
   }

   public abstract File getFile();

   public abstract InputStream getInputStream();
   
   // protected, private helper methods
}

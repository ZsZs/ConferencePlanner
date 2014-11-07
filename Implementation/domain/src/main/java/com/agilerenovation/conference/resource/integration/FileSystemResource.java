package com.agilerenovation.conference.resource.integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemResource extends Resource {

   public FileSystemResource( String resourceSpecifier ) {
      super( resourceSpecifier );
   }

   @Override
   public File getFile() {
      String absolutePath = determineAbsolutePath();
      return new File( absolutePath );
   }

   @Override
   public InputStream getInputStream() {
      try{
         inputStream = new FileInputStream( getFile() );
      }catch( FileNotFoundException e ){
         e.printStackTrace();
      }
      return inputStream;
   }

   //protected, private helper methods
   private String determineAbsolutePath(){
      String absolutePath = null;
      Path parentDirectory = Paths.get( determineCurrentDirectory() );
      String resourceSpecifierFragment = resourceSpecifier;
      while( resourceSpecifierFragment.startsWith( "../" )){
         parentDirectory = parentDirectory.getParent();
         resourceSpecifierFragment = resourceSpecifierFragment.substring( 3 );
      }
      
      absolutePath = parentDirectory.toString() + "/" + resourceSpecifierFragment;
      return absolutePath;
   }
   
   private String determineCurrentDirectory(){
      return System.getProperty("user.dir");
   }
}

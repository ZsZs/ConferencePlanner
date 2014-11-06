package com.agilerenovation.conference.resource.integration;

import java.io.File;
import java.io.InputStream;

public class ClasspathResource extends Resource {
   
   public ClasspathResource( String resourceSpecifier ) {
      super( resourceSpecifier );
   }

   // public helper and mutator methods
   @Override
   public File getFile() {
      return null;
   }

   @Override
   public InputStream getInputStream() {
      inputStream = ClasspathResource.class.getResourceAsStream( resourceSpecifier );
      return inputStream;
   }
}

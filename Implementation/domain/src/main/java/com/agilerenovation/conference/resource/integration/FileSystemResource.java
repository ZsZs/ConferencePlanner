package com.agilerenovation.conference.resource.integration;

import java.io.File;
import java.io.InputStream;

public class FileSystemResource extends Resource {

   public FileSystemResource( String resourceSpecifier ) {
      super( resourceSpecifier );
   }

   @Override
   public File getFile() {
      return null;
   }

   @Override
   public InputStream getInputStream() {
      return null;
   }

}

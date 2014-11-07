package com.agilerenovation.conference.resource.integration;

import java.io.InputStream;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ResourceTest {
   private static final String CLASSPATH_RESOURCE = "classpath:ConferenceSubmissions.xml";
   private static final String FILE_SYSTEM_RESOURCE = "file:../integration-tests/src/test/resources/ConferenceSubmissions.xml";

   @Test public void create_whenClasspathSpecified_instantiatesClasspathResource(){
      assertThat( Resource.create( CLASSPATH_RESOURCE ), instanceOf( ClasspathResource.class ));
   }
   
   @Test public void create_whenFileSpecified_instantiatesFileResource(){
      assertThat( Resource.create( FILE_SYSTEM_RESOURCE ), instanceOf( FileSystemResource.class ));
   }
   
   @Test public void getInputStream_whenFileSpecified_handlesRelativePath(){
      Resource resource = Resource.create( FILE_SYSTEM_RESOURCE );
      
      assertThat( resource.getInputStream(), instanceOf( InputStream.class ));
   }
}

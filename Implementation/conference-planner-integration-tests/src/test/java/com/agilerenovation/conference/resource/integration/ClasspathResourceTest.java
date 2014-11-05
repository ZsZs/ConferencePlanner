package com.agilerenovation.conference.resource.integration;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import org.junit.Test;

public class ClasspathResourceTest {

   @Test public void getFile_instantiatesFileObject(){
      Resource fileResource = Resource.create( "classpath:/ConferenceSubmissions.xml" );
      
      assertThat( fileResource.getInputStream(), instanceOf( InputStream.class ));
   }
}

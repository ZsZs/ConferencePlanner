package com.agilerenovation.conference.resource.integration;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ResourceTest {
   @Test public void create_whenClasspathSpecified_instantiatesClasspathResource(){
      assertThat( Resource.create( "classpath:ConferenceSubmission.xml" ), instanceOf( ClasspathResource.class ));
   }
   
   @Test public void create_whenFileSpecified_instantiatesFileResource(){
      assertThat( Resource.create( "file:ConferenceSubmission.xml" ), instanceOf( FileSystemResource.class ));
   }
}

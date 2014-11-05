package com.agilerenovation.conference.resource.integration;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.w3c.dom.Document;

public class ResourceTest {
   @Test public void getDomDocument_parsesXml(){
      Resource fileResource = Resource.create( "classpath:/ConferenceSubmissions.xml" );
      
      assertThat( fileResource.getDomDocument(), instanceOf( Document.class ));
   }
}

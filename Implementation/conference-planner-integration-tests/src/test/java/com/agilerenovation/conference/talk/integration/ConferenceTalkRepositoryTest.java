package com.agilerenovation.conference.talk.integration;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.agilerenovation.conference.resource.integration.Resource;

public class ConferenceTalkRepositoryTest {
   private ConferenceTalkRepository repository;

   @Before public void beforeEachTests(){
      repository = new ConferenceTalkRepository();
   }
   
   @Test public void importTalks_ReadsXmlFile() throws MalformedURLException{
      //EXECUTION:
      repository.importTalks( "file:///ConferenceSubmissions.xml" );
      
      //VERIFY:
      assertThat( repository.getImportResource(), instanceOf( Resource.class ));
   }
}

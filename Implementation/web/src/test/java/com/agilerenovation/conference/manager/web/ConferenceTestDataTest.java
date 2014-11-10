package com.agilerenovation.conference.manager.web;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.agilerenovation.conference.event.integration.ConferenceEventRepository;

public class ConferenceTestDataTest {
   @Test public void setUp_instantiatesEvents(){
      ConferenceTestData testData = ConferenceTestData.getInstance();
      
      testData.setUp();
      
      ConferenceEventRepository repository = ConferenceEventRepository.getInstance();
      assertThat( repository.findConferences().size(), greaterThan( 0 ));
   }
}

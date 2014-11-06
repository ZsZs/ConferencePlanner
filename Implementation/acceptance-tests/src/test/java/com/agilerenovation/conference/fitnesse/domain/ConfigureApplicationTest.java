package com.agilerenovation.conference.fitnesse.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeRepository;

public class ConfigureApplicationTest {
   private ConfigureApplication configureApplication;

   @Before public void beforeEachTests(){
      configureApplication = new ConfigureApplication();
   }
   
   @Test public void defineConferenceEventTypes(){
      configureApplication.configure();
      
      ConferenceEventTypeRepository eventTypeRepository = ConferenceEventTypeRepository.getInstance();
      
      assertThat( eventTypeRepository.findAll().size(), greaterThan( 1 ));
   }
}

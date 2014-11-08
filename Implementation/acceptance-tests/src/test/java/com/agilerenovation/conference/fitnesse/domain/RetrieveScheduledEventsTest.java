package com.agilerenovation.conference.fitnesse.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.agilerenovation.conference.fitnesse.sharedfixtures.ConferenceConfiguration;

public class RetrieveScheduledEventsTest {
   private ConferenceConfiguration conferenceConfiguration;
   private static RetrieveScheduledEvents retrieveScheduledEvents;
   
   @Before public void beforeEachTests(){
      conferenceConfiguration = new ConferenceConfiguration();
      conferenceConfiguration.setUp();
      
      retrieveScheduledEvents = new RetrieveScheduledEvents( ConferenceConfiguration.TRACK_NAME );
   }
   
   @Test public void query_retrievesNestedEvents(){
      List<Object> rows = retrieveScheduledEvents.query();
      
      assertThat( rows.size(), greaterThan( 0 ));
   }
}

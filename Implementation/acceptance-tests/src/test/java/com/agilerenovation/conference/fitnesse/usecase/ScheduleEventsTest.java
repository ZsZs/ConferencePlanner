package com.agilerenovation.conference.fitnesse.usecase;

import org.junit.Before;
import org.junit.Test;

import com.agilerenovation.conference.fitnesse.sharedfixtures.ConferenceConfiguration;

public class ScheduleEventsTest {
   private ConferenceConfiguration conferenceConfiguration;
   private static ScheduleEvents scheduleEvents;
   
   @Before public void beforeEachTests(){
      conferenceConfiguration = new ConferenceConfiguration();
      conferenceConfiguration.setUp();
      
      scheduleEvents = new ScheduleEvents();
   }

   @Test public void scheduleSubmittedPapers_placesTalksIntoSessions(){
      scheduleEvents.scheduleSubmittedPapers();
   }
}

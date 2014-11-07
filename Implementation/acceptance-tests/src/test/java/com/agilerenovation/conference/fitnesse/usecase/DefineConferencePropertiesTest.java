package com.agilerenovation.conference.fitnesse.usecase;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.agilerenovation.conference.event.domain.ConferenceSession;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;

public class DefineConferencePropertiesTest {
   private DefineConferenceProperties defineConferenceProperties;
   private ConferenceEventRepository eventRepository;

   @Before public void beforeEachTests(){
      defineConferenceProperties = new DefineConferenceProperties(); 
      eventRepository = ConferenceEventRepository.getInstance();
   }
   
   @After public void afterEachTests(){
      eventRepository.deleteAll();
   }
   
   @Test public void defineConferenceFromTo_storesInEventRepository(){
      defineConferenceProperties.defineConferenceFromTo( "Conference Name", "2014-12-01", "2014-12-01" );
      
      assertThat( eventRepository.findAll().size(), greaterThan( 0 ));
   }
   
   @Test public void defineDayFor_determinesParent(){
      defineConferenceProperties.defineConferenceFromTo( "Conference Name", "2014-12-01", "2014-12-01" );
      
      defineConferenceProperties.defineDayFor( "first day", "Conference Name" );
   }
   
   @Test public void defineSessionForStartingAtEndingAt_calculatesTimes(){
      //SETUP:
      defineConferenceProperties.defineConferenceFromTo( "Conference Name", "2014-12-01", "2014-12-01" );
      defineConferenceProperties.defineDayFor( "first day", "Conference Name" );
      defineConferenceProperties.defineTrackFor( "track-1", "first day" );

      //EXECUTION:
      defineConferenceProperties.defineSessionForStartingAtEndingAt( "session-1", "track-1", "9", "12" );
      
      //VERIFY:
      ConferenceTrack track = eventRepository.findByName( "track-1", ConferenceTrack.class );
      ConferenceSession session = eventRepository.findByName( "session-1", ConferenceSession.class );
      assertThat( session.getPlannedStart(), equalTo( track.getPlannedStart().plusHours( 9 )));
      assertThat( session.getPlannedEnd(), equalTo( track.getPlannedStart().plusHours( 12 )));
   }
}

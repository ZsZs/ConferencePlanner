package com.agilerenovation.conference.fitnesse.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.agilerenovation.conference.event.domain.Conference;
import com.agilerenovation.conference.event.domain.ConferenceCatering;
import com.agilerenovation.conference.event.domain.ConferenceDay;
import com.agilerenovation.conference.event.domain.ConferenceSession;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.integration.ConferenceEventFactory;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.agilerenovation.conference.manager.domain.ConferenceManager;

public class RetrieveScheduledEventsTest {
   private static final String CONFERENCE_END = "2012-12-01";
   private static final String CONFERENCE_START = "2014-12-01";
   public static final String TRACK_NAME = "Track-1";
   private static RetrieveScheduledEvents retrieveScheduledEvents;
   
   @Before public void beforeEachTests(){
      ConferenceManager conferenceManager = ConferenceManager.getInstance();
      conferenceManager.setUpEventTypes();
      conferenceManager.setUpPaperTypes();
      
      ConferenceEventFactory eventFactory = new ConferenceEventFactory();
      ConferenceEventRepository eventRepository = ConferenceEventRepository.getInstance();
      
      Conference conference = eventFactory.createConference( "a conference", new DateTime( CONFERENCE_START ), new DateTime( CONFERENCE_END ));
      eventRepository.add( conference );
      
      ConferenceDay day = eventFactory.createDay( "first day", conference );
      eventRepository.add( day );
      
      ConferenceTrack track = eventFactory.createTrack( TRACK_NAME, day );
      eventRepository.add( track );
      
      ConferenceSession session = eventFactory.createSession( "morning session", track, 9, 12 );
      eventRepository.add( session );
      
      ConferenceCatering catering = eventFactory.createCatering( "lunch", day, 12, 13 );
      eventRepository.add( catering );
            
      retrieveScheduledEvents = new RetrieveScheduledEvents( TRACK_NAME );
   }
   
   @Test public void query_retrievesNestedEvents(){
      List<Object> rows = retrieveScheduledEvents.query();
      
      assertThat( rows.size(), greaterThan( 0 ));
   }
}

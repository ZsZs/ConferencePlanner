package com.agilerenovation.conference.fitnesse.sharedfixtures;

import org.joda.time.DateTime;

import com.agilerenovation.conference.event.domain.CompositeConferenceEvent;
import com.agilerenovation.conference.event.domain.Conference;
import com.agilerenovation.conference.event.domain.ConferenceCatering;
import com.agilerenovation.conference.event.domain.ConferenceDay;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.integration.ConferenceEventFactory;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.agilerenovation.conference.manager.domain.ConferenceManager;

public class ConferenceConfiguration {
   public static final String CONFERENCE_END = "2012-12-01";
   public static final String CONFERENCE_START = "2014-12-01";
   public static final String TRACK_NAME = "Track-1";
   
   public void setUp(){
      ConferenceManager conferenceManager = ConferenceManager.getInstance();
      conferenceManager.setUpEventTypes();
      conferenceManager.setUpPaperTypes();
      conferenceManager.importTalks( "file:../integration-tests/src/test/resources/ConferenceSubmissions.xml" );
      
      ConferenceEventFactory eventFactory = new ConferenceEventFactory();
      ConferenceEventRepository eventRepository = ConferenceEventRepository.getInstance();
      
      Conference conference = eventFactory.createConference( "a conference", new DateTime( CONFERENCE_START ), new DateTime( CONFERENCE_END ));
      eventRepository.add( conference );
      
      ConferenceDay day = eventFactory.createDay( "first day", conference );
      eventRepository.add( day );
      
      ConferenceTrack track = eventFactory.createTrack( TRACK_NAME, day );
      eventRepository.add( track );
      
      CompositeConferenceEvent session = eventFactory.createSession( "morning session", track, 9, 12 );
      eventRepository.add( session );
      
      ConferenceCatering catering = eventFactory.createCatering( "lunch", day, 12, 13 );
      eventRepository.add( catering );
      
   }
}

package com.agilerenovation.conference.event.integration;

import org.joda.time.DateTime;

import com.agilerenovation.conference.event.domain.CompositeConferenceEvent;
import com.agilerenovation.conference.event.domain.Conference;
import com.agilerenovation.conference.event.domain.ConferenceCatering;
import com.agilerenovation.conference.event.domain.ConferenceDay;
import com.agilerenovation.conference.event.domain.ConferenceSession;
import com.agilerenovation.conference.event.domain.ConferenceTalk;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.domain.NetworkEvent;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeRepository;
import com.agilerenovation.conference.paper.domain.ConferencePaper;

public class ConferenceEventFactory {
   private ConferenceEventRepository eventRepository;
   private ConferenceEventTypeRepository eventTypeRepository;

   public ConferenceEventFactory(){
      eventRepository = ConferenceEventRepository.getInstance();
      eventTypeRepository = ConferenceEventTypeRepository.getInstance();
   }

   public ConferenceCatering createCatering( String cateringName, String dayName, int startsAt, int endsAt ) {
      ConferenceDay conferenceDay = eventRepository.findByName( dayName, ConferenceDay.class );
      return createCatering( cateringName, conferenceDay, startsAt, endsAt );
   }
   
   public ConferenceCatering createCatering( String cateringName, ConferenceDay conferenceDay, int startsAt, int endsAt ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.CATERING );
      DateTime plannedStart = conferenceDay.getPlannedStart().plusHours( startsAt );
      DateTime plannedEnd = conferenceDay.getPlannedStart().plusHours( endsAt );
      return new ConferenceCatering( eventType, conferenceDay, cateringName, plannedStart, plannedEnd );
   }

   public Conference createConference( String conferenceName, DateTime plannedStart, DateTime plannedEnd ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.CONFERENCE );
      return new Conference( eventType, conferenceName, plannedStart, plannedEnd );
   }

   public ConferenceDay createDay( String dayName, String conferenceName ) {
      Conference conference = eventRepository.findByName( conferenceName, Conference.class );
      return createDay( dayName, conference );
   }

   public ConferenceDay createDay( String dayName, Conference conference ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.CONFERENCE_DAY );
      return new ConferenceDay( eventType, conference, dayName, conference.getPlannedStart(), conference.getPlannedEnd() );
   }

   public NetworkEvent createNetworkEvent( String networkEventName, String dayName, int startsAt, int endsAt ) {
      ConferenceDay conferenceDay = eventRepository.findByName( dayName, ConferenceDay.class );
      return createNetworkEvent( networkEventName, conferenceDay, startsAt, endsAt );
   }
   
   public NetworkEvent createNetworkEvent( String networkEventName, ConferenceDay conferenceDay, int startsAt, int endsAt ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.NETWORK_EVENT );
      DateTime plannedStart = conferenceDay.getPlannedStart().plusHours( startsAt );
      DateTime plannedEnd = conferenceDay.getPlannedStart().plusHours( endsAt );
      return new NetworkEvent( eventType, conferenceDay, networkEventName, plannedStart, plannedEnd );
   }

   public CompositeConferenceEvent createSession( String sessionName, String trackName, Integer startsAt, Integer endsAt ) {
      ConferenceTrack track = eventRepository.findByName( trackName, ConferenceTrack.class );
      return createSession( sessionName, track, startsAt, endsAt );
   }
   
   public CompositeConferenceEvent createSession( String sessionName, ConferenceTrack track, Integer startsAt, Integer endsAt ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.SESSION );
      DateTime plannedStart = track.getPlannedStart().plusHours( startsAt );
      DateTime plannedEnd = track.getPlannedStart().plusHours( endsAt );
      return new ConferenceSession( eventType, track, sessionName, plannedStart, plannedEnd );
   }

   public ConferenceTalk createTalk( CompositeConferenceEvent session, ConferencePaper conferencePaper ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.TALK );
      DateTime plannedStart = session.getScheduledEnd();
      DateTime plannedEnd = session.getScheduledEnd().plusMinutes( conferencePaper.getLength() );
      return new ConferenceTalk( eventType, session, conferencePaper.getTitle(), plannedStart, plannedEnd );
   }

   public ConferenceTrack createTrack( String trackName, String dayName ) {
      ConferenceDay conferenceDay = eventRepository.findByName( dayName, ConferenceDay.class );
      return createTrack( trackName, conferenceDay );
   }

   public ConferenceTrack createTrack( String trackName, ConferenceDay conferenceDay ) {
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.TRACK );
      return new ConferenceTrack( eventType, conferenceDay, trackName, conferenceDay.getPlannedStart(), conferenceDay.getPlannedEnd() );
   }
}

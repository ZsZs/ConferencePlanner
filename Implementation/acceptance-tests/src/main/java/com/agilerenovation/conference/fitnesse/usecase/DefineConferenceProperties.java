package com.agilerenovation.conference.fitnesse.usecase;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.agilerenovation.conference.event.domain.Conference;
import com.agilerenovation.conference.event.domain.ConferenceCatering;
import com.agilerenovation.conference.event.domain.ConferenceDay;
import com.agilerenovation.conference.event.domain.ConferenceSession;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.domain.NetworkEvent;
import com.agilerenovation.conference.event.integration.ConferenceEventFactory;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;

public class DefineConferenceProperties {
   private ConferenceEventFactory eventFactory;
   private ConferenceEventRepository eventRepository;

   public DefineConferenceProperties(){
      eventFactory = new ConferenceEventFactory();
      eventRepository = ConferenceEventRepository.getInstance();
   }
   
   public void defineCateringForStartingAtEndingAt( final String cateringName, final String dayName, final String startsAt, final String endsAt ){
      ConferenceCatering catering = eventFactory.createCatering( cateringName, dayName, Integer.parseInt( startsAt ), Integer.parseInt( endsAt ));
      eventRepository.add( catering );
   }
   
   public void defineConferenceFromTo( final String conferenceName, final String startDate, final String startEnd ){
      DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern( "yyyy-MM-dd" );
      DateTime plannedStart = dateTimeFormatter.parseDateTime( startDate );
      DateTime plannedEnd = dateTimeFormatter.parseDateTime( startEnd );
      Conference conference = eventFactory.createConference( conferenceName, plannedStart, plannedEnd );
      eventRepository.add( conference );
   }
   
   public void defineDayFor( final String dayName, final String conferenceName ){
      ConferenceDay day = eventFactory.createDay( dayName, conferenceName );
      eventRepository.add( day );
   }
   
   public void defineNetworkEventForStartingAtEndingAt( final String networkEventName, final String dayName, final String startsAt, final String endsAt ){
      NetworkEvent networkEvent = eventFactory.createNetworkEvent( networkEventName, dayName, Integer.parseInt( startsAt ), Integer.parseInt( endsAt ));
      eventRepository.add( networkEvent );
   }
   
   public void defineSessionForStartingAtEndingAt( final String sessionName, final String trackName, final String startsAt, final String endsAt ){
      ConferenceSession session = eventFactory.createSession( sessionName, trackName, Integer.parseInt( startsAt ), Integer.parseInt( endsAt ));
      eventRepository.add( session );
   }
   
   public void defineTrackFor( final String trackName, final String dayName ){
      ConferenceTrack track = eventFactory.createTrack( trackName, dayName );
      eventRepository.add( track );
   }
}

package com.agilerenovation.conference.event.integration;

import java.util.List;
import java.util.Set;

import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ConferenceEventRepository {
   private static Set<ConferenceEvent> events = Sets.newHashSet();
   private static ConferenceEventRepository soleInstance;
   
   //Constructors and factory methods
   private ConferenceEventRepository(){}
   
   public static ConferenceEventRepository getInstance(){
      if( soleInstance == null ){
         soleInstance = new ConferenceEventRepository();
      }
      
      return soleInstance;
   }
   
   //Public accessors and mutators
   public void add( final ConferenceEvent conferenceEvent ){
      events.add( conferenceEvent );
   }

   public void deleteAll() {
      events.clear();
   }
   
   public Set<ConferenceEvent> findAll(){
      return ImmutableSet.copyOf( events );
   }

   @SuppressWarnings( "unchecked" )
   public <T extends ConferenceEvent> T findByName( String eventName, Class<T> type ) {
      T foundEvent = null;
      for( ConferenceEvent conferenceEvent : events ){
         if( conferenceEvent.getName().equals( eventName )){
            foundEvent = (T) conferenceEvent;
            break;
         }
      }
      return foundEvent;
   }

   public List<ConferenceEvent> findByType( ConferenceEventTypes typeCode ) {
      List<ConferenceEvent> foundEvents = Lists.newArrayList();
      for( ConferenceEvent conferenceEvent : events ){
         if( conferenceEvent.getType().getName().equals( typeCode.getName() )){
            foundEvents.add( conferenceEvent );
         }
      }
      return foundEvents;
   }

   public List<ConferenceEvent> findCaterings() {
      return findByType( ConferenceEventTypes.CATERING );
   }

   public List<ConferenceEvent> findConferences() {
      return findByType( ConferenceEventTypes.CONFERENCE );
   }

   public List<ConferenceEvent> findDays() {
      return findByType( ConferenceEventTypes.CONFERENCE_DAY );
   }

   public List<ConferenceEvent> findNetworkEvents() {
      return findByType( ConferenceEventTypes.NETWORK_EVENT );
   }

   public List<ConferenceEvent> findSessions() {
      return findByType( ConferenceEventTypes.SESSION );
   }

   public List<ConferenceEvent> findTalks() {
      return findByType( ConferenceEventTypes.TALK );
   }

   public List<ConferenceEvent> findTracks() {
      return findByType( ConferenceEventTypes.TRACK );
   }
}

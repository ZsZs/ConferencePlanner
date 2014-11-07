package com.agilerenovation.conference.event.integration;

import java.util.Set;

import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.google.common.collect.ImmutableSet;
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
}

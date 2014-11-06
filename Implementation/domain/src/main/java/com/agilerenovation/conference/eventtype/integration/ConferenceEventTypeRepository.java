package com.agilerenovation.conference.eventtype.integration;

import java.util.Set;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class ConferenceEventTypeRepository{
   private static ConferenceEventTypeRepository soleInstance;
   private static Set<ConferenceEventType> eventTypes = Sets.newHashSet();
   
   //Constructors and factory methods
   private ConferenceEventTypeRepository(){}
   
   public static ConferenceEventTypeRepository getInstance(){
      if( soleInstance == null ){
         soleInstance = new ConferenceEventTypeRepository();
      }
      
      return soleInstance;
   }
   
   //Public accessors and mutators
   public void add( final ConferenceEventType eventType ){
      eventTypes.add( eventType );
   }
   
   public Set<ConferenceEventType> findAll(){
      return ImmutableSet.copyOf( eventTypes );
   }
}

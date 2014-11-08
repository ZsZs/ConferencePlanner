package com.agilerenovation.conference.event.domain;

import java.util.List;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public abstract class CompositeConferenceEvent extends ConferenceEvent {
   private List<ConferenceEvent> events = Lists.newArrayList();
   
   //Constructors
   public CompositeConferenceEvent( ConferenceEventType type, CompositeConferenceEvent parent, String name, DateTime plannedStart, DateTime plannedEnd ) {
      super( type, parent, name, plannedStart, plannedEnd );
   }

   //Public accessors and mutators
   public void addEvent( final ConferenceEvent event ){
      events.add( event );
   }
   
   //Properties
   public List<ConferenceEvent> getAllNestedEvents(){
      List<ConferenceEvent> allEvents = Lists.newArrayList();
      for( ConferenceEvent conferenceEvent : events ){
         allEvents.add( conferenceEvent );
         if( conferenceEvent instanceof CompositeConferenceEvent ){
            allEvents.addAll( ((CompositeConferenceEvent) conferenceEvent).getAllNestedEvents() );
         }
      }
      return allEvents;
   }

   public List<ConferenceEvent> getEvents(){
      return ImmutableList.copyOf( events );
   }

   @SuppressWarnings( "unchecked" )
   public <E extends ConferenceEvent> List<E> getEventsByType( ConferenceEventTypes eventType, Class<E> eventClass  ) {
      List<E> filteredEvents = Lists.newArrayList();
      for( ConferenceEvent conferenceEvent : getAllNestedEvents() ){
         if( conferenceEvent.getType().getName().equals( eventType.getName() )){
            filteredEvents.add( (E) conferenceEvent );
         }
      }
      return filteredEvents;
   }

   //Protected, private helper methods
   protected void calculateScheduledEnd() {
      for( ConferenceEvent conferenceEvent : events ){
         conferenceEvent.calculateScheduledEnd();
         if( conferenceEvent.getScheduledEnd().compareTo( scheduledEnd ) > 0 ){
            scheduledEnd = conferenceEvent.getScheduledEnd();
         }
      }
   }
}

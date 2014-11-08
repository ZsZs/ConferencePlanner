package com.agilerenovation.conference.fitnesse.domain;

import static util.ListUtility.list;

import java.util.Collections;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.agilerenovation.conference.event.domain.ConferenceDay;
import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class RetrieveScheduledEvents {
   private String trackName;
   
   public RetrieveScheduledEvents( final String trackName ){
      this.trackName = trackName;
   }
   
   public void table( List<List<String>> table ) {
      // optional function
   }
   
   @SuppressWarnings( "unchecked" )
   public List<Object> query() {
      List<Object> resultList = Lists.newArrayList();
      ConferenceEventRepository eventRepository = ConferenceEventRepository.getInstance();
      ConferenceTrack track = eventRepository.findByName( trackName, ConferenceTrack.class );
      ConferenceDay day = eventRepository.findByName( track.getParent().getName(), ConferenceDay.class );
      
      List<ConferenceEvent> events = track.getAllNestedEvents();
      events.addAll( day.getCateringEvents() );
      events.addAll( day.getNetworkEvents() );
      
      OrderingByStartTime orderingByStartTime = new OrderingByStartTime();
      Collections.sort( events, orderingByStartTime );
      
      DateTimeFormatter timeFormatter = DateTimeFormat.forPattern( "HH:mm" );
            
      for( ConferenceEvent conferenceEvent : events ){
         List<String> begin = list( "begins at", timeFormatter.print( conferenceEvent.getPlannedStart() ));
         List<String> title = list( "title", conferenceEvent.getName());
         
         resultList.add( list( begin, title ));
      }
      
      return resultList;
   }
   
   private class OrderingByStartTime extends Ordering<ConferenceEvent> {
      @Override
      public int compare( ConferenceEvent arg0, ConferenceEvent arg1 ) {
         return arg0.getScheduledStart().compareTo( arg1.getScheduledStart() );
      }
  }
}

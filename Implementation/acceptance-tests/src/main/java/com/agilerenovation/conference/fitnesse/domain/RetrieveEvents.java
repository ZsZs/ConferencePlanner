package com.agilerenovation.conference.fitnesse.domain;

import static util.ListUtility.list;

import java.util.List;
import java.util.Set;

import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.google.common.collect.Lists;

public class RetrieveEvents {

   public void table( List<List<String>> table ) {
      // optional function
   }
   
   @SuppressWarnings( "unchecked" )
   public List<Object> query() {
      List<Object> resultList = Lists.newArrayList();
      Set<ConferenceEvent> events = ConferenceEventRepository.getInstance().findAll();
      
      for( ConferenceEvent conferenceEvent : events ){
         List<String> name = list( "name", conferenceEvent.getName());
         List<String> type = list( "type", conferenceEvent.getType().getName());
         List<String> parent = (conferenceEvent.getParent() != null) ? list( "parent", conferenceEvent.getParent().getName()) : list( "parent", "none" );
         List<String> plannedStart = list( "planned start", conferenceEvent.getEarliestStart().toString() );
         List<String> plannedEnd = list( "planned end", conferenceEvent.getEarliestEnd().toString() );
         
         resultList.add( list( name, type, parent, plannedStart, plannedEnd ));
      }
      return resultList;
   }
}

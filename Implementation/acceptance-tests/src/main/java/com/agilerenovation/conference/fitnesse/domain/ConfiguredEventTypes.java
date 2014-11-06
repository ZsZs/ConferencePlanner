package com.agilerenovation.conference.fitnesse.domain;

import static util.ListUtility.list;

import java.util.List;
import java.util.Set;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeRepository;
import com.google.common.collect.Lists;

public class ConfiguredEventTypes {

   public void table( List<List<String>> table ) {
      // optional function
   }

   @SuppressWarnings( "unchecked" )
   public List<Object> query() {
      List<Object> resultList = Lists.newArrayList();
      Set<ConferenceEventType> eventTypes = ConferenceEventTypeRepository.getInstance().findAll();
      
      for( ConferenceEventType conferenceEventType : eventTypes ){
         List<String> nameValue = list( "name", conferenceEventType.getName());
         List<String> descriptionValue = list( "description", conferenceEventType.getDescription());
         
         resultList.add( list( nameValue, descriptionValue ));
      }
      return resultList;
   }
}

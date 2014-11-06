package com.agilerenovation.conference.eventtype.integration;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;


public class ConferenceEventTypeFactory {
   
   //Constructors and factory methods
   
   //Public accessors and mutators
   public ConferenceEventType create( final ConferenceEventTypes eventTypeDef ){
      ConferenceEventType newEventType = new ConferenceEventType( eventTypeDef.getName(), eventTypeDef.getDescription() );
      
      return newEventType;
   }
}

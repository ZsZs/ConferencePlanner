package com.agilerenovation.conference.eventtype.integration;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.agilerenovation.conference.eventtype.domain.ParentTimeFrameRestriction;


public class ConferenceEventTypeFactory {
   
   //Constructors and factory methods
   
   //Public accessors and mutators
   public ConferenceEventType create( final ConferenceEventTypes eventTypeDef ){
      ConferenceEventType newEventType = new ConferenceEventType( eventTypeDef.getName(), eventTypeDef.getDescription() );
      if( eventTypeDef.equals( ConferenceEventTypes.TALK ) || eventTypeDef.equals( ConferenceEventTypes.LIGHTENING_TALK )){
         newEventType.addRequiredConstraint( ParentTimeFrameRestriction.class );
      }
      return newEventType;
   }
}

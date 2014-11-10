package com.agilerenovation.conference.eventtype.domain;

import com.agilerenovation.conference.event.domain.CompositeConferenceEvent;
import com.agilerenovation.conference.event.domain.ConferenceEvent;

public class ParentTimeFrameRestriction extends SchedulingConstraint {

   @Override
   public boolean analyseEvent( CompositeConferenceEvent parentEvent, ConferenceEvent eventToAnalyse ) {
      return parentEvent.getEarliestEnd().compareTo( eventToAnalyse.getEarliestEnd() ) > 0 ;
   }

}

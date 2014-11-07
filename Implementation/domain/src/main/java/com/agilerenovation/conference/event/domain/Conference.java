package com.agilerenovation.conference.event.domain;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;

public class Conference extends CompositeConferenceEvent {

   public Conference( ConferenceEventType type, String name, DateTime plannedStart, DateTime plannedEnd ) {
      super( type, null, name, plannedStart, plannedEnd );
   }
}

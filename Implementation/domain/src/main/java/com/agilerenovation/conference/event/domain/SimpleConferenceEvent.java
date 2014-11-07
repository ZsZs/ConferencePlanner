package com.agilerenovation.conference.event.domain;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;

public abstract class SimpleConferenceEvent extends ConferenceEvent {

   public SimpleConferenceEvent( ConferenceEventType type, CompositeConferenceEvent parent, String name, DateTime plannedStart, DateTime plannedEnd ) {
      super( type, parent, name, plannedStart, plannedEnd );
   }

}

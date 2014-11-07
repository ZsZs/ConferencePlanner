package com.agilerenovation.conference.event.domain;

import java.util.List;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;

public class ConferenceDay extends CompositeConferenceEvent {

   public ConferenceDay( ConferenceEventType type, CompositeConferenceEvent parent, String name, DateTime plannedStart, DateTime plannedEnd ) {
      super( type, parent, name, plannedStart, plannedEnd );
   }

   public List<ConferenceEvent> getNetworkEvents() {
      return getEventsByType( ConferenceEventTypes.NETWORK_EVENT );
   }

   public List<ConferenceEvent> getCateringEvents() {
      return getEventsByType( ConferenceEventTypes.CATERING );
   }

}

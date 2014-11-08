package com.agilerenovation.conference.eventtype.domain;

import com.agilerenovation.conference.event.domain.CompositeConferenceEvent;
import com.agilerenovation.conference.event.domain.ConferenceEvent;

public abstract class SchedulingConstraint {
   public abstract boolean analyseEvent( CompositeConferenceEvent parentEvent, ConferenceEvent eventToAnalyse );
}

package com.agilerenovation.conference.event.domain;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeRepository;
import com.agilerenovation.conference.paper.domain.ConferencePaper;

public class ConferenceSession extends CompositeConferenceEvent {

   public ConferenceSession( ConferenceEventType type, CompositeConferenceEvent parent, String name, DateTime plannedStart, DateTime plannedEnd ) {
      super( type, parent, name, plannedStart, plannedEnd );
   }

   public boolean canEventScheduled( ConferenceEvent eventToSchedule ){
      return eventToSchedule.getType().verifyConstraints( this, eventToSchedule );
   }
   
   public boolean canPaperScheduled( ConferencePaper conferencePaper ) {
      ConferenceTalk talk = instantiateTemporaryTalk( conferencePaper );
      return canEventScheduled( talk );
   }

   @Override
   public void addEvent( ConferenceEvent event ) {
      super.addEvent( event );
      this.calculateScheduledEnd();
   }

   private ConferenceTalk instantiateTemporaryTalk( ConferencePaper conferencePaper ) {
      ConferenceEventTypeRepository eventTypeRepository = ConferenceEventTypeRepository.getInstance();
      ConferenceEventType eventType = eventTypeRepository.findByName( ConferenceEventTypes.TALK );
      DateTime plannedStart = this.scheduledEnd;
      DateTime plannedEnd = this.scheduledEnd.plusMinutes( conferencePaper.getLength() );
      ConferenceTalk talk = new ConferenceTalk( eventType, null, conferencePaper.getTitle(), plannedStart, plannedEnd );
      return talk;
   }
}

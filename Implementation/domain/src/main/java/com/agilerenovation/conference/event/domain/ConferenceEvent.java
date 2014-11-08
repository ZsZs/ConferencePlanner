package com.agilerenovation.conference.event.domain;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;

public abstract class ConferenceEvent {
   protected String name;
   protected DateTime scheduledEnd;
   protected DateTime scheduledStart;
   protected CompositeConferenceEvent parent;
   protected DateTime plannedEnd;
   protected DateTime plannedStart;
   protected ConferenceEventType type;
   
   public ConferenceEvent( ConferenceEventType type, CompositeConferenceEvent parent, String name, DateTime plannedStart, DateTime plannedEnd ){
      this.type = type;
      this.name = name;
      this.plannedStart = plannedStart;
      this.plannedEnd = plannedEnd;
      this.scheduledStart = plannedStart;
      this.scheduledEnd = plannedStart;
      this.parent = parent;
      if( parent != null ){
         parent.addEvent( this );
      }
   }

   //Public accessors and mutators
   
   //Properties
   public String getName() { return name; }
   public ConferenceEvent getParent() { return parent; }
   public DateTime getScheduledEnd() { return scheduledEnd; }
   public DateTime getScheduledStart() { return scheduledStart; }
   public DateTime getPlannedEnd() { return plannedEnd; }
   public DateTime getPlannedStart() { return plannedStart; }
   public ConferenceEventType getType() { return type; }
   public void setName( String name ) { this.name = name; }
   public void setPlannedEnd( DateTime plannedEnd ) { this.plannedEnd = plannedEnd; }
   public void setPlannedStart( DateTime plannedStart ) { this.plannedStart = plannedStart; }   
   public void setScheduledEnd( DateTime scheduledEnd ) { this.scheduledEnd = scheduledEnd; }
   public void setScheduledStart( DateTime scheduledStart ) { this.scheduledStart = scheduledStart; }

   //Protected, private helper methods
   protected void calculateScheduledEnd() {
      scheduledEnd = plannedEnd;
   }   
}

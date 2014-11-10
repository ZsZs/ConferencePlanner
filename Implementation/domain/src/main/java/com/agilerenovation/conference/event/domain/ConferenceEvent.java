package com.agilerenovation.conference.event.domain;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;

public abstract class ConferenceEvent {
   protected String name;
   protected DateTime scheduledEnd;
   protected DateTime scheduledStart;
   protected CompositeConferenceEvent parent;
   protected DateTime earliestEnd;
   protected DateTime earliestStart;
   protected DateTime latestEnd;
   protected DateTime latestStart;
   protected ConferenceEventType type;
   
   public ConferenceEvent( ConferenceEventType type, CompositeConferenceEvent parent, String name, DateTime earliestStart, DateTime earliestEnd ){
      this.type = type;
      this.name = name;
      this.earliestStart = earliestStart;
      this.earliestEnd = earliestEnd;
      this.scheduledStart = earliestStart;
      this.scheduledEnd = earliestStart;
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
   public DateTime getEarliestEnd() { return earliestEnd; }
   public DateTime getEarliestStart() { return earliestStart; }
   public DateTime getLatestEnd() { return latestEnd; }
   public DateTime getLatestStart() { return latestStart; }
   public ConferenceEventType getType() { return type; }
   public void setName( String name ) { this.name = name; }
   public void setPlannedEnd( DateTime plannedEnd ) { this.earliestEnd = plannedEnd; }
   public void setPlannedStart( DateTime plannedStart ) { this.earliestStart = plannedStart; }   
   public void setScheduledEnd( DateTime scheduledEnd ) { this.scheduledEnd = scheduledEnd; }
   public void setScheduledStart( DateTime scheduledStart ) { this.scheduledStart = scheduledStart; }

   //Protected, private helper methods
   protected void calculateScheduledEnd() {
      scheduledEnd = earliestEnd;
   }   
}

package com.agilerenovation.conference.eventtype.domain;

import java.util.List;

import com.agilerenovation.conference.event.domain.CompositeConferenceEvent;
import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.agilerenovation.conference.repository.integration.AggregationRoot;
import com.google.common.collect.Lists;

public class ConferenceEventType implements AggregationRoot{
   private String description;
   private final String name;
   private List<SchedulingConstraint> requiredConstrains = Lists.newArrayList();
   
   //Constructors
   public ConferenceEventType( final String name ){
      this( name, null );
   }

   public ConferenceEventType( final String name, final String description ){
      this.name = name;
      this.description = description;
   }
   
   //Public accossors and mutators
   public void addRequiredConstraint( Class<? extends SchedulingConstraint> constraintClass ){
      try{
         SchedulingConstraint schedulingConstraing = constraintClass.newInstance();
         requiredConstrains.add( schedulingConstraing );
      }catch( InstantiationException | IllegalAccessException e ){
         e.printStackTrace();
      }
   }

   //Properties
   public String getDescription() { return description; }
   public String getName(){ return name; }

   public boolean verifyConstraints( CompositeConferenceEvent parentEvent, ConferenceEvent eventToSchedule ) {
      boolean isVerified = true;
      for( SchedulingConstraint schedulingConstraint : requiredConstrains ){
         if( !schedulingConstraint.analyseEvent( parentEvent, eventToSchedule )){
            isVerified = false;
            break;
         }
      }
      return isVerified;
   }
}

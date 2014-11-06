package com.agilerenovation.conference.eventtype.domain;

import com.agilerenovation.conference.repository.integration.AggregationRoot;

public class ConferenceEventType implements AggregationRoot{
   private String description;
   private final String name;
   
   public ConferenceEventType( final String name ){
      this( name, null );
   }

   public ConferenceEventType( final String name, final String description ){
      this.name = name;
      this.description = description;
   }

   //Properties
   public String getDescription() { return description; }
   public String getName(){ return name; }
}

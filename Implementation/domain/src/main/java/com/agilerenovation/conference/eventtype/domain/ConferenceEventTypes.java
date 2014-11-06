package com.agilerenovation.conference.eventtype.domain;

public enum ConferenceEventTypes {
   CONFERENCE( "Conference", "Gathering of professionals for sharing knowledge." ),
   CONFERENCE_DAY( "Conference Day", "" ),
   TRACK( "Track", "" ),
   SESSION( "Session", "" ),
   TALK( "Talk", "" ),
   LIGHTENING_TALK( "Lightening Talk", "Very short talk to flash an idea." ),
   NETWORK_EVENT( "Network Event", "" ),
   CATERING( "Catering", "" );
   
   private final String name;
   private final String description;
   
   private ConferenceEventTypes( final String name, final String description ){
      this.name = name;
      this.description = description;
   }
   
   //Properties
   public String getDescription(){ return description; }
   public String getName(){ return name; }
   
}

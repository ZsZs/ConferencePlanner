package com.agilerenovation.conference.papertype.domain;

public enum ConferencePaperTypes {
   TALK( "Talk", "maximum 60 minute presentation" ),
   LIGHTENING_TALK( "Lightening Talk", "5 minutes long presentaion" );
   
   private final String name;
   private final String description;
   
   private ConferencePaperTypes( final String name, final String description ){
      this.name = name;
      this.description = description;
   }
   
   //Properties
   public String getDescription(){ return description; }
   public String getName(){ return name; }
   
}

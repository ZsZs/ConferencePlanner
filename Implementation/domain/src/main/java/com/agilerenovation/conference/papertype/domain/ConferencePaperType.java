package com.agilerenovation.conference.papertype.domain;

public class ConferencePaperType {
   private String description;
   private String name;

   //Constructors
   public ConferencePaperType( final String name, final String description ){
      this.name = name;
      this.description = description;
   }
   
   public String getDescription(){ return description; }
   public String getName() { return name; }

}

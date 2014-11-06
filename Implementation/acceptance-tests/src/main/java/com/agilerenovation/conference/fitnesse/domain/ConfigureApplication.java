package com.agilerenovation.conference.fitnesse.domain;

import com.agilerenovation.conference.manager.domain.ConferenceManager;

public class ConfigureApplication {
   private ConferenceManager conferenceManager;

   //Constructors
   public ConfigureApplication(){
      conferenceManager = ConferenceManager.getInstance();
   }
   
   public void setUpEventTypes(){
      conferenceManager.setUpEventTypes();
   }
   
}

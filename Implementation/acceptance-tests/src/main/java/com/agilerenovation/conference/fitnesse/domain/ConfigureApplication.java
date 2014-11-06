package com.agilerenovation.conference.fitnesse.domain;

import com.agilerenovation.conference.manager.domain.ConferenceManager;

public class ConfigureApplication {
   private ConferenceManager conferenceManager;
   private static boolean isConfigured = false;

   //Constructors
   public ConfigureApplication(){
      conferenceManager = ConferenceManager.getInstance();
   }
   
   public void configure(){
      if( !isConfigured ){
         setUpEventTypes();
         setUpPaperTypes();
         isConfigured = true;
      }
   }
   
   private void setUpEventTypes(){
      conferenceManager.setUpEventTypes();
   }
   
   private void setUpPaperTypes(){
      conferenceManager.setUpPaperTypes();
   }
   
}

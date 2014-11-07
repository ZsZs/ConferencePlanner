package com.agilerenovation.conference.fitnesse.usecase;

import com.agilerenovation.conference.manager.domain.ConferenceManager;

public class ScheduleEvents {
   private ConferenceManager conferenceManager;
   
   public ScheduleEvents(){
      conferenceManager = ConferenceManager.getInstance();
   }
   
   public void scheduleSubmittedPapers(){
      conferenceManager.scheduleEvents();
   }
}

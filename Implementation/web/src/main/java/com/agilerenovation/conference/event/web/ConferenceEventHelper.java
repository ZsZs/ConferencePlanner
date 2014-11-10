package com.agilerenovation.conference.event.web;

import java.util.Set;

import com.agilerenovation.conference.event.artifact.ConferenceEventDataSheet;
import com.agilerenovation.conference.manager.web.ConferenceTestData;

public class ConferenceEventHelper {

   public ConferenceEventHelper(){
      ConferenceTestData testData = ConferenceTestData.getInstance();
      testData.setUp();
   }
   
   //Properties
   public ConferenceEventDataSheet getConference(){
      Set<ConferenceEventDataSheet> conferences = ConferenceEventDataSheet.findConferences();
      ConferenceEventDataSheet firstConference = null;
      for( ConferenceEventDataSheet conferenceEventDataSheet : conferences ){
         firstConference = conferenceEventDataSheet;
         break;
      }
      return firstConference;
   }
   
   public String getConferenceEarliestEnd(){
      return getConference().getEarliestEnd();
   }
   
   public String getConferenceEarliestStart(){
      return getConference().getEarliestStart();
   }
   
   public String getConferenceName(){
      return getConference().getName();
   }
   
   public Set<ConferenceEventDataSheet> getDays(){
      return ConferenceEventDataSheet.findDays();
   }   
   
   public Set<ConferenceEventDataSheet> getTracks(){
      return ConferenceEventDataSheet.findTracks();
   }   
}

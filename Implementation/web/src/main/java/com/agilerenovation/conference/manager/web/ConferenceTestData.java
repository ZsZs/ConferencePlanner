package com.agilerenovation.conference.manager.web;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.agilerenovation.conference.event.domain.Conference;
import com.agilerenovation.conference.event.domain.ConferenceDay;
import com.agilerenovation.conference.event.domain.ConferenceTrack;
import com.agilerenovation.conference.event.integration.ConferenceEventFactory;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.agilerenovation.conference.manager.domain.ConferenceManager;

public class ConferenceTestData {
   private static boolean isTestConfigured = false;
   private static ConferenceTestData soleInstance;
   
   private ConferenceTestData(){
   }
   
   public static ConferenceTestData getInstance(){
      if( soleInstance == null ){
         soleInstance = new ConferenceTestData();
      }
      return soleInstance;
   }
   
   public void setUp() {
      if( !isTestConfigured ){
         ConferenceManager conferenceManager = ConferenceManager.getInstance();
         
         conferenceManager.setUpEventTypes();
         conferenceManager.setUpPaperTypes();
         conferenceManager.importTalks( "file:src/main/resources/webapp/ConferenceSubmissions.xml" );
         
         ConferenceEventFactory factory = new ConferenceEventFactory();
         ConferenceEventRepository repository = ConferenceEventRepository.getInstance();
         
         DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern( "MM/dd/yyyy HH:mm" );
         Conference conference = factory.createConference( "Hello World", dateTimeFormatter.parseDateTime( "12/15/2014 09:00" ), dateTimeFormatter.parseDateTime( "12/15/2014 23:00" ));
         repository.add( conference );
         
         ConferenceDay day = factory.createDay( "First day", conference );
         repository.add( day );
         
         ConferenceTrack track = factory.createTrack( "Track-I", day );
         repository.add( track );
         
         isTestConfigured = true;
      }
   }

}

package com.agilerenovation.conference.manager.domain;

import java.util.List;
import java.util.Set;

import com.agilerenovation.conference.event.domain.CompositeConferenceEvent;
import com.agilerenovation.conference.event.domain.Conference;
import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.agilerenovation.conference.event.domain.ConferenceTalk;
import com.agilerenovation.conference.event.integration.ConferenceEventFactory;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeFactory;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeRepository;
import com.agilerenovation.conference.paper.domain.ConferencePaper;
import com.agilerenovation.conference.paper.integration.ConferencePaperRepository;
import com.agilerenovation.conference.papertype.domain.ConferencePaperTypes;
import com.agilerenovation.conference.papertype.integration.ConferencePaperTypeFactory;
import com.agilerenovation.conference.papertype.integration.ConferencePaperTypeRepository;

public class ConferenceManager {
   private ConferenceEventFactory eventFactory;
   private ConferenceEventRepository eventRepository;
   private ConferenceEventTypeFactory eventTypeFactory;
   private ConferenceEventTypeRepository eventTypeRepository;
   private ConferencePaperRepository paperRepository;
   private ConferencePaperTypeFactory paperTypeFactory;
   private ConferencePaperTypeRepository paperTypeRepository;
   private static ConferenceManager soleInstance;
   private static boolean isTestConfigured = false;
   
   //Constructors and factory methods
   private ConferenceManager(){
      eventFactory = new ConferenceEventFactory();
      eventRepository = ConferenceEventRepository.getInstance();
      
      eventTypeRepository = ConferenceEventTypeRepository.getInstance();
      eventTypeFactory = new ConferenceEventTypeFactory();
      
      paperRepository = ConferencePaperRepository.getInstance();
      
      paperTypeRepository = ConferencePaperTypeRepository.getInstance();
      paperTypeFactory = new ConferencePaperTypeFactory();
   }
   
   public static ConferenceManager getInstance(){
      if( soleInstance == null ){
         soleInstance = new ConferenceManager();
      }
      
      return soleInstance;
   }
   
   //Public accessors and mutators
   public void importTalks( final String resourceSpecifier ) {
      paperRepository.importTalks( resourceSpecifier );
   }

   public void scheduleEvents() {
      scheduleSubmittedPapers();
      scheduleNetworkEvent();
   }
   
   public void setUpEventTypes(){
      for( ConferenceEventTypes eventType : ConferenceEventTypes.values() ){
         eventTypeRepository.add( eventTypeFactory.create( eventType ));
      }
   }

   public void setUpPaperTypes() {
      for( ConferencePaperTypes paperType : ConferencePaperTypes.values() ){
         paperTypeRepository.add( paperTypeFactory.create( paperType ));
      }
   }
   
   //Protected, private helper methods
   private void scheduleNetworkEvent(){
      List<ConferenceEvent> conferenceDays = eventRepository.findByType( ConferenceEventTypes.CONFERENCE_DAY );
      for( ConferenceEvent conferenceDay : conferenceDays ){
      }
      
      List<ConferenceEvent> networkEvents = eventRepository.findByType( ConferenceEventTypes.NETWORK_EVENT );
      for( ConferenceEvent networkEvent : networkEvents ){
//         networkEvent.calculateScheduledEnd();
      }
   }
   
   private void scheduleSubmittedPapers() {
      Set<ConferencePaper> submittedPapers = paperRepository.findAll();
      List<ConferenceEvent> conferences = eventRepository.findByType( ConferenceEventTypes.CONFERENCE );
      Conference conference = (Conference) conferences.get( 0 );
      
      for( ConferencePaper conferencePaper : submittedPapers ){
         CompositeConferenceEvent appropriateSession = conference.findSessionForPaper( conferencePaper );
         if( appropriateSession != null ){
            ConferenceTalk talk = eventFactory.createTalk( appropriateSession, conferencePaper );
            talk.setScheduledStart( talk.getEarliestStart() );
            talk.setScheduledEnd( talk.getEarliestEnd() );
            eventRepository.add( talk );
         }
      }
   }

}

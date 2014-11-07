package com.agilerenovation.conference.manager.domain;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeFactory;
import com.agilerenovation.conference.eventtype.integration.ConferenceEventTypeRepository;
import com.agilerenovation.conference.papertype.domain.ConferencePaperTypes;
import com.agilerenovation.conference.papertype.integration.ConferencePaperTypeFactory;
import com.agilerenovation.conference.papertype.integration.ConferencePaperTypeRepository;

public class ConferenceManager {
   private static ConferenceManager soleInstance;
   
   //Constructors and factory methods
   private ConferenceManager(){
   }
   
   public static ConferenceManager getInstance(){
      if( soleInstance == null ){
         soleInstance = new ConferenceManager();
      }
      
      return soleInstance;
   }
   
   //Public accessors and mutators
   public void scheduleEvents() {
   }
   
   public void setUpEventTypes(){
      ConferenceEventTypeRepository eventTypeRepository = ConferenceEventTypeRepository.getInstance();
      ConferenceEventTypeFactory eventTypeFactory = new ConferenceEventTypeFactory();
      
      for( ConferenceEventTypes eventType : ConferenceEventTypes.values() ){
         eventTypeRepository.add( eventTypeFactory.create( eventType ));
      }
   }

   public void setUpPaperTypes() {
      ConferencePaperTypeRepository paperTypeRepository = ConferencePaperTypeRepository.getInstance();
      ConferencePaperTypeFactory paperTypeFactory = new ConferencePaperTypeFactory();

      for( ConferencePaperTypes paperType : ConferencePaperTypes.values() ){
         paperTypeRepository.add( paperTypeFactory.create( paperType ));
      }
   }
   
   //Protected, private helper methods
}

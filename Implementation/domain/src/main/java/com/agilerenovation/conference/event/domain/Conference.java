package com.agilerenovation.conference.event.domain;

import java.util.List;

import org.joda.time.DateTime;

import com.agilerenovation.conference.eventtype.domain.ConferenceEventType;
import com.agilerenovation.conference.eventtype.domain.ConferenceEventTypes;
import com.agilerenovation.conference.paper.domain.ConferencePaper;

public class Conference extends CompositeConferenceEvent {

   public Conference( ConferenceEventType type, String name, DateTime earliestStart, DateTime earliestEnd ) {
      super( type, null, name, earliestStart, earliestEnd );
   }

   public CompositeConferenceEvent findSessionForPaper( ConferencePaper conferencePaper ) {
      List<ConferenceSession> sessions = getEventsByType( ConferenceEventTypes.SESSION, ConferenceSession.class );
      CompositeConferenceEvent foundSession = null;
      for( ConferenceSession session : sessions ){
         if( session.canPaperScheduled( conferencePaper )){
            foundSession = session;
            break;
         }
      }
      
      return foundSession;
   }
}

package com.agilerenovation.conference.paper.artifact;

import java.util.Set;

import com.agilerenovation.conference.paper.domain.ConferencePaper;
import com.agilerenovation.conference.paper.integration.ConferencePaperRepository;
import com.google.common.collect.Sets;

public class ConferencePaperDataSheet {
   private final ConferencePaper domainObject;
   private static ConferencePaperRepository repository = ConferencePaperRepository.getInstance();
   
   public ConferencePaperDataSheet( final ConferencePaper domainObject ){
      this.domainObject = domainObject;
   }
   
   public static Set<ConferencePaperDataSheet> findAll(){
      return wrapAllEvents( repository.findAll() );
   }
   
   //Properties
   public String getLength(){ return domainObject.getLength().toString(); }
   public String getTitle(){ return domainObject.getTitle(); }
   
   //Protected, private helper methods
   private static Set<ConferencePaperDataSheet> wrapAllEvents( final Set<ConferencePaper> evens ){
      Set<ConferencePaperDataSheet> dataSheets = Sets.newHashSet();
      for( ConferencePaper conferenceEvent : evens ){
         dataSheets.add( new ConferencePaperDataSheet( conferenceEvent ));
      }
      return dataSheets;
   }
}

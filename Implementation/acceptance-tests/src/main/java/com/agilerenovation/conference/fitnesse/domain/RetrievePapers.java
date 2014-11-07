package com.agilerenovation.conference.fitnesse.domain;

import static util.ListUtility.list;

import java.util.List;
import java.util.Set;

import com.agilerenovation.conference.paper.domain.ConferencePaper;
import com.agilerenovation.conference.paper.integration.ConferencePaperRepository;
import com.google.common.collect.Lists;

public class RetrievePapers {

   public void table( List<List<String>> table ) {
      // optional function
   }

   @SuppressWarnings( "unchecked" )
   public List<Object> query() {
      List<Object> resultList = Lists.newArrayList();
      Set<ConferencePaper> papers = ConferencePaperRepository.getInstance().findAll();
      
      for( ConferencePaper conferencePaper : papers ){
         List<String> title = list( "title", conferencePaper.getTitle());
         List<String> length = list( "length", conferencePaper.getLength().toString() );
         List<String> type = list( "type", conferencePaper.getType().getName() );
         
         resultList.add( list( title, length, type ));
      }
      return resultList;
   }
}

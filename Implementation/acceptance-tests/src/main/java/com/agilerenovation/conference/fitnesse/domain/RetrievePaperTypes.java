package com.agilerenovation.conference.fitnesse.domain;

import static util.ListUtility.list;

import java.util.List;
import java.util.Set;

import com.agilerenovation.conference.papertype.domain.ConferencePaperType;
import com.agilerenovation.conference.papertype.integration.ConferencePaperTypeRepository;
import com.google.common.collect.Lists;

public class RetrievePaperTypes {

   public void table( List<List<String>> table ) {
      // optional function
   }

   @SuppressWarnings( "unchecked" )
   public List<Object> query() {
      List<Object> resultList = Lists.newArrayList();
      Set<ConferencePaperType> paperTypes = ConferencePaperTypeRepository.getInstance().findAll();
      
      for( ConferencePaperType conferencePaperType : paperTypes ){
         List<String> nameValue = list( "name", conferencePaperType.getName());
         List<String> descriptionValue = list( "description", conferencePaperType.getDescription());
         
         resultList.add( list( nameValue, descriptionValue ));
      }
      return resultList;
   }
}

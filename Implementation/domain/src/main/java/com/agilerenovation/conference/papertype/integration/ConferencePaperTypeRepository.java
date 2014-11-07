package com.agilerenovation.conference.papertype.integration;

import java.util.Set;

import com.agilerenovation.conference.papertype.domain.ConferencePaperType;
import com.agilerenovation.conference.papertype.domain.ConferencePaperTypes;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;


public class ConferencePaperTypeRepository {
   private static ConferencePaperTypeRepository soleInstance;
   private static Set<ConferencePaperType> paperTypes = Sets.newHashSet();
   
   
   //Constructors and factory methods
   private ConferencePaperTypeRepository(){
   }
   
   public static ConferencePaperTypeRepository getInstance() {
      if( soleInstance == null ){
         soleInstance = new ConferencePaperTypeRepository();
      }
      
      return soleInstance;
   }

   //Public accessors and mutators
   public void add( final ConferencePaperType paperType ){
      paperTypes.add( paperType );
   }
   
   public Set<ConferencePaperType> findAll(){
      return ImmutableSet.copyOf( paperTypes );
   }

   public ConferencePaperType findByName( ConferencePaperTypes paperTypeEnum ) {
      ConferencePaperType foundType = null;
      
      for( ConferencePaperType conferencePaperType : paperTypes ){
         if( conferencePaperType.getName().equals( paperTypeEnum.getName() )){
            foundType = conferencePaperType;
            break;
         }
      }
      
      return foundType;
   }
}

package com.agilerenovation.conference.paper.domain;

import com.agilerenovation.conference.papertype.domain.ConferencePaperType;
import com.agilerenovation.conference.papertype.domain.ConferencePaperTypes;
import com.agilerenovation.conference.papertype.integration.ConferencePaperTypeRepository;

public class ConferencePaper {
   private Integer length;
   private String title;
   private ConferencePaperType type;
   
   //Constructors and factory methods
   public ConferencePaper( final ConferencePaperType type, final String title, final Integer length ){
      this.type = type;
      this.title = title;
      this.length = length;
   }
   
   public static ConferencePaper create( String title, String length ) {
      ConferencePaper talk = null;
      ConferencePaperTypeRepository paperTypeRepository = ConferencePaperTypeRepository.getInstance();
      
      if( length == null || length == "" ){
         talk = new ConferencePaper( paperTypeRepository.findByName( ConferencePaperTypes.LIGHTENING_TALK ), title, 5 );
      }else{
         talk = new ConferencePaper( paperTypeRepository.findByName( ConferencePaperTypes.TALK ), title, Integer.parseInt( length ));
      }
      
      return talk;
   }

   //Properties
   public Integer getLength() { return length; }
   public String getTitle() { return title; }
   public ConferencePaperType getType() { return type; }
   public void setLength( Integer length ) { this.length = length; }
   public void setTitle( String title ) { this.title = title; }

}

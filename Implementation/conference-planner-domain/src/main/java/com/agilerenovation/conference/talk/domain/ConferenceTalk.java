package com.agilerenovation.conference.talk.domain;

public class ConferenceTalk {
   private Integer length;
   private String title;
   
   //Constructors and factory methods
   public ConferenceTalk( final String title, final Integer length ){
      this.title = title;
      this.length = length;
   }
   
   public static ConferenceTalk create( String title, String length ) {
      ConferenceTalk talk = null;
      
      if( length == null || length == "" ){
         talk = new LighteningTalk( title );
      }else{
         talk = new ConferenceTalk( title, Integer.parseInt( length ));
      }
      
      return talk;
   }

   //Properties
   public Integer getLength() { return length; }
   public String getTitle() { return title; }
   public void setLength( Integer length ) { this.length = length; }
   public void setTitle( String title ) { this.title = title; }
}

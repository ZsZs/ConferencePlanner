package com.agilerenovation.conference.talk.domain;

public class ConferencePaper {
   private Integer length;
   private String title;
   
   //Constructors and factory methods
   public ConferencePaper( final String title, final Integer length ){
      this.title = title;
      this.length = length;
   }
   
   public static ConferencePaper create( String title, String length ) {
      ConferencePaper talk = null;
      
      if( length == null || length == "" ){
         talk = new ConferencePaper( title, 5 );
      }else{
         talk = new ConferencePaper( title, Integer.parseInt( length ));
      }
      
      return talk;
   }

   //Properties
   public Integer getLength() { return length; }
   public String getTitle() { return title; }
   public void setLength( Integer length ) { this.length = length; }
   public void setTitle( String title ) { this.title = title; }
}

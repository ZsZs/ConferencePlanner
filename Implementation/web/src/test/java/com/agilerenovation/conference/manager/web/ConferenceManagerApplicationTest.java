package com.agilerenovation.conference.manager.web;

import org.junit.Before;
import org.junit.Test;

public class ConferenceManagerApplicationTest {
   private ConferenceManagerApplication application;

   @Before public void beforeEachTests(){
      application = new ConferenceManagerApplication( 8000 );
   }
   
   @Test public void start_startsWebApplication() throws Exception{
      application.start(); 
   }
}

package com.agilerenovation.conference.fitnesse.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RetrieveEventTypesTest {
   @Before public void beforeEachTests(){
      ConfigureApplication configureApplication = new ConfigureApplication();
      configureApplication.configure();
   }
   
   @Test public void collectsDefinedEventTypes(){
      RetrieveEventTypes configuredEventTypes = new RetrieveEventTypes();
      
      List<Object> rows = configuredEventTypes.query();
      
      assertThat( rows.size(), greaterThan( 1 ));
   }
}

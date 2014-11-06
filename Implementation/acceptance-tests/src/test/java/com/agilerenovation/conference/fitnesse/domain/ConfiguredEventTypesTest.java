package com.agilerenovation.conference.fitnesse.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConfiguredEventTypesTest {
   @Before public void beforeEachTests(){
      ConfigureApplication configureApplication = new ConfigureApplication();
      configureApplication.setUpEventTypes();
   }
   
   @Test public void collectsDefinedEventTypes(){
      ConfiguredEventTypes configuredEventTypes = new ConfiguredEventTypes();
      
      List<Object> rows = configuredEventTypes.query();
      
      assertThat( rows.size(), greaterThan( 1 ));
   }
}

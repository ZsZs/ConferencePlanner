package com.agilerenovation.conference.talk.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.agilerenovation.conference.resource.integration.Resource;

public class ConferenceTalkRepositoryTest {
   private static final String INPUT_DATA_PATH = "classpath:/ConferenceSubmissions.xml";
   private ConferenceTalkRepository repository;

   @Before public void beforeEachTests(){
      repository = new ConferenceTalkRepository();
   }
   
   @Test public void importTalks_instantiatesTalks() throws MalformedURLException, XPathExpressionException{
      //SETUP:
      Document inputData = Resource.create( INPUT_DATA_PATH ).getDomDocument();
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList nodes = (NodeList) xPath.evaluate( ConferenceTalkRepository.XPATH_SUBMISSIONS, inputData, XPathConstants.NODESET );
      
      //EXECUTION:
      repository.importTalks( INPUT_DATA_PATH );
      
      //VERIFY:
      assertThat( repository.getAll().size(), equalTo( nodes.getLength() ));
   }
   
   //private test helper methods
}

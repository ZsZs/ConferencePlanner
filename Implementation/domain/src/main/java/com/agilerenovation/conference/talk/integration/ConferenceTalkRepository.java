package com.agilerenovation.conference.talk.integration;

import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.agilerenovation.conference.resource.integration.Resource;
import com.agilerenovation.conference.talk.domain.ConferenceTalk;
import com.google.common.collect.Sets;

public class ConferenceTalkRepository {
   public static final String XPATH_SUBMISSIONS = "/submissions/submission";
   private Set<ConferenceTalk> talks = Sets.newHashSet();

   // public accessors and mutators
   public void importTalks( final String resourceSpecifier ) {
      NodeList submissionNodes = readSubmissionNodes( resourceSpecifier );
      instantiateTalksFromXml( submissionNodes );
   }

   public Resource getImportResource() {
      return null;
   }

   // properties
   public Set<ConferenceTalk> getAll() {
      return talks;
   }

   // Protected, private helper methods
   private void instantiateTalksFromXml( final NodeList submissionNodes ) {
      for( int i = 0; i < submissionNodes.getLength(); i++ ){
         if( submissionNodes.item( i ).getNodeType() == Node.ELEMENT_NODE ){
            Element element = (Element) submissionNodes.item( i );
            String title = element.getElementsByTagName( "title" ).item( 0 ).getTextContent();
            String length = element.getElementsByTagName( "length" ).item( 0 ).getTextContent();
            ConferenceTalk talk = ConferenceTalk.create( title, length );
            talks.add( talk );
         }
      }
   }

   private NodeList readSubmissionNodes( final String resourceSpecifier ) {
      Resource inputResource = Resource.create( resourceSpecifier );
      Document inputDocument = inputResource.getDomDocument();
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList inputNodes = null;
      try{
         inputNodes = (NodeList) xPath.evaluate( XPATH_SUBMISSIONS, inputDocument, XPathConstants.NODESET );
      }catch( XPathExpressionException e ){
         e.printStackTrace();
      }

      return inputNodes;
   }

}
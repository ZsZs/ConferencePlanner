package com.agilerenovation.conference.fitnesse.usecase;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.agilerenovation.conference.paper.integration.ConferencePaperRepository;

public class ImportPapersTest {
   @Test public void instantiatesPapersFromXml(){
      ImportPapers importPapers = new ImportPapers();
      importPapers.importFile( "file:../integration-tests/src/test/resources/ConferenceSubmissions.xml" );
     
      ConferencePaperRepository paperRepository = ConferencePaperRepository.getInstance();
      assertThat( paperRepository.findAll().size(), greaterThan( 1 ));
   }
}

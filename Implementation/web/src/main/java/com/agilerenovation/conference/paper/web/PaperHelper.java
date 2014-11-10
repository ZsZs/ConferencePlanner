package com.agilerenovation.conference.paper.web;

import java.util.Set;

import com.agilerenovation.conference.manager.web.ConferenceTestData;
import com.agilerenovation.conference.paper.artifact.ConferencePaperDataSheet;

public class PaperHelper {
   public PaperHelper(){
      ConferenceTestData testData = ConferenceTestData.getInstance();
      testData.setUp();
   }
   
   public Set<ConferencePaperDataSheet> getPapers(){
      Set<ConferencePaperDataSheet> paperList = ConferencePaperDataSheet.findAll();
      return paperList;
   }
}

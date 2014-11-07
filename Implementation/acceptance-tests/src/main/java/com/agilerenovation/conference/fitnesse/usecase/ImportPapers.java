package com.agilerenovation.conference.fitnesse.usecase;

import com.agilerenovation.conference.paper.integration.ConferencePaperRepository;

public class ImportPapers {
   public void importFile( final String resourceSpecifier ){
      ConferencePaperRepository paperRepository = ConferencePaperRepository.getInstance();
      paperRepository.importTalks( resourceSpecifier );
   }
}

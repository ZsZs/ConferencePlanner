package com.agilerenovation.conference.papertype.integration;

import com.agilerenovation.conference.papertype.domain.ConferencePaperType;
import com.agilerenovation.conference.papertype.domain.ConferencePaperTypes;

public class ConferencePaperTypeFactory {

   public ConferencePaperType create( ConferencePaperTypes paperType ) {
      return new ConferencePaperType( paperType.getName(), paperType.getDescription() );
   }

}

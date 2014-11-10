package com.agilerenovation.conference.manager.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilerenovation.conference.manager.domain.ConferenceManager;
import com.agilerenovation.conference.paper.domain.ConferencePaper;
import com.agilerenovation.conference.paper.integration.ConferencePaperRepository;

public class CommandController extends HttpServlet {
   private static final long serialVersionUID = 8167721693175418700L;

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      ConferenceManager manager = ConferenceManager.getInstance();
      manager.setUpEventTypes();
      manager.setUpPaperTypes();
      manager.importTalks( "file:src/main/resources/webapp/ConferenceSubmissions.xml" );

      ConferencePaperRepository repsitory = ConferencePaperRepository.getInstance();
      Set<ConferencePaper> paperList = repsitory.findAll();

      request.setAttribute( "paperList", paperList );
      
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/submitted-papers.jsp");
      rd.forward(request, response);      
   }
}

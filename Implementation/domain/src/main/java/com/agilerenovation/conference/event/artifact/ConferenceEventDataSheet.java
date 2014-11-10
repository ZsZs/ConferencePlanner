package com.agilerenovation.conference.event.artifact;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.agilerenovation.conference.event.domain.ConferenceEvent;
import com.agilerenovation.conference.event.integration.ConferenceEventRepository;
import com.google.common.collect.Sets;

public class ConferenceEventDataSheet {
   private DateTimeFormatter dateTimeFormatter;
   private ConferenceEvent domainObject;
   private static ConferenceEventRepository repository = ConferenceEventRepository.getInstance();
   private DateTimeFormatter timeFormatter;
   
   public ConferenceEventDataSheet( final ConferenceEvent domainObject ){
      this.domainObject = domainObject;      
      dateTimeFormatter = DateTimeFormat.forPattern( "yyyy/MM/dd HH:mm" );
      timeFormatter = DateTimeFormat.forPattern( "HH:mm" );
   }
   
   public static Set<ConferenceEventDataSheet> findAll(){
      return wrapAllEvents( repository.findAll() );
   }
   
   public static Set<ConferenceEventDataSheet> findConferences(){
      return wrapAllEvents( repository.findConferences() );
   }
   
   public static Set<ConferenceEventDataSheet> findDays(){
      return wrapAllEvents( repository.findDays() );
   }
   
   public static Set<ConferenceEventDataSheet> findSessions(){
      return wrapAllEvents( repository.findSessions() );
   }
   
   public static Set<ConferenceEventDataSheet> findTalks(){
      return wrapAllEvents( repository.findTalks() );
   }
   
   public static Set<ConferenceEventDataSheet> findTracks(){
      return wrapAllEvents( repository.findTracks() );
   }
   
   //Properties
   public String getEarliestEnd() { return formatDateTime( domainObject.getEarliestEnd() ); }
   public String getEarliestStart() { return formatDateTime( domainObject.getEarliestStart() ); }
   public String getLatestEnd() { return formatDateTime( domainObject.getLatestEnd() ); }
   public String getLatestStart() { return formatDateTime( domainObject.getLatestStart() ); }   
   public String getName(){ return domainObject.getName(); }
   public String getScheduledEnd() { return formatDateTime( domainObject.getScheduledEnd() ); }
   public String getScheduledEndTime() { return formatTime( domainObject.getScheduledEnd() ); }
   public String getScheduledStart() { return formatDateTime( domainObject.getScheduledStart() ); }
   public String getScheduledStartTime() { return formatTime( domainObject.getScheduledStart() ); }
   
   //Protected, private helper methods
   private String formatDateTime( final DateTime dateTime ){
      return dateTimeFormatter.print( dateTime );
   }
   
   private String formatTime( final DateTime dateTime ){
      return timeFormatter.print( dateTime );
   }
   
   private static Set<ConferenceEventDataSheet> wrapAllEvents( final List<ConferenceEvent> evens ){
      Set<ConferenceEventDataSheet> dataSheets = Sets.newHashSet();
      for( ConferenceEvent conferenceEvent : evens ){
         dataSheets.add( new ConferenceEventDataSheet( conferenceEvent ));
      }
      return dataSheets;
   }
   
   private static Set<ConferenceEventDataSheet> wrapAllEvents( final Set<ConferenceEvent> evens ){
      Set<ConferenceEventDataSheet> dataSheets = Sets.newHashSet();
      for( ConferenceEvent conferenceEvent : evens ){
         dataSheets.add( new ConferenceEventDataSheet( conferenceEvent ));
      }
      return dataSheets;
   }
}

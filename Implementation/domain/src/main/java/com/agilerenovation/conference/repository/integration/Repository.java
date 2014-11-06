package com.agilerenovation.conference.repository.integration;

import java.util.Set;

import com.google.common.collect.Sets;

@SuppressWarnings( "rawtypes" )
public abstract class Repository <A extends AggregationRoot, R extends Repository>{
   protected R soleInstance;
   protected Set<A> eventTypes = Sets.newHashSet();

}

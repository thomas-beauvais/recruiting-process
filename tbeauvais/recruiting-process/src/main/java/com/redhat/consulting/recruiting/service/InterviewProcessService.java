package com.redhat.consulting.recruiting.service;

import com.redhat.consulting.recruiting.model.Candidate;
import com.redhat.consulting.recruiting.model.Interview;
import com.redhat.consulting.recruiting.model.Person;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class InterviewProcessService {

   @Inject
   private Logger log;

   @Inject
   private EntityManager em;

   @Inject
   private Event<Person> memberEventSrc;

   public void addPerson(Person person) throws Exception {
      log.info("Adding " + person.getClass().getSimpleName() + " " + person.getName() + "(" + person.getEmail() + ")" );
      
      em.persist(person);
      
//      memberEventSrc.fire(member);
   }

	public void addInterview(Interview interview) {
		Candidate candidate = interview.getCandidate();
		
		if ( candidate == null ) {
			throw new NullPointerException( "Must specifify Canditate when adding Interview." );
		}
		
		
		log.info("Adding " + candidate.getClass().getSimpleName() + " for " + candidate.getName() + "(" + candidate.getEmail() + ")" );
	      
	    em.persist(interview);	
	}
}

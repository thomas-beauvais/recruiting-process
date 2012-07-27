package com.redhat.consulting.recruiting.test;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.redhat.consulting.recruiting.model.Candidate;
import com.redhat.consulting.recruiting.model.Employee;
import com.redhat.consulting.recruiting.model.Interview;
import com.redhat.consulting.recruiting.model.InterviewEvent;
import com.redhat.consulting.recruiting.model.Person;
import com.redhat.consulting.recruiting.service.InterviewProcessService;
import com.redhat.consulting.recruiting.util.Resources;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class InterviewProcessTest {
   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Candidate.class, Employee.class, Person.class, InterviewEvent.class, Resources.class)
            .addClasses(Interview.class, InterviewProcessService.class )
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // Deploy our test datasource
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
   }

   @Inject
   InterviewProcessService interviewProcessService;

   @Inject
   Logger log;

   @Test
   public void testCreateEmployee() throws Exception {
	   createEmployee();
   }
   
   public Employee createEmployee() throws Exception {
	   	Employee interviewer = new Employee();
  		interviewer.setName("Jane Doe");
  		interviewer.setEmail( UUID.randomUUID() + "-jane@mailinator.com");
  		interviewer.setPhoneNumber("2125551234");
  
  		interviewProcessService.addPerson( interviewer );
  
  		assertNotNull(interviewer.getId());
  		log.info(interviewer.getName() + " was persisted with id " + interviewer.getId());
  
  		return interviewer;
   }
   
   @Test
   public void testCreateCandidate() throws Exception {
	   	createCandidate();
   }
   
   public Candidate createCandidate() throws Exception {
	   	Candidate candidate = new Candidate();
	   	candidate.setName("John Doe");
  		candidate.setEmail( UUID.randomUUID() + "-john@mailinator.com");
  		candidate.setPhoneNumber("0123456567");
      
  		interviewProcessService.addPerson( candidate );
      
  		assertNotNull(candidate.getId());
  		log.info(candidate.getName() + " was persisted with id " + candidate.getId());
      
  		return candidate;
   }
   
   @Test
   public void testCreateInterview() throws Exception {
	   createCandidate();
   }
   
   public Interview createInterview() throws Exception {
	   	Candidate candidate = createCandidate();
	   
	   	Interview interview = new Interview();
	   	interview.setCandidate(candidate);
	   
	   interviewProcessService.addInterview( interview );
	   
	   assertNotNull(interview.getId());
	   
	   log.info("Interview was persisted with id " + interview.getId() );
	   
	   return interview;
   }
   
   @Test 
   public void testCrateInterviewEvent() throws Exception {
	   Interview interview = createInterview();
	   Employee interviewer = createEmployee();
	   
	   // Verify that the cascading is working persist.. it will probably throw an error
	   InterviewEvent interviewEvent = interview.addInteviewEvent( interviewer );
	
	   assertNotNull(interviewEvent.getId());
		
	   log.info("InterviewEvent was persisted with id " + interviewEvent.getId() );
   }
   
}

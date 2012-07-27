package com.redhat.consulting.recruiting.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.redhat.consulting.recruiting.model.Candidate;
import com.redhat.consulting.recruiting.model.Employee;
import com.redhat.consulting.recruiting.model.Interview;
import com.redhat.consulting.recruiting.model.InterviewEvent;
import com.redhat.consulting.recruiting.model.Person;
import com.redhat.consulting.recruiting.service.InterviewProcessService;
import com.redhat.consulting.recruiting.util.Resources;

@RunWith(Arquillian.class)
public class InterviewProcessTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Candidate.class, Employee.class, Person.class,
						InterviewEvent.class, Resources.class)
				.addClasses(Interview.class, InterviewProcessService.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	InterviewProcessService interviewProcessService;

	@Inject
	EntityManager entityManager;

	@Inject
	Logger log;

	@Test
	public void testCreateEmployee() throws Exception {
		Employee employee1 = createEmployee();

		long id = employee1.getId();

		Employee employee2 = entityManager.find(Employee.class, id);
		assertNotNull(employee2);

		assertEquals(employee1.getName(), employee2.getName());
		assertEquals(employee1.getEmail(), employee2.getEmail());
		assertEquals(employee1.getId(), employee2.getId());
		assertEquals(employee1.getPhoneNumber(), employee2.getPhoneNumber());
		// assertEquals(employee1.getInterviewEvents(),
		// employee2.getInterviewEvents());

		// Finally, test the equals() method..
		assertEquals(employee1, employee2);
	}

	public Employee createEmployee() throws Exception {
		Employee interviewer = new Employee();
		interviewer.setName("Jane Doe");
		interviewer.setEmail(UUID.randomUUID() + "-jane@mailinator.com");
		interviewer.setPhoneNumber("2125551234");

		interviewProcessService.addPerson(interviewer);

		assertNotNull(interviewer.getId());
		log.info(interviewer.getName() + " was persisted with id "
				+ interviewer.getId());

		return interviewer;
	}

	@Test
	public void testCreateCandidate() throws Exception {
		Candidate candidate1 = createCandidate();

		long id = candidate1.getId();

		Candidate candidate2 = entityManager.find(Candidate.class, id);
		assertNotNull(candidate2);

		assertEquals(candidate1.getName(), candidate2.getName());
		assertEquals(candidate1.getEmail(), candidate2.getEmail());
		assertEquals(candidate1.getId(), candidate2.getId());
		assertEquals(candidate1.getPhoneNumber(), candidate2.getPhoneNumber());

		// Finally, test the equals() method..
		assertEquals(candidate1, candidate2);
	}

	public Candidate createCandidate() throws Exception {
		Candidate candidate = new Candidate();
		candidate.setName("John Doe");
		candidate.setEmail(UUID.randomUUID() + "-john@mailinator.com");
		candidate.setPhoneNumber("0123456567");

		interviewProcessService.addPerson(candidate);

		assertNotNull(candidate.getId());
		log.info(candidate.getName() + " was persisted with id "
				+ candidate.getId());

		return candidate;
	}

	@Test
	public void testCreateInterview() throws Exception {
		Interview interview1 = createInterview();

		long id = interview1.getId();

		Interview interview2 = entityManager.find(Interview.class, id);
		assertNotNull(interview2);

		assertEquals(interview1.getCandidate(), interview2.getCandidate());
		assertEquals(interview1.getId(), interview2.getId());

		// Finally, test the equals() method..
		assertEquals(interview1, interview2);
	}

	public Interview createInterview() throws Exception {
		Candidate candidate = createCandidate();

		Interview interview = new Interview();
		interview.setCandidate(candidate);

		interviewProcessService.addInterview(interview);

		assertNotNull(interview.getId());

		log.info("Interview was persisted with id " + interview.getId());

		return interview;
	}

	@Test
	public void testCrateInterviewEvent() throws Exception {
		Interview interview = createInterview();
		Employee interviewer = createEmployee();

		// Verify that the cascading is working persist.. it will probably throw
		// an error
		InterviewEvent interviewEvent = interview.addInteviewEvent(interviewer);

		assertNotNull(interviewEvent.getId());

		log.info("InterviewEvent was persisted with id "
				+ interviewEvent.getId());
	}

}

package com.redhat.consulting.recruiting.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Created with IntelliJ IDEA.
 * User: tbeauvais
 * Date: 7/26/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Employee extends Person {
	@ManyToMany( targetEntity = InterviewEvent.class )
	private List<InterviewEvent> interviewEvents;

	public List<InterviewEvent> getInterviewEvents() {
		return interviewEvents;
	}

	public void setInterviewEvents(List<InterviewEvent> interviewEvents) {
		this.interviewEvents = interviewEvents;
	}
}

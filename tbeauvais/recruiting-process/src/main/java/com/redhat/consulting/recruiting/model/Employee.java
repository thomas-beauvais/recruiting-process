package com.redhat.consulting.recruiting.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * Created with IntelliJ IDEA.
 * User: tbeauvais
 * Date: 7/26/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Employee extends Person {
	@ManyToMany( targetEntity = InterviewEvent.class, fetch = FetchType.EAGER )
	private List<InterviewEvent> interviewEvents = new Vector<InterviewEvent>();

	public List<InterviewEvent> getInterviewEvents() {
		return interviewEvents;
	}

	public void setInterviewEvents(List<InterviewEvent> interviewEvents) {
		this.interviewEvents = interviewEvents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((interviewEvents == null) ? 0 : interviewEvents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (interviewEvents == null) {
			if (other.interviewEvents != null)
				return false;
		} else if (interviewEvents.size() != other.interviewEvents.size() )
			return false;
		else {
			// TODO:  Find out if there is a Hibernate util to handle proxy collections equals()
			// CUSTOM: Iteration
			for ( int i = 0; i < interviewEvents.size(); i ++ ) {
				InterviewEvent interviewEvent = interviewEvents.get( i );
				if ( other.interviewEvents.get( i ).equals( interviewEvent ) ) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Employee [interviewEvents="
				+ (interviewEvents != null ? interviewEvents.subList(0,
						Math.min(interviewEvents.size(), maxLen)) : null) + "]";
	}

}

package com.redhat.consulting.recruiting.model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.faces.application.StateManager.SerializedView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Interview implements Serializable {
	@ManyToMany
	private List<InterviewEvent> inteviewEvents = new Vector<InterviewEvent>();
	
	@ManyToOne
	private Candidate candidate;
	
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<InterviewEvent> getInteviewEvents() {
		return inteviewEvents;
	}

	public void setInteviewEvents(List<InterviewEvent> inteviewEvents) {
		this.inteviewEvents = inteviewEvents;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public InterviewEvent addInteviewEvent(Employee interviewer) {
		InterviewEvent interviewEvent = new InterviewEvent( interviewer, this );
		this.inteviewEvents.add( interviewEvent );
		
		return interviewEvent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((candidate == null) ? 0 : candidate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interview other = (Interview) obj;
		if (candidate == null) {
			if (other.candidate != null)
				return false;
		} else if (!candidate.equals(other.candidate))
			return false;
		if (id != other.id)
			return false;
	
		// TODO:  Handle the collection
		
		return true;
	}
	
	
	
}

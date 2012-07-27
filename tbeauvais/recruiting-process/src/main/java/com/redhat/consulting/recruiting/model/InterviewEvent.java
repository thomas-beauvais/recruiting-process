package com.redhat.consulting.recruiting.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InterviewEvent implements Serializable {
	@ManyToOne
	private Interview interview;
	
	@ManyToOne
	private Employee employee;
	
	@Id
	@GeneratedValue
	private long id;

	public InterviewEvent() {
		
	}
	
	public InterviewEvent(Employee interviewer, Interview interview) {
		this.employee = interviewer;
		this.interview = interview;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
}

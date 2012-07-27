package com.redhat.consulting.recruiting.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: tbeauvais
 * Date: 7/26/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Candidate extends Person {
	@ManyToOne( targetEntity = Interview.class )
	private List<Interview> interview;

	public List<Interview> getInterview() {
		return interview;
	}

	public void setInterview(List<Interview> interview) {
		this.interview = interview;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((interview == null) ? 0 : interview.hashCode());
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
		Candidate other = (Candidate) obj;
		if (interview == null) {
			if (other.interview != null)
				return false;
		} else if (!interview.equals(other.interview))
			return false;
		return true;
	}
	
}

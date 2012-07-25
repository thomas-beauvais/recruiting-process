package org.redhat.consulting.recruiting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tbeauvais
 * Date: 7/24/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class InterviewEvent {

	@Id
    @GeneratedValue
    private long id;
	
    @OneToOne
    private Candidate candidate;

    @ManyToMany
    private List<Employee> employees;

    public long getId() {
    	return this.id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }
    
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

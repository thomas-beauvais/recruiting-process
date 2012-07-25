package org.redhat.consulting.recruiting.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tbeauvais
 * Date: 7/24/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Employee extends Person {
    @ManyToMany( mappedBy = "employees" )
    private List<InterviewEvent> interviewEvents;

    public List<InterviewEvent> getInterviewEvents() {
        return interviewEvents;
    }

    public void setInterviewEvents(List<InterviewEvent> interviewEvents) {
        this.interviewEvents = interviewEvents;
    }
}

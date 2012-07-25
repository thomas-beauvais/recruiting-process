package org.redhat.consulting.recruiting.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: tbeauvais
 * Date: 7/24/12
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Candidate extends Person {
    @OneToOne
    private InterviewEvent interviewEvent;

    public InterviewEvent getInterviewEvent() {
        return interviewEvent;
    }

    public void setInterviewEvent(InterviewEvent interviewEvent) {
        this.interviewEvent = interviewEvent;
    }
}

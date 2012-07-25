package org.redhat.consulting.recruiting.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.redhat.consulting.recruiting.model.Person;
import org.redhat.consulting.recruiting.service.MemberRegistration;
import org.richfaces.cdi.push.Push;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberController {

   public static final String PUSH_CDI_TOPIC = "pushCdi";

   @Inject
   private FacesContext facesContext;

   @Inject
   private MemberRegistration memberRegistration;

   @Inject
   @Push(topic = PUSH_CDI_TOPIC) Event<String> pushEvent;

   private Person newMember;
   private Person member;

   @Produces
   @Named
   public Person getNewMember() {
      return newMember;
   }

   @Produces
   @Named
   public Person getMember() {
      return member;
   }

   public void setMember(Person member) {
      this.member = member;
   }

   public void register() throws Exception {
      memberRegistration.register(newMember);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      pushEvent.fire(String.format("New member added: %s (id: %d)", newMember.getName(), newMember.getId()));
      initNewMember();
   }

   @PostConstruct
   public void initNewMember() {
      newMember = new Person();
   }
}

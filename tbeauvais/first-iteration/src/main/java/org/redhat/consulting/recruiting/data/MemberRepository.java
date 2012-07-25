package org.redhat.consulting.recruiting.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import org.redhat.consulting.recruiting.model.Person;

@ApplicationScoped
public class MemberRepository {

   @Inject
   private EntityManager em;

   public Person findById(Long id) {
      return em.find(Person.class, id);
   }

   public Person findByEmail(String email) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
      Root<Person> member = criteria.from(Person.class);
      // Swap criteria statements if you would like to try out type-safe criteria queries, a new
      // feature in JPA 2.0
      // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
      criteria.select(member).where(cb.equal(member.get("email"), email));
      return em.createQuery(criteria).getSingleResult();
   }

   public List<Person> findAllOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
      Root<Person> member = criteria.from(Person.class);
      // Swap criteria statements if you would like to try out type-safe criteria queries, a new
      // feature in JPA 2.0
      // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
      criteria.select(member).orderBy(cb.asc(member.get("name")));
      return em.createQuery(criteria).getResultList();
   }
}

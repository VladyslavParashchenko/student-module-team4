/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package sumdu.team4_project.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sumdu.team4_project.web.Student;

@Stateless
public class StudentBean {

    private static final Logger logger =
            Logger.getLogger("sumdu.team4_project.ejb.ejb.StudentBean");

    @PersistenceContext
    private EntityManager em;

   
//    public void saveNewStudent(String name, int age) {
//        StudentEntity entity = new StudentEntity(name, age);
//        em.persist(entity);
//    }
//
//
//    public List<Student> getAllStudents () {
//        Query query = em.createNamedQuery("AllStudents");
//        return query.getResultList();
//    }
//
//    public List<StudentEntity> getPotentialStudents (int subjectId, int lectorId) {
//        Query query = em.createNamedQuery("AllStudents");
//        return query.getResultList();
//    }
}

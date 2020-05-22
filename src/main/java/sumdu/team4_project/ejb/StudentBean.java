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
import javax.transaction.Transactional;

import sumdu.team4_project.entity.CourseEntity;
import sumdu.team4_project.entity.EmployeeEntity;
import sumdu.team4_project.entity.StudentEntity;
import sumdu.team4_project.web.Student;

@Stateless
public class StudentBean {

    private static final Logger logger =
            Logger.getLogger("sumdu.team4_project.ejb.ejb.StudentBean");

    @PersistenceContext
    private EntityManager em;

    public List<StudentEntity> getConnectedStudents (CourseEntity courseEntity) {
        Query query = em
                .createNamedQuery("StudentEntity.CourseStudent")
                .setParameter("course", courseEntity);

        return query.getResultList();
    }

    @Transactional
    public List<StudentEntity> getPotentialStudents (CourseEntity courseEntity) {
        Query query = em
                .createNamedQuery("StudentEntity.PotentialStudent", StudentEntity.class)
                .setParameter(1, courseEntity.getId());

        return query.getResultList();
    }

    @Transactional
    public void addStudentToCourse (CourseEntity courseEntity, List<StudentEntity> studentEntities)  {
        for(StudentEntity student: studentEntities) {
            student.getCourses().add(courseEntity);
            em.merge(student);
        }
        courseEntity.getCourseParticipants().addAll(studentEntities);
        em.merge(courseEntity);
    }

    @Transactional
    public void removeStudentsFromCourse(CourseEntity subject, List<StudentEntity> studentsForRemove) {
        for(StudentEntity studentEntity:studentsForRemove) {
            em
                    .createNamedQuery("StudentEntity.RmCources")
                    .setParameter(1, studentEntity.getPersonInfo().getId())
                    .setParameter(2, subject.getId())
                    .executeUpdate();
        }
    }
}

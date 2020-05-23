package sumdu.team4_project.ejb;

import sumdu.team4_project.entity.CourseEntity;
import sumdu.team4_project.entity.EmployeeEntity;
import sumdu.team4_project.entity.Responsibility;
import sumdu.team4_project.entity.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    private EntityManager em;

    public CourseEntity find(Long id) {
        return em.find(CourseEntity.class, id);
    }

    public List<EmployeeEntity> getPotentialEmployee(Long courseId) {
        Query query = em
                .createNamedQuery("Employee.potentialForCourse", StudentEntity.class)
                .setParameter(1, courseId);

        return query.getResultList();
    }

    public void addEmployeeToCourse(CourseEntity course, EmployeeEntity employee, String role) {
        List<Responsibility> responsibilities = course.getEmployeesRespForCourse();
        Responsibility responsibility = new Responsibility(role, employee, course);
        responsibilities.add(responsibility);
        em.persist(responsibility);
        em.merge(course);
    }

    @Transactional
    public CourseEntity removeEmployeeFromCourse(CourseEntity course, Long id) {
        em
                .createNamedQuery("Responsibility.RmById")
                .setParameter("id", id)
                .executeUpdate();

        return find(course.getId());
    }
}

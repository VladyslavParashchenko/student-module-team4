package sumdu.team4_project.ejb;

import sumdu.team4_project.entity.CourseEntity;
import sumdu.team4_project.entity.EmployeeEntity;
import sumdu.team4_project.entity.Responsibility;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return em.createNamedQuery("Employee.potentialForCourse").getResultList();
    }

    public void addEmployeeToCourse(CourseEntity course, List<EmployeeEntity> employeeEntities) {
        List<Responsibility> responsibilities = course.getEmployeesRespForCourse();
        for (EmployeeEntity entity : employeeEntities) {
            Responsibility responsibility = new Responsibility("", entity, course);
            responsibilities.add(responsibility);
            em.persist(responsibility);
        }
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

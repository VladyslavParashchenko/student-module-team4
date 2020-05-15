package sumdu.team4_project.ejb;


import sumdu.team4_project.entity.CourseEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SubjectBean {

    @PersistenceContext
    private EntityManager em;

    public List<CourseEntity> getCourses () {
        return em.createNamedQuery("CourseEntity.getAll").getResultList();
    }
}

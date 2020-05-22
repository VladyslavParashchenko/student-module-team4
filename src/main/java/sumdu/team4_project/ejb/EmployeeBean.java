package sumdu.team4_project.ejb;

import sumdu.team4_project.entity.EmployeeEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmployeeBean {

    @PersistenceContext
    private EntityManager em;

    public EmployeeEntity find(Long id) {
        return em.find(EmployeeEntity.class, id);
    }
}

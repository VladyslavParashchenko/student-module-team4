package sumdu.team4_project.web;

import sumdu.team4_project.ejb.EntityGeneratorBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@SessionScoped
public class EntityGenerator implements Serializable {

    @EJB
    EntityGeneratorBean generatorBean;

    public void generateEntities () {
        generatorBean.generateEntitites();
    }
}

package sumdu.team4_project.web;

import sumdu.team4_project.ejb.CourseBean;
import sumdu.team4_project.ejb.EmployeeBean;
import sumdu.team4_project.entity.CourseEntity;
import sumdu.team4_project.entity.EmployeeEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class CourseEmployee implements Serializable {

    @EJB
    CourseBean courseBean;

    @EJB
    EmployeeBean employeeBean;

    private CourseEntity course;
    private Long newEmployeeId;
    private String newEmployeeRole;

    private List<SelectItem> potentialEmployeeItems;
    private String employeeForRemove;

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        if(params.containsKey("course_id")) {
            Long courseId = Long.parseLong(params.get("course_id"));
            setCourse(courseBean.find(courseId));
            setPotentialEmployeeItems(courseBean.getPotentialEmployee(courseId));
        }
    }

    public String addEmployeeToSubject () {
        EmployeeEntity employeeEntity = employeeBean.find(newEmployeeId);
        courseBean.addEmployeeToCourse(course, employeeEntity, newEmployeeRole);
        return "index.xhtml?faces-redirect=true";
    }

    public void removeEmployee(String id) {
        course = courseBean.removeEmployeeFromCourse(course, Long.parseLong(id));
    }

    public String getEmployeeForRemove() {
        return employeeForRemove;
    }

    public void setEmployeeForRemove(String employeeForRemove) {
        this.employeeForRemove = employeeForRemove;
    }

    public Long getNewEmployeeId() {
        return newEmployeeId;
    }

    public void setNewEmployeeId(Long newEmployeeId) {
        this.newEmployeeId = newEmployeeId;
    }

    public String getNewEmployeeRole() {
        return newEmployeeRole;
    }

    public void setNewEmployeeRole(String newEmployeeRole) {
        this.newEmployeeRole = newEmployeeRole;
    }

    public List<SelectItem> getPotentialEmployeeItems() {

        return potentialEmployeeItems;
    }

    public void setPotentialEmployeeItems(List<EmployeeEntity> potentialEmployee) {
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for(EmployeeEntity emp:potentialEmployee) {
            items.add(new SelectItem(emp.getPersonInfo().getId(), emp.toString()));
        }
        this.potentialEmployeeItems = items;
    }
}

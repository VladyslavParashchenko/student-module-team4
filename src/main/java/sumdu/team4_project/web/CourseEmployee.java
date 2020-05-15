package sumdu.team4_project.web;

import sumdu.team4_project.ejb.CourseBean;
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

    private CourseEntity course;
    private List<EmployeeEntity> newEmployee;
    private List<EmployeeEntity> potentialEmployee;
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
            potentialEmployee = courseBean.getPotentialEmployee(courseId);
        }
    }

    public List<EmployeeEntity> getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(List<EmployeeEntity> newEmployee) {
        this.newEmployee = newEmployee;
    }

    public List<EmployeeEntity> getPotentialEmployee() {
        return potentialEmployee;
    }

    public void setPotentialEmployee (List<EmployeeEntity> potentialEmployee) {
        this.potentialEmployee = potentialEmployee;
    }

    public String addEmployeeToSubject () {
        courseBean.addEmployeeToCourse(course, newEmployee);
        return "index.xhtml";
    }

    public void removeEmployee(String id) {
        course = courseBean.removeEmployeeFromCourse(course, Long.parseLong(id));
    }

    private List<SelectItem> transformStudentForCheckbox(List<EmployeeEntity> employee) {
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        SelectItemGroup group = new SelectItemGroup();
        for (EmployeeEntity employeeEntity : employee) {
            items.add(
                    new SelectItem(
                            employeeEntity,
                            employeeEntity.getPersonInfo().getName()
                    ));
        }
        group.setSelectItems(items.toArray(new SelectItem[items.size()]));

        List<SelectItem> list = new ArrayList<SelectItem>();
        list.add(group);

        return list;
    }

    public String getEmployeeForRemove() {
        return employeeForRemove;
    }

    public void setEmployeeForRemove(String employeeForRemove) {
        this.employeeForRemove = employeeForRemove;
    }
}

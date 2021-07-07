package sumdu.team4_project.web;

import sumdu.team4_project.ejb.CourseBean;
import sumdu.team4_project.ejb.EmployeeBean;
import sumdu.team4_project.ejb.StudentBean;
import sumdu.team4_project.entity.CourseEntity;
import sumdu.team4_project.entity.EmployeeEntity;
import sumdu.team4_project.entity.StudentEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class SubjectStudent implements Serializable {

    @EJB
    private StudentBean studentBean;

    @EJB
    private CourseBean courseBean;

    @EJB
    private EmployeeBean employeeBean;


    private CourseEntity subject;
    private List<StudentEntity> newStudents;
    private List<StudentEntity> studentsForRemove;
    private List<StudentEntity> potentialStudents;
    private List<StudentEntity> connectedStudents;

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
    }

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(CourseBean courseBean) {
        this.courseBean = courseBean;
    }

    public EmployeeBean getEmployeeBean() {
        return employeeBean;
    }

    public void setEmployeeBean(EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;
    }

    public CourseEntity getSubject() {
        return subject;
    }

    public void setSubject(CourseEntity subject) {
        this.subject = subject;
    }

    public void setPotentialStudents(List<StudentEntity> potentialStudents) {
        this.potentialStudents = potentialStudents;
    }

    public void setConnectedStudents(List<StudentEntity> connectedStudents) {
        this.connectedStudents = connectedStudents;
    }

    public List<StudentEntity> getPotentialStudents() {
        return potentialStudents;
    }

    public List<StudentEntity> getNewStudents() {
        return newStudents;
    }

    public void setNewStudents(List<StudentEntity> newStudents) {
        this.newStudents = newStudents;
    }

    public List<StudentEntity> getStudentsForRemove() {
        return studentsForRemove;
    }

    public void setStudentsForRemove(List<StudentEntity> studentsForRemove) {
        this.studentsForRemove = studentsForRemove;
    }

    public List<StudentEntity> getConnectedStudents() {
        return connectedStudents;
    }

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();

        Long subjectId = Long.parseLong(params.get("course_id"));

        subject = courseBean.find(subjectId);

        connectedStudents = studentBean.getConnectedStudents(subject);
        potentialStudents = studentBean.getPotentialStudents(subject);
    }

    public String addStudents() {
        studentBean.addStudentToCourse(subject, newStudents);
        return "index.xhtml?faces-redirect=true";
    }

    public String removeStudents() {
        studentBean.removeStudentsFromCourse(subject, studentsForRemove);
        return "index.xhtml?faces-redirect=true";
    }
}

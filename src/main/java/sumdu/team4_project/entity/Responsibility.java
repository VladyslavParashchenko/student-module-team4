package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course_emp")
public class Responsibility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "responsibility")
    private String responsibilityDescr;


    @Id
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee respPerson;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course courseRespFor;



    public Responsibility() {
    }

    public Responsibility(String responsibilityDescr, Employee respPerson, Course courseRespFor) {
        this.responsibilityDescr = responsibilityDescr;
        this.respPerson = respPerson;
        this.courseRespFor = courseRespFor;
    }

    public String getResponsibilityDescr() {
        return responsibilityDescr;
    }

    public void setResponsibilityDescr(String responsibilityDescr) {
        this.responsibilityDescr = responsibilityDescr;
    }

    public Employee getRespPerson() {
        return respPerson;
    }

    public void setRespPerson(Employee respPerson) {
        this.respPerson = respPerson;
    }

    public Course getCourseRespFor() {
        return courseRespFor;
    }

    public void setCourseRespFor(Course courseRespFor) {
        this.courseRespFor = courseRespFor;
    }
}

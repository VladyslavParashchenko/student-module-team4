package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course_emp", schema = "dbo")
@NamedQueries({
        @NamedQuery(name="Responsibility.RmById",
                query="DELETE from Responsibility  r where r.id =:id")})
@Cacheable(false)
public class Responsibility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "responsibility")
    private String responsibilityDescr;

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private EmployeeEntity respPerson;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private CourseEntity courseRespFor;



    public Responsibility() {
    }

    public Responsibility(String responsibilityDescr, EmployeeEntity respPerson, CourseEntity courseRespFor) {
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

    public EmployeeEntity getRespPerson() {
        return respPerson;
    }

    public void setRespPerson(EmployeeEntity respPerson) {
        this.respPerson = respPerson;
    }

    public CourseEntity getCourseRespFor() {
        return courseRespFor;
    }

    public void setCourseRespFor(CourseEntity courseRespFor) {
        this.courseRespFor = courseRespFor;
    }
}

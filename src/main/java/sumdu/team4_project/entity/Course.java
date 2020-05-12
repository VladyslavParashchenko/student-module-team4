package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "courses", schema = "dbo")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;



    @ManyToOne
    @JoinColumn(name = "base_for_course", referencedColumnName = "dep_id")
    private Department baseForCourse;

    @OneToMany(mappedBy = "courseRespFor")
    private List<Responsibility> employeesRespForCourse;

    @ManyToMany(mappedBy = "courses")
    private List<Student> courseParticipants;



    public Course() {
    }

    public Course(String title, String description, Department baseForCourse) {
        this.title = title;
        this.description = description;
        this.baseForCourse = baseForCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getBaseForCourse() {
        return baseForCourse;
    }

    public void setBaseForCourse(Department baseForCourse) {
        this.baseForCourse = baseForCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Responsibility> getEmployeesRespForCourse() {
        return employeesRespForCourse;
    }

    public void setEmployeesRespForCourse(List<Responsibility> employeesRespForCourse) {
        this.employeesRespForCourse = employeesRespForCourse;
    }
}

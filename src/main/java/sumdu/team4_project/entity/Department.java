package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments", schema = "dbo")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dep_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String depName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_dep_id", referencedColumnName = "dep_id")
    private Department parentDep;



    @OneToMany(mappedBy = "parentDep")
    private List<Department> childDeps;

    @OneToMany(mappedBy = "group")
    private List<Student> studentsInGroup;

    @OneToMany(mappedBy = "workPlace")
    private List<Position> employees;

    @OneToMany(mappedBy = "baseForCourse")
    private List<Course> courses;



    public Department() {
    }

    public Department(String depName, String description) {
        this.depName = depName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getParentDep() {
        return parentDep;
    }

    public void setParentDep(Department parentDep) {
        this.parentDep = parentDep;
    }

    public List<Department> getChildDeps() {
        return childDeps;
    }

    public void setChildDeps(List<Department> childDeps) {
        this.childDeps = childDeps;
    }

    public List<Student> getStudentsInGroup() {
        return studentsInGroup;
    }

    public void setStudentsInGroup(List<Student> studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
    }

    public List<Position> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Position> employees) {
        this.employees = employees;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

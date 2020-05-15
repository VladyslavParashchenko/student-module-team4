package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments", schema = "dbo")
@Cacheable(false)
public class DepartmentEntity implements Serializable {

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
    private DepartmentEntity parentDep;



    @OneToMany(mappedBy = "parentDep")
    private List<DepartmentEntity> childDeps;

    @OneToMany(mappedBy = "group")
    private List<StudentEntity> studentsInGroup;

    @OneToMany(mappedBy = "workPlace")
    private List<Position> employees;

    @OneToMany(mappedBy = "baseForCourse")
    private List<CourseEntity> courses;



    public DepartmentEntity() {
    }

    public DepartmentEntity(String depName, String description) {
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

    public DepartmentEntity getParentDep() {
        return parentDep;
    }

    public void setParentDep(DepartmentEntity parentDep) {
        this.parentDep = parentDep;
    }

    public List<DepartmentEntity> getChildDeps() {
        return childDeps;
    }

    public void setChildDeps(List<DepartmentEntity> childDeps) {
        this.childDeps = childDeps;
    }

    public List<StudentEntity> getStudentsInGroup() {
        return studentsInGroup;
    }

    public void setStudentsInGroup(List<StudentEntity> studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
    }

    public List<Position> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Position> employees) {
        this.employees = employees;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}

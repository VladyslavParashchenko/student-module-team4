package sumdu.team4_project.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students", schema = "dbo")
@Cacheable(false)
@NamedQueries({
        @NamedQuery(name = "StudentEntity.CourseStudent",
                query = "SELECT s from StudentEntity s JOIN fetch s.courses c" +
                        " where c =:course")}

)
@NamedNativeQueries({
        @NamedNativeQuery(
                resultClass = StudentEntity.class,
                name = "StudentEntity.PotentialStudent",
                query = "select * from students s left join course_student c on s.student_id=c.student_id and c.course_id= ? " +
                        " where c.course_id is null"),
        @NamedNativeQuery(
                name = "StudentEntity.RmCources",
                query = "DELETE from course_student where student_id = ? and course_id = ?")
})
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "program")
    private String studyProgram;

    @Id
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "person_id")
    private PersonEntity personInfo;

    @ManyToOne
    @JoinColumn(name = "belong_to_group", referencedColumnName = "dep_id")
    private DepartmentEntity group;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "course_id")})
    private List<CourseEntity> courses;


    public StudentEntity() {
    }

    public StudentEntity(String studyProgram, PersonEntity personInfo, DepartmentEntity group) {
        this.studyProgram = studyProgram;
        this.personInfo = personInfo;
        this.group = group;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public PersonEntity getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonEntity personInfo) {
        this.personInfo = personInfo;
    }

    public DepartmentEntity getGroup() {
        return group;
    }

    public void setGroup(DepartmentEntity group) {
        this.group = group;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return this.personInfo.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        return that.getPersonInfo().getId() == this.getPersonInfo().getId();
    }

    @Override
    public int hashCode() {
        int result = studyProgram != null ? studyProgram.hashCode() : 0;
        result = 31 * result + (personInfo != null ? personInfo.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }
}

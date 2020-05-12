package sumdu.team4_project.entity;


import org.eclipse.persistence.annotations.PrimaryKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students", schema = "dbo")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "program")
    private String studyProgram;



    @Id
    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "person_id")
    private Person personInfo;

    @ManyToOne
    @JoinColumn(name = "belong_to_group", referencedColumnName = "dep_id")
    private Department group;

    @ManyToMany
    @JoinTable(name = "course_student",
               joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "student_id")},
               inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "course_id")})
    private List<Course> courses;



    public Student() {
    }

    public Student(String studyProgram, Person personInfo, Department group) {
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

    public Person getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(Person personInfo) {
        this.personInfo = personInfo;
    }

    public Department getGroup() {
        return group;
    }

    public void setGroup(Department group) {
        this.group = group;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

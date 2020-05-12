package sumdu.team4_project.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees", schema = "dbo")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "skills")
    private String skills;

    @Column(name = "characteristics")
    private String characteristics;



    @Id
    @OneToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "person_id")
    private Person personInfo;

    @OneToMany(mappedBy = "worker")
    private List<Position> positions;

    @OneToMany(mappedBy = "respPerson")
    private List<Responsibility> coursesRespFor;



    public Employee() {
    }

    public Employee(String skills, String characteristics, Person personInfo) {
        this.skills = skills;
        this.characteristics = characteristics;
        this.personInfo = personInfo;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Person getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(Person personInfo) {
        this.personInfo = personInfo;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<Responsibility> getCoursesRespFor() {
        return coursesRespFor;
    }

    public void setCoursesRespFor(List<Responsibility> coursesRespFor) {
        this.coursesRespFor = coursesRespFor;
    }
}

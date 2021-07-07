package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "persons", schema = "dbo")
@Cacheable(false)
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;



    @OneToOne(mappedBy = "personInfo")
    private StudentEntity studentEntity;

    @OneToOne(mappedBy = "personInfo")
    private EmployeeEntity employee;



    public PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName () {
        return this.firstName + " " + this.lastName;
    }
}

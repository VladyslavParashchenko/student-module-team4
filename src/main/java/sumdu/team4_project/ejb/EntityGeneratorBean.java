package sumdu.team4_project.ejb;

import com.github.javafaker.Faker;
import sumdu.team4_project.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EntityGeneratorBean {
    @PersistenceContext
    private EntityManager em;
    private Faker faker;

    public void generateEntitites() {
        faker = new Faker();

        DepartmentEntity faculty = new DepartmentEntity();
        faculty.setDepName("ELIT");
        em.persist(faculty);

        DepartmentEntity group1 = generateGroup(faculty);
        DepartmentEntity group2 = generateGroup(faculty);
        em.persist(group1);
        em.persist(group2);

        List<StudentEntity> students = new ArrayList<StudentEntity>();
        for (int i = 0; i < 10; i++) {
            PersonEntity personEntity = generatePerson();
            em.persist(personEntity);
            DepartmentEntity group = i % 2 == 0 ? group1 : group2;
            StudentEntity studentEntity = generateStudent(group, personEntity);
            em.persist(studentEntity);

            students.add(studentEntity);
        }

        List<CourseEntity> courses = new ArrayList<CourseEntity>();
        for (int i = 0; i < 10; i++) {
            CourseEntity c = generateCourse(faculty, i);
            em.persist(c);
            courses.add(c);
        }

        List<EmployeeEntity> employers = new ArrayList<EmployeeEntity>();
        for (int i = 0; i < 10; i++) {
            PersonEntity personEntity = generatePerson();
            em.persist(personEntity);
            EmployeeEntity employee = generateEmptyee(personEntity);
            em.persist(employee);

            employers.add(employee);
        }


    }

    private EmployeeEntity generateEmptyee(PersonEntity personEntity) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setPersonInfo(personEntity);

        return employee;
    }


    private PersonEntity generatePerson() {
        PersonEntity person = new PersonEntity();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setAge(faker.number().numberBetween(18, 45));

        return person;
    }

    private DepartmentEntity generateGroup(DepartmentEntity faculty) {
        DepartmentEntity group = new DepartmentEntity();
        group.setDepName("IH-" + faker.random().nextInt(20, 50));
        group.setDescription("Group IH-" + faker.random().nextInt(20, 50));
        group.setParentDep(faculty);

        return group;
    }

    private StudentEntity generateStudent(DepartmentEntity group, PersonEntity person) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setGroup(group);
        studentEntity.setPersonInfo(person);

        return studentEntity;
    }

    private CourseEntity generateCourse(DepartmentEntity faculty, int index) {
        index += 1;
        CourseEntity courseEntity = new CourseEntity(
                "Subject #" + index,
                faker.lorem().paragraph(),
                faculty
        );
        courseEntity.setImage(index + ".jpg");

        return courseEntity;
    }
}

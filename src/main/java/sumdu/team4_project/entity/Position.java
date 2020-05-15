package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dep_emp", schema = "dbo")
@Cacheable(false)
public class Position implements Serializable {


    private static final long serialVersionUID = 1L;

    @Column(name = "position")
    private String positionDescription;



    @Id
    @ManyToOne
    @JoinColumn(name = "dep_id", referencedColumnName = "dep_id")
    private DepartmentEntity workPlace;

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private EmployeeEntity worker;



    public Position() {
    }

    public Position(DepartmentEntity workPlace, EmployeeEntity worker, String positionDescription) {
        this.workPlace = workPlace;
        this.worker = worker;
        this.positionDescription = positionDescription;
    }

    public DepartmentEntity getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(DepartmentEntity workPlace) {
        this.workPlace = workPlace;
    }

    public EmployeeEntity getWorker() {
        return worker;
    }

    public void setWorker(EmployeeEntity worker) {
        this.worker = worker;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}

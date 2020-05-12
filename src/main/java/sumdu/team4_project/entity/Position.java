package sumdu.team4_project.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dep_emp", schema = "dbo")
public class Position implements Serializable {


    private static final long serialVersionUID = 1L;

    @Column(name = "position")
    private String positionDescription;



    @Id
    @ManyToOne
    @JoinColumn(name = "dep_id", referencedColumnName = "dep_id")
    private Department workPlace;

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee worker;



    public Position() {
    }

    public Position(Department workPlace, Employee worker, String positionDescription) {
        this.workPlace = workPlace;
        this.worker = worker;
        this.positionDescription = positionDescription;
    }

    public Department getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Department workPlace) {
        this.workPlace = workPlace;
    }

    public Employee getWorker() {
        return worker;
    }

    public void setWorker(Employee worker) {
        this.worker = worker;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}

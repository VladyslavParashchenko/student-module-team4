package sumdu.team4_project.web;

import sumdu.team4_project.ejb.StudentBean;
import sumdu.team4_project.entity.LectorEntity;
import sumdu.team4_project.entity.StudentEntity;
import sumdu.team4_project.entity.StudentEntity_;
import sumdu.team4_project.entity.SubjectEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class SubjectStudentLector implements Serializable {

    @EJB
    private StudentBean studentBean;

    private LectorEntity lector;
    private SubjectEntity subject;
    private String[] newStudents;
    private String[] studentsForRemove;
    private List<SelectItem> potentialStudents;

    private List<SelectItem> connectedStudents;


    public List<SelectItem> getPotentialStudents() {
        return potentialStudents;
    }

    public String[] getNewStudents() {
        return newStudents;
    }

    public void setNewStudents(String[] newStudents) {
        this.newStudents = newStudents;
    }

    public String[] getStudentsForRemove() {
        return studentsForRemove;
    }

    public void setStudentsForRemove(String[] studentsForRemove) {
        this.studentsForRemove = studentsForRemove;
    }

    public List<SelectItem> getConnectedStudents() {
        return connectedStudents;
    }

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();

        int lectorId = Integer.parseInt(params.get("lector"));
        lector = new LectorEntity();
        lector.setId(lectorId);
        fc.getExternalContext().getRequestParameterMap();

        int subjectId = Integer.parseInt(params.get("subject"));
        subject = new SubjectEntity();
        subject.setId(subjectId);

        List<StudentEntity> students = studentBean.getPotentialStudents(0, 0);
        potentialStudents = transformStudentForCheckbox(students);
        connectedStudents = transformStudentForCheckbox(new ArrayList<StudentEntity>());

    }


    public LectorEntity getLector() {
        return lector;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public String addStudents() {
        return "index.xhtml";
    }

    public String removeStudents() {
        return "index.xhtml";
    }

    private List<SelectItem> transformStudentForCheckbox(List<StudentEntity> students) {
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        SelectItemGroup group = new SelectItemGroup();
        for (StudentEntity student : students) {
            items.add(new SelectItem(student.getId(), student.getName()));
        }
        group.setSelectItems(items.toArray(new SelectItem[items.size()]));

        List<SelectItem> list = new ArrayList<SelectItem>();
        list.add(group);

        return list;
    }

}

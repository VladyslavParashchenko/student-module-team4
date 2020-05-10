package sumdu.team4_project.web;

import sumdu.team4_project.entity.SubjectEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class Subject implements Serializable {

    private ArrayList<SubjectEntity> subjects;

    public Subject() {

    }

    public ArrayList<SubjectEntity> getSubjects() {
        this.subjects = new ArrayList<SubjectEntity>();
        this.subjects.add(new SubjectEntity());
        this.subjects.add(new SubjectEntity());
        this.subjects.add(new SubjectEntity());
        this.subjects.add(new SubjectEntity());
        return subjects;
    }

    public void setSubjects(ArrayList<SubjectEntity> subjects) {

        this.subjects = subjects;
    }
}

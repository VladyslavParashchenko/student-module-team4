package sumdu.team4_project.web;

import sumdu.team4_project.ejb.SubjectBean;
import sumdu.team4_project.entity.CourseEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class Subject implements Serializable {

    @EJB
    SubjectBean subjectBean;
    private List<CourseEntity> courses;

    public Subject() {
    }

    @PostConstruct
    public void init() {
        this.courses = subjectBean.getCourses();
    }


    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}

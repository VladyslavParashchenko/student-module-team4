/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/firstcup-examples/LICENSE.txt
 */
package sumdu.team4_project.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import sumdu.team4_project.ejb.StudentBean;

@Named
@RequestScoped
public class Student implements Serializable {

    @EJB
    private StudentBean studentBean;
    
    protected String studentName;
    protected int studentAge; 

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
   
    private static final Logger logger 
            = Logger.getLogger("com.vladyslav_parashchenko.dogs.web.DukesBDay");

    public Student() {
    }
    
    public String saveStudent () {
        studentBean.saveNewStudent(this.studentName, this.studentAge);
        
        return "/students.xthml";
    }
    
    public List<Student> getStudents () {
        return studentBean.getAllStudents();
    }
}

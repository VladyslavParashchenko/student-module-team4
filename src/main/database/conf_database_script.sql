if(db_id(N'University') is NULL)
    begin
        create database University
    end;

use University;

if(object_id(N'Persons') is NULL)
    begin
        CREATE TABLE Persons (
	        Id                   int NOT NULL   IDENTITY ,
	        Age                  int     ,
	        FirstName            varchar(100)     ,
	        LastName             varchar(100)     ,
	        CONSTRAINT Pk_Person_Id PRIMARY KEY  ( Id ))
    end;


if(object_id(N'Departments') is NULL)
    begin
        CREATE TABLE Departments (
	        Id                   int NOT NULL   IDENTITY ,
	        Name                 varchar(100)     ,
	        Description          varchar(max)     ,
	        ParentDep            int     ,
	        CONSTRAINT Pk_Departments_Id PRIMARY KEY  ( Id ))
        ALTER TABLE Departments ADD CONSTRAINT fk_departments_departments FOREIGN KEY ( ParentDep ) REFERENCES Departments( Id ) ON DELETE NO ACTION ON UPDATE NO ACTION
    end;

if(object_id(N'Courses') is NULL)
    begin
        CREATE TABLE Courses (
	        CourseId             int NOT NULL   IDENTITY ,
	        Title                varchar(100) NOT NULL    ,
	        Description          varchar(max)     ,
	        BelongToDep          int              ,
	        CONSTRAINT Pk_Course_CourseId PRIMARY KEY  ( CourseId ))
        ALTER TABLE Courses ADD CONSTRAINT fk_course_departments FOREIGN KEY ( BelongToDep ) REFERENCES Departments( Id ) ON DELETE SET NULL ON UPDATE CASCADE
    end;


if(object_id(N'Employees') is NULL)
    begin
        CREATE TABLE Employees (
	        Person_id            int NOT NULL    ,
	        Skills               varchar(200)     ,
	        Characteristics      varchar(max)     ,
	        CONSTRAINT Pk_Employee_Person_id PRIMARY KEY  ( Person_id ))
        ALTER TABLE Employees ADD CONSTRAINT Fk_Employees_Person FOREIGN KEY ( Person_id ) REFERENCES Persons( Id ) ON DELETE CASCADE ON UPDATE CASCADE
    end;


if(object_id(N'Students') is NULL)
    begin
        CREATE TABLE Students (
	        Person_id            int NOT NULL    ,
	        StudyProgram         varchar(100)     ,
	        BelongToGroup        int     ,
	        CONSTRAINT Pk_Students_Person_id PRIMARY KEY  ( Person_id ))
        ALTER TABLE Students ADD CONSTRAINT fk_students_departments FOREIGN KEY ( BelongToGroup ) REFERENCES Departments( Id ) ON DELETE NO ACTION ON UPDATE NO ACTION
        ALTER TABLE Students ADD CONSTRAINT Fk_Students_Person FOREIGN KEY ( Person_id ) REFERENCES Persons( Id ) ON DELETE CASCADE ON UPDATE CASCADE
    end;

if(object_id(N'CourseParticipants') is NULL)
    begin
        CREATE TABLE CourseParticipants (
	        CourseId             int NOT NULL    ,
	        StudentId            int NOT NULL    ,
	        CONSTRAINT Idx_CourseParticipants PRIMARY KEY  ( CourseId, StudentId ))
        ALTER TABLE CourseParticipants ADD CONSTRAINT fk_courseparticipants_course FOREIGN KEY ( CourseId ) REFERENCES Courses( CourseId ) ON DELETE CASCADE ON UPDATE CASCADE
        ALTER TABLE CourseParticipants ADD CONSTRAINT fk_courseparticipants_students FOREIGN KEY ( StudentId ) REFERENCES Students( Person_id ) ON DELETE CASCADE ON UPDATE CASCADE
    end;


if(object_id(N'CourseWorkers') is NULL)
    begin
        CREATE TABLE CourseWorkers (
	        CourseId             int NOT NULL    ,
	        EmpId                int NOT NULL    ,
	        DescriptionRespFor   varchar(max)     ,
	    CONSTRAINT CourseWorkerKey PRIMARY KEY  ( CourseId, EmpId ))
        ALTER TABLE CourseWorkers ADD CONSTRAINT fk_courseworker_course FOREIGN KEY ( CourseId ) REFERENCES Courses( CourseId ) ON DELETE CASCADE ON UPDATE CASCADE
        ALTER TABLE CourseWorkers ADD CONSTRAINT fk_courseworker_employees FOREIGN KEY ( EmpId ) REFERENCES Employees( Person_id ) ON DELETE CASCADE ON UPDATE CASCADE
    end;


if(object_id(N'DepEmp') is NULL)
    begin
        CREATE TABLE DepEmp (
	        DepId                int NOT NULL    ,
	        EmpId                int NOT NULL    ,
	        Position             varchar(100)     ,
	        CONSTRAINT DepEmpKey PRIMARY KEY  ( DepId, EmpId ))
        ALTER TABLE DepEmp ADD CONSTRAINT fk_depemp_departments FOREIGN KEY ( DepId ) REFERENCES Departments( Id ) ON DELETE CASCADE ON UPDATE CASCADE
        ALTER TABLE DepEmp ADD CONSTRAINT fk_depemp_employees FOREIGN KEY ( EmpId ) REFERENCES Employees( Person_id ) ON DELETE CASCADE ON UPDATE CASCADE
    end;



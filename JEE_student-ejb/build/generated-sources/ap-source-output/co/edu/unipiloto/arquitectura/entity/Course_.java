package co.edu.unipiloto.arquitectura.entity;

import co.edu.unipiloto.arquitectura.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-10-27T08:06:11")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, Integer> credits;
    public static volatile SingularAttribute<Course, Integer> courseCode;
    public static volatile CollectionAttribute<Course, Student> studentCollection;
    public static volatile SingularAttribute<Course, Integer> semester;
    public static volatile SingularAttribute<Course, Integer> admittedStudents;

}
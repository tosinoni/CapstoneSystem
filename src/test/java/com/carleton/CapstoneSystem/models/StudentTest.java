package com.carleton.CapstoneSystem.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StudentTest {
    @Test
    public void testStudentForEntityAttribute() {
        AssertAnnotations.assertType(Student.class, Entity.class, Inheritance.class);
    }

    @Test
    public void testProgram() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Student.class, "program",Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Student.class, "program", Column.class);
        assertEquals("column program:  name is not equal", "program", c.name());
        assertEquals("column program: nullable is false", true, c.nullable());

    }
    @Test
    public void testProjectAddition() {
        Student student = new Student();
        Project project = new Project();
        student.setProject(project);
        student.applyForProject(project);
        assertEquals(student.getProject(),project);

        assertEquals(student.appliedForProject(project),true);

    }



}

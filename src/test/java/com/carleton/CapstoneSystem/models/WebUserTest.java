package com.carleton.CapstoneSystem.models;


import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
public class WebUserTest {
    @Test
    public void testUserForEntityAttribute() {
        AssertAnnotations.assertType(WebUser.class, Entity.class);
    }

    @Test
    public void testId() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( WebUser.class, "id", Id.class, GeneratedValue.class, Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "id", Column.class);
        assertEquals("column id:  name is not equal", "id", c.name());
        assertEquals("column id: unique is false", true, c.unique());
        assertEquals("column id: nullable is true", false, c.nullable());



    }

    @Test
    public void testUserName() {
        AssertAnnotations.assertField( WebUser.class, "userName", Column.class);

        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "userName", Column.class);

        assertEquals("column userName:  userame is not equal", "user_name", c.name());
        assertEquals("column userName: unique is false", true, c.unique());
        assertEquals("column userName: nullable is true", false, c.nullable());

    }

    @Test
    public void testUserForEmail() {
        AssertAnnotations.assertField( WebUser.class, "email", Column.class,Email.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "email", Column.class);
        assertEquals("column email: nullable is true", false, c.nullable());
        assertEquals("column email: unique is false", true, c.unique());


    }
    @Test
    public void testUserForPassword() {
        AssertAnnotations.assertField( WebUser.class, "password", Column.class,Size.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "password", Column.class);
        Size s= ReflectTool.getFieldAnnotation(WebUser.class, "password", Size.class);
        assertEquals("column password: nullable is true", false, c.nullable());
        assertEquals("column password: size is not 4", 4, s.min());

    }

    @Test
    public void testUserForRole() {
        AssertAnnotations.assertField( WebUser.class, "role", Column.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "role", Column.class);
        assertEquals("column role: nullable is true", false, c.nullable());
    }
    @Test
    public void testUserForIdentificationNumber() {
        AssertAnnotations.assertField( WebUser.class, "identifier", Column.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "identifier", Column.class);
        assertEquals("column identifier: nullable is true", false, c.nullable());
        assertEquals("column identifier: unique is false", true, c.unique());
    }
    @Test
    public void testUserForFirstName() {
        AssertAnnotations.assertField( WebUser.class, "firstName", Column.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "firstName", Column.class);
        assertEquals("column firstName: nullable is true", false, c.nullable());
    }
    @Test
    public void testUserForLastName() {
        AssertAnnotations.assertField( WebUser.class, "lastName", Column.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "lastName", Column.class);
        assertEquals("column lastName: nullable is true", false, c.nullable());
    }
    @Test
    public void testUserForProgram() {
        AssertAnnotations.assertField( WebUser.class, "program", Column.class);
        Column c = ReflectTool.getFieldAnnotation(WebUser.class, "program", Column.class);
        assertEquals("column program: nullable is true", false, c.nullable());
    }


}

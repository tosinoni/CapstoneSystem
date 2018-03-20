package com.carleton.CapstoneSystem.models;

import org.junit.Test;

import javax.persistence.*;

import static org.junit.Assert.assertEquals;

public class ProjectTest {
    @Test
    public void testProjectForEntityAttribute() {
        AssertAnnotations.assertType(Project.class, Entity.class);
    }

    @Test
    public void testId() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Project.class, "id", Id.class, GeneratedValue.class, Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Project.class, "id", Column.class);
        assertEquals("column id:  name is not equal", "id", c.name());
        assertEquals("column id: unique is false", true, c.unique());
        assertEquals("column id: nullable is true", false, c.nullable());



    }

    @Test
    public void testProjectDescription() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Project.class, "description",Column.class,Lob.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Project.class, "description", Column.class);
        assertEquals("column description: nullable is true", false, c.nullable());



    }
    @Test
    public void testProjectName() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Project.class, "name",Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Project.class, "name", Column.class);
        assertEquals("column name: nullable is true", false, c.nullable());

    }



    @Test
    public void testProjectMaxCapacity() {
        testHelperNullFieldChecker("maxCapacity");



    }
    @Test
    public void testProjectMinCapacity() {
        testHelperNullFieldChecker("minCapacity");


    }
    @Test
    public void testProgramArchive() {
        testHelperNullFieldChecker("archive");



    }

    private void testHelperNullFieldChecker(String fieldName){
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Project.class, fieldName,Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Project.class, fieldName, Column.class);
        assertEquals("column"+fieldName+": nullable is true", false, c.nullable());

    }
}

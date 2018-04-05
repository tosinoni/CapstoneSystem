package com.carleton.CapstoneSystem.models;

import org.junit.Test;

import javax.persistence.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SubmissionTest {

    @Test
    public void testSubmissionForEntityAttribute() {
        AssertAnnotations.assertType(Submission.class, Entity.class);
    }

    @Test
    public void testId() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Submission.class, "id", Id.class, GeneratedValue.class, Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Submission.class, "id", Column.class);
        assertEquals("column id:  name is not equal", "id", c.name());
        assertEquals("column id: unique is false", true, c.unique());
        assertEquals("column id: nullable is true", false, c.nullable());

        Submission submission = getDefaultSubmission();
        assertEquals("id is not equal", 1, submission.getId());
    }

    @Test
    public void testSubmissionName() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Submission.class, "name",Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Submission.class, "name", Column.class);
        assertEquals("column name: nullable is true", false, c.nullable());

        Submission submission = getDefaultSubmission();
        assertEquals("name is not equal", "Final Report", submission.getName());
    }

    @Test
    public void testFileName() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Submission.class, "fileName",Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Submission.class, "fileName", Column.class);
        assertEquals("column fileName: name is not equal", "file_name", c.name());

        Submission submission = getDefaultSubmission();
        assertEquals("file name is not equal", "report.pdf", submission.getFileName());
    }

    @Test
    public void testProject(){
        AssertAnnotations.assertField(Submission.class, "project", ManyToOne.class, JoinColumn.class);

        ManyToOne m = ReflectTool.getFieldAnnotation(Submission.class, "project", ManyToOne.class);
        assertEquals("ManyToOne: Fetch is not equal", FetchType.EAGER, m.fetch());

        JoinColumn joinColumn = ReflectTool.getFieldAnnotation(Submission.class, "project", JoinColumn.class);
        assertEquals("JoinColumn project: name is not equal", "project_id", joinColumn.name());
        assertEquals("JoinColumn project: nullable is true", false, joinColumn.nullable());

        Project project = new Project("KAPI", "Fourth Year project");
        Submission submission = new Submission();
        submission.setProject(project);
        assertEquals("project is not equal", project, submission.getProject());
    }

    @Test
    public void testDueDate() throws ParseException {
        AssertAnnotations.assertField(Submission.class, "dueDate", Column.class, Temporal.class);

        Column c = ReflectTool.getFieldAnnotation(Submission.class, "dueDate", Column.class);

        assertEquals("column dueDate: name is not equal", "due_date", c.name());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("01/01/2017");
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);

        Submission submission = new Submission();
        submission.setDueDate(timestamp);
        assertEquals("due date is not equal", new Date(timestamp.getTime()), submission.getDueDate());
    }

    @Test
    public void testFile(){
        AssertAnnotations.assertField(Submission.class, "file", Column.class, Lob.class);

        Submission submission = new Submission();

        byte[] b = new byte[10];
        submission.setFile(b);

        assertNotEquals("file is equal", b, submission.getFile());
    }

    @Test
    public void testGrade() {
        //testing all the annotations on the id field
        AssertAnnotations.assertField( Submission.class, "grade",Column.class);
        //testing the @column annotation
        Column c = ReflectTool.getFieldAnnotation(Submission.class, "grade", Column.class);
        assertEquals("column grade:  name is not equal", "grade", c.name());
    }

    private Submission getDefaultSubmission() {
        Submission submission = new Submission();
        submission.setId(1);
        submission.setName("Final Report");
        submission.setFile(new byte[10]);
        submission.setFileName("report.pdf");

        return submission;
    }
}

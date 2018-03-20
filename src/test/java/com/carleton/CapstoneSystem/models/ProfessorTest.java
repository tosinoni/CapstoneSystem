package com.carleton.CapstoneSystem.models;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

public class ProfessorTest {
    @Test
    public void testStudentForEntityAttribute() {
        AssertAnnotations.assertType(Professor.class, Entity.class, Inheritance.class);
    }
}

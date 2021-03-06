package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kurssikuulustelija.domain.Exercise;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author henripal
 */
public class ExerciseTest {
    
    @Test
    public void creatingExerciseWorks() {
        Exercise exercise = new Exercise(0, "Tietorakenteet ja algoritmit", "Mikä on kurssin lyhenne?", "Tira");
        assertEquals(0, exercise.getId());
        assertEquals("Tietorakenteet ja algoritmit", exercise.getCourse());
        assertEquals("Mikä on kurssin lyhenne?", exercise.getQuestion());
        assertEquals("Tira", exercise.getAnswer());
        assertEquals("#0 | Course: Tietorakenteet ja algoritmit | Question: Mikä on kurssin lyhenne? | Answer: Tira", exercise.toString());
    }
}

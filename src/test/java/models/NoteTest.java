/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SPN
 */
public class NoteTest {
    
    public NoteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of updateBody method, of class Note.
     */
    @Test
    public void testUpdateBody() {
        System.out.println("updateBody");
        String newBody = "secondBody";
        Note note = new Note();
        note.body = "firstBody";
        assertEquals(note.body, "firstBody");
        note.updateBody(newBody);
        assertEquals(note.body, newBody);
    }
    
    @Test
    public void testAddTags() {
        Note note = new Note();
        assertEquals(note.tags.size(), 0);
        ArrayList<String> addTags = new ArrayList<>();
        addTags.add("nugg");
        addTags.add("jabber");
        note.addTags(addTags);
        assertEquals(note.tags.size(), 2);
        
    }
    
}

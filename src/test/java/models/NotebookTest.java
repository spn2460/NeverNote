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
public class NotebookTest {
    
    public NotebookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getNotebook method, of class Notebook.
     */
    @Test
    public void testGetNotebook() {
        System.out.println("getNotebook");
        int id = 0;
        Notebook instance = new Notebook();
        Notebook expResult = null;
        Notebook result = instance.getNotebook(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Notebook.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Notebook instance = new Notebook();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Notebook.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Notebook instance = new Notebook();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNote method, of class Notebook.
     */
    @Test
    public void testAddNote() {
        System.out.println("addNote");
        Note note = null;
        Notebook instance = new Notebook();
        instance.addNote(note);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNote method, of class Notebook.
     */
    @Test
    public void testDeleteNote() {
        System.out.println("deleteNote");
        Note note = null;
        Notebook instance = new Notebook();
        instance.deleteNote(note);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotes method, of class Notebook.
     */
    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        Notebook instance = new Notebook();
        ArrayList<Note> expResult = null;
        ArrayList<Note> result = instance.getNotes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNote method, of class Notebook.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        int id = 0;
        Notebook instance = new Notebook();
        Note expResult = null;
        Note result = instance.getNote(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilteredNotes method, of class Notebook.
     */
    @Test
    public void testGetFilteredNotes() {
        System.out.println("getFilteredNotes");
        String[] tags = null;
        Notebook instance = new Notebook();
        Notebook expResult = null;
        Notebook result = instance.getFilteredNotes(tags);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

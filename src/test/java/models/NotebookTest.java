/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.outbottle.controllers.DefaultController;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author SPN
 */
public class NotebookTest {

    public Notebook book;
    public DefaultController controller;

    public NotebookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        book = new Notebook();
        controller = new DefaultController();
        controller.createNotebook(book);
    }

    /**
     * Test of getFilteredNotes method, of class Notebook.
     */
    @Test
    public void testGetNotesByTags() {
        System.out.println("getNotesByTags");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("nugg");

        Notebook result = book.getFilteredNotes(tags);
        assertEquals(result.getNotes().size(), 0);

        addNoteHelper(new Note(), "figg");
        addNoteHelper(new Note(), "fizz");
        addNoteHelper(new Note(), "nuggy");
        assertEquals(book.getNotes().size(), 3);
        
        result = book.getFilteredNotes(tags);
        assertEquals(result.getNotes().size(), 0);
        
        tags.add("fizz");
        result = book.getFilteredNotes(tags);
        assertEquals(result.getNotes().size(), 1);
    }

    private void addNoteHelper(Note note, String tag) {
        note.tags.add(tag);
        book.addNote(note);
    }

}

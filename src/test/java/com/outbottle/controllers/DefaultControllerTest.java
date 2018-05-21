
package com.outbottle.controllers;

import java.util.ArrayList;
import models.Note;
import models.Notebook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;

/**
 *
 * @author SPN
 */
public class DefaultControllerTest {

    public Notebook book;
    public DefaultController controller;

    @Before
    public void setUp() {
        book = new Notebook();
        controller = new DefaultController();
    }

    /**
     * Test of getNotebookById method, of class DefaultController.
     */
    @Test
    public void testGetNotebookById_fail() {
        System.out.println("getNotebookById_fail");

        ResponseEntity entity = controller.getNotebookById(String.valueOf(book.id));
        HttpStatus statusCode = entity.getStatusCode();
        assertEquals(statusCode.toString(), "204");

        // good request
        controller.notebooks.put(book.id, book);
        entity = controller.getNotebookById(String.valueOf(book.id));
        statusCode = entity.getStatusCode();
        assertEquals(statusCode.toString(), "200");
    }
    
        /**
     * Test of getNotebookById method, of class DefaultController.
     */
    @Test
    public void testGetNotebookById_success() {
        System.out.println("getNotebookById_Success");

        controller.notebooks.put(book.id, book);
        ResponseEntity entity = controller.getNotebookById(String.valueOf(book.id));
        HttpStatus statusCode = entity.getStatusCode();
        assertEquals(statusCode.toString(), "200");
    }

    /**
     * Test of deleteNotebook method, of class DefaultController.
     */
    @Test
    public void testDeleteNotebook() {
        System.out.println("deleteNotebook");
        controller.notebooks.put(book.id, book);
        assertEquals(controller.notebooks.size(), 1);

        controller.deleteNotebook(String.valueOf(book.id), new ModelMap());
        assertEquals(controller.notebooks.size(), 0);
    }

    /**
     * Test of createNote method, of class DefaultController.
     */
    @Test
    public void testCreateNote() {
        System.out.println("createNote");
        Note note = new Note();
        ResponseEntity entity = controller.createNote(String.valueOf(note.id), note);
        HttpStatus statusCode = entity.getStatusCode();
        assertEquals(statusCode.toString(), "204");

        controller.notebooks.put(book.id, book);
        assertEquals(book.getNotes().size(), 0);
        entity = controller.createNote(String.valueOf(book.id), note);
        statusCode = entity.getStatusCode();
        assertEquals(statusCode.toString(), "200");
        assertEquals(book.getNotes().size(), 1);
    }

    /**
     * Test of getNoteById method, of class DefaultController.
     */
    @Test
    public void testGetNoteById() {
        System.out.println("getNoteById");
        Note note = new Note();
        ResponseEntity entity = controller.getNoteById(String.valueOf(book.id), String.valueOf(note.id));
        HttpStatus statusCode = entity.getStatusCode();
        // not notebook yet
        assertEquals(statusCode.toString(), "204");
        
        controller.notebooks.put(book.id, book);
        entity = controller.getNoteById(String.valueOf(book.id), String.valueOf(note.id));
        statusCode = entity.getStatusCode();
        // note not added to book yet
        assertEquals(statusCode.toString(), "204");
        
        book.addNote(note);
        entity = controller.getNoteById(String.valueOf(book.id), String.valueOf(note.id));
        statusCode = entity.getStatusCode();
        // note added to book
        assertEquals(statusCode.toString(), "200");
    }

    /**
     * Test of getNotesByTag method, of class DefaultController.
     * this method tests the response is OK
     * test for get tags is in notebook class
     */
    @Test
    public void testGetNotesByTag() {
        System.out.println("getNotesByTag");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("nugg");
        tags.add("nuggy");
        Note note = new Note();
        book.addNote(note);
        controller.notebooks.put(book.id, book);
        
        ResponseEntity entity = controller.getNotesByTag(String.valueOf(book.id), tags);
        HttpStatus statusCode = entity.getStatusCode();
        // not notebook yet
        assertEquals(statusCode.toString(), "200");

    }
    /**
     * Test of deleteNote method, of class DefaultController.
     */
    @Test
    public void testDeleteNote() {
        System.out.println("deleteNote");
        Note note = new Note();
        String response = controller.getResponseMsgForDeleteNote(String.valueOf(book.id), String.valueOf(note.id));
        assertEquals("Notebook " + book.id + " does not exist", response);
        
        controller.notebooks.put(book.id, book);
        response = controller.getResponseMsgForDeleteNote(String.valueOf(book.id), String.valueOf(note.id));
        assertEquals("Note " + note.id + " does not exist", response);
        
        book.addNote(note);
        int numNotes = book.getNotes().size();
        assertEquals(numNotes, 1);
        
        response = controller.getResponseMsgForDeleteNote(String.valueOf(book.id), String.valueOf(note.id));
        assertEquals("Note " + note.id + " deleted", response);
        controller.deleteNote(String.valueOf(book.id), String.valueOf(note.id), new ModelMap());
        numNotes = book.getNotes().size();
        assertEquals(numNotes, 0);
    }

}

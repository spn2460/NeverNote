/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.controllers;

import models.Note;
import models.Notebook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;

/**
 *
 * @author SPN
 */
public class DefaultControllerTest {
    
    public DefaultControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of index method, of class DefaultController.
     */
    @Test
    public void testIndex() {
        System.out.println("index");
        ModelMap map = null;
        DefaultController instance = new DefaultController();
        String expResult = "";
        String result = instance.index(map);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNotebook method, of class DefaultController.
     */
    @Test
    public void testCreateNotebook() {
        System.out.println("createNotebook");
        Notebook notebook = null;
        DefaultController instance = new DefaultController();
        ResponseEntity<Notebook> expResult = null;
        ResponseEntity<Notebook> result = instance.createNotebook(notebook);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotebookById method, of class DefaultController.
     */
    @Test
    public void testGetNotebookById() {
        System.out.println("getNotebookById");
        String id = "";
        DefaultController instance = new DefaultController();
        ResponseEntity<Notebook> expResult = null;
        ResponseEntity<Notebook> result = instance.getNotebookById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNotebook method, of class DefaultController.
     */
    @Test
    public void testDeleteNotebook() {
        System.out.println("deleteNotebook");
        String id = "";
        ModelMap map = null;
        DefaultController instance = new DefaultController();
        String expResult = "";
        String result = instance.deleteNotebook(id, map);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNote method, of class DefaultController.
     */
    @Test
    public void testCreateNote() {
        System.out.println("createNote");
        String id = "";
        Note note = null;
        DefaultController instance = new DefaultController();
        ResponseEntity<Note> expResult = null;
        ResponseEntity<Note> result = instance.createNote(id, note);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoteById method, of class DefaultController.
     */
    @Test
    public void testGetNoteById() {
        System.out.println("getNoteById");
        String bookid = "";
        String noteid = "";
        DefaultController instance = new DefaultController();
        ResponseEntity<Note> expResult = null;
        ResponseEntity<Note> result = instance.getNoteById(bookid, noteid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateNote method, of class DefaultController.
     */
    @Test
    public void testUpdateNote() {
        System.out.println("updateNote");
        String bookId = "";
        String noteId = "";
        Note note = null;
        DefaultController instance = new DefaultController();
        ResponseEntity<Note> expResult = null;
        ResponseEntity<Note> result = instance.updateNote(bookId, noteId, note);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotesByTag method, of class DefaultController.
     */
    @Test
    public void testGetNotesByTag() {
        System.out.println("getNotesByTag");
        String id = "";
        String[] tags = null;
        DefaultController instance = new DefaultController();
        ResponseEntity<Notebook> expResult = null;
        ResponseEntity<Notebook> result = instance.getNotesByTag(id, tags);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNote method, of class DefaultController.
     */
    @Test
    public void testDeleteNote() {
        System.out.println("deleteNote");
        String bookId = "";
        String noteId = "";
        ModelMap map = null;
        DefaultController instance = new DefaultController();
        String expResult = "";
        String result = instance.deleteNote(bookId, noteId, map);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

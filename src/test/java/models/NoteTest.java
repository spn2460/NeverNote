/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
     * Test of getTitle method, of class Note.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Note instance = new Note();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBody method, of class Note.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        Note instance = new Note();
        String expResult = "";
        String result = instance.getBody();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTags method, of class Note.
     */
    @Test
    public void testGetTags() {
        System.out.println("getTags");
        Note instance = new Note();
        String[] expResult = null;
        String[] result = instance.getTags();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreatedTime method, of class Note.
     */
    @Test
    public void testGetCreatedTime() {
        System.out.println("getCreatedTime");
        Note instance = new Note();
        long expResult = 0L;
        long result = instance.getCreatedTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastUpdated method, of class Note.
     */
    @Test
    public void testGetLastUpdated() {
        System.out.println("getLastUpdated");
        Note instance = new Note();
        long expResult = 0L;
        long result = instance.getLastUpdated();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBody method, of class Note.
     */
    @Test
    public void testUpdateBody() {
        System.out.println("updateBody");
        String newBody = "";
        Note instance = new Note();
        instance.updateBody(newBody);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

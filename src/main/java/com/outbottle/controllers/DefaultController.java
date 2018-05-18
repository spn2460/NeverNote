/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.controllers;

import java.util.HashMap;
import java.util.Map;
import models.Note;
import models.Notebook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author john
 */
@Controller
public class DefaultController {

    Notebook notebook;
    Note note;
    Map<Integer, Notebook> notebooks = new HashMap<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.put("msg", "Welcome to NeverNote built by Maven with Spring MVC framework!");
        return "index";
    }

    /**
     * create notebook
     * 127.0.0.1:8080/Spring4AnnotationConfigNeverNote/notebooks/create -d
     * "{\"title\":\"NUGGZ\"}" -H "Content-Type: application/json"
     *
     * @param notebook the notebook being created by json
     *
     * @return the JSON representation of the notebook
     */
    @RequestMapping(value = "/notebooks/create", method = RequestMethod.POST)
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook) {
        notebooks.put(notebook.id, notebook);
        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }

    // get notebook
    @RequestMapping(value = "notebooks/getNotebook/{id}", method = RequestMethod.GET)
    public ResponseEntity<Notebook> getNotebookById(@PathVariable(value = "id") String id) {
        int notebookId = (int) Integer.valueOf(id);
        notebook = notebooks.get(notebookId);
        //TODO: like to add something here if notebook is null
        // show message instead of empty field
        if (notebook == null) {
            return new ResponseEntity<>(notebook, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }

    // delete notebook
    @RequestMapping(value = "/notebooks/delete/{id}", method = RequestMethod.GET)
    public String deleteNotebook(@PathVariable(value = "id") String id, ModelMap map) {
        notebook = notebooks.remove(Integer.valueOf(id));
        if (notebook == null) {
            map.put("msg", "Notebook " + id + " does note exist");
        } else {
            map.put("msg", "Notebook " + id + " Deleted");
        }
        return "index";
    }

    // create note
    // curl 127.0.0.1:8080/Spring4AnnotationConfigNeverNote/notes/create/1 -d "{\"title\":\"NUGGZ\", \"body\":\"The body for note 1\", \"tags\": [\"nugg\", \"figg\"]}" -H "Content-Type: application/json"
    // add id for notebook
    @RequestMapping(value = "/notes/create/{id}", method = RequestMethod.POST)
    public ResponseEntity<Note> createNote(
            @PathVariable(value = "id") String id, @RequestBody Note note) {
        int notebookId = (int) Integer.valueOf(id);
        notebook = notebooks.get(notebookId);
        notebook.addNote(note);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    // update note
    @RequestMapping(value = "/update/{bookid}/{noteid}", method = RequestMethod.POST)
    public ResponseEntity<Note> updateNote(
            @PathVariable(value = "bookid") String bookId,
            @PathVariable(value = "noteid") String noteId,
            @RequestBody Note note) {
        String str = note.body;
        int notebookId = (int) Integer.valueOf(bookId);
        notebook = notebooks.get(notebookId);
        note = notebook.getNote((int) Integer.valueOf(noteId));
        note.updateBody(str);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    // retrieve filtered notes
    // SOMETHIGN WRONG HERE
    // all notes are deleted from the copy.  
    // try changing name of copy to make sure it is copy
    // check logic in nested for loops for errors, make sure notes are being added by tag
    @RequestMapping(value = "filter/{id}", method = RequestMethod.POST)
    public ResponseEntity<Notebook> getNotesByTag(
            @PathVariable(value = "id") String bookId,
            @RequestBody Note note) {
        String[] tags = note.tags;
        int notebookId = (int) Integer.valueOf(bookId);
        notebook = notebooks.get(notebookId);
        Notebook filteredBook = notebook.getFilteredNotes(tags);
        return new ResponseEntity<>(filteredBook, HttpStatus.OK);
    }

    // delete note by id
    @RequestMapping(value = "notes/delete/{bookid}/{noteid}", method = RequestMethod.GET)
    public String deleteNote(
            @PathVariable(value = "bookid") String bookId,
            @PathVariable(value = "noteid") String noteId,
            ModelMap map) {
        notebook = notebooks.get(Integer.valueOf(bookId));
        note = notebook.getNote(Integer.valueOf(noteId));
        notebook.deleteNote(note);
        if (note == null) {
            map.put("msg", "Notebook " + noteId + " does note exist");
        } else {
            map.put("msg", "Notebook " + noteId + " Deleted");
        }
        return "index";
    }
}

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
     * create notebook curl <url> -d title (as JSON, -H "Content-Type:
     * application/json"
     *
     * @param notebook the notebook being created by json
     *
     * @return the JSON representation of the notebook
     * @Jeremy - this method works as a post and returns id and title as JSON
     * curl 127.0.0.1:8080/Spring4AnnotationConfigNeverNote/notebooks/create -d "{\"title\":\"NUGGZ\"}" -H "Content-Type: application/json"
     */
    @RequestMapping(value = "/notebooks/create", method = RequestMethod.POST)
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook) {
        notebooks.put(notebook.id, notebook);
        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }

    // get notebook
    // @JEREMY - this also works as expected, returning the id and title of the notebook from the map
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
    // this works
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
    // @JEREMY this one says syntactically incorrect, but i am using the same curl except notes, not notebook
    // curl 127.0.0.1:8080/Spring4AnnotationConfigNeverNote/notes/create -d "{\"title\":\"NUGGZ\", \"body\":\"The body for note 1\", \"tag\":\"tag1\"}" -H "Content-Type: application/json"
    @RequestMapping(value = "/notes/create", method = RequestMethod.POST)
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    // retrieve notes
    @RequestMapping(value = "notes/tag/{tag}", method = RequestMethod.GET)
    public String getNoteByTag(ModelMap map) {
        map.put("msg", "Note retrieved");
        return "index";
    }

    // retrieve notes
    @RequestMapping(value = "notes/title/{title}", method = RequestMethod.GET)
    public String getNoteByTitle(ModelMap map) {
        map.put("msg", "Note retrieved");
        return "index";
    }

    // retrieve notes
    @RequestMapping(value = "notes/notebook/{ID}", method = RequestMethod.GET)
    public String getAllNotes(ModelMap map) {
        map.put("msg", "All Notes retrieved");
        return "index";
    }

    // update note , by id
    @RequestMapping(value = "notes/edit/{title}/{body}", method = RequestMethod.PUT)
    public String editBody(ModelMap map) {
        map.put("msg", "Body Updated");
        return "index";
    }

    // delete note ,by title
    @RequestMapping(value = "notes/id", method = RequestMethod.DELETE)
    public String deleteNote(ModelMap map) {
        map.put("msg", "Note deleted");
        return "index";
    }
}


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
 * @author nug
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
     * @param notebook the notebook being created from JSON request
     * @return the JSON representation of the notebook
     */
    @RequestMapping(value = "/notebooks/create", method = RequestMethod.POST)
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook) {
        notebooks.put(notebook.id, notebook);
        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }

    /**
     * get notebook by id
     * @param id The notebook id
     * @return the Notebook
     */
    @RequestMapping(value = "notebooks/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Notebook> getNotebookById(@PathVariable(value = "id") String id) {
        notebook = notebooks.get(Integer.valueOf(id));
        //TODO: show message instead of empty field if notebook id is null
        if (notebook == null) {
            return new ResponseEntity<>(notebook, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }

    /**
     * delete notebook
     * @param id the notebook id you want to delete
     * @param map the message map for displaying a message to the user
     * @return the page where the message will show
     */
    @RequestMapping(value = "/notebooks/delete/{id}", method = RequestMethod.GET)
    public String deleteNotebook(@PathVariable(value = "id") String id, ModelMap map) {
        notebook = notebooks.remove(Integer.valueOf(id));
        if (notebook == null) {
            map.put("msg", "Notebook " + id + " does note exist");
        } else {
            notebook = null;
            map.put("msg", "Notebook " + id + " Deleted");
        }
        return "index";
    }

    /**
     * create note
     * @param id the notebook id where the note will be stored
     * @param note the JSON from the request as a Note
     * @return The Note that was created
     */
    @RequestMapping(value = "/notes/create/{id}", method = RequestMethod.POST)
    public ResponseEntity<Note> createNote(
            @PathVariable(value = "id") String id, @RequestBody Note note) {
        notebook = notebooks.get(Integer.valueOf(id));
        if (notebook == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        notebook.addNote(note);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * get note
     * @param bookid the id of the notebook that contains the note
     * @param noteid the id of the note
     * @return The Note that was retrieved
     */
    @RequestMapping(value = "/notes/get/{bookid}/{noteid}", method = RequestMethod.GET)
    public ResponseEntity<Note> getNoteById(
            @PathVariable(value = "bookid") String bookid,
            @PathVariable(value = "noteid") String noteid) {
        notebook = notebooks.get(Integer.valueOf(bookid));
        note = notebook.getNote(Integer.valueOf(noteid));
        //TODO: show message instead of empty field if notebook id is null
        if (note == null) {
            return new ResponseEntity<>(note, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * update note body
     * @param bookId the id of the notebook that contains the note
     * @param noteId the id of the note to update
     * @param note a JSON request of only the body string
     * @return The Note that was updated
     */
    @RequestMapping(value = "/notes/update/{bookid}/{noteid}", method = RequestMethod.POST)
    public ResponseEntity<Note> updateNote(
            @PathVariable(value = "bookid") String bookId,
            @PathVariable(value = "noteid") String noteId,
            @RequestBody Note note) {
        String str = note.body;
        notebook = notebooks.get(Integer.valueOf(bookId));
        note = notebook.getNote(Integer.valueOf(noteId));
        note.updateBody(str);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * get notes by tag
     * @param bookId the id of the notebook that contains the notes
     * @param note a JSON request with an array of tags to search on
     * @return a filtered notebook
     */
    @RequestMapping(value = "notes/filter/{id}", method = RequestMethod.POST)
    public ResponseEntity<Notebook> getNotesByTag(
            @PathVariable(value = "id") String bookId,
            @RequestBody Note note) {
        String[] tags = note.tags;
        notebook = notebooks.get(Integer.valueOf(bookId));
        Notebook filteredBook = notebook.getFilteredNotes(tags);
        return new ResponseEntity<>(filteredBook, HttpStatus.OK);
    }

    /**
     * delete note
     * @param bookId the id of the notebook that contains the note
     * @param noteId the id of the note to delete
     * @param map a map to hold responses
     * @return the page to show the response
     */
    @RequestMapping(value = "notes/delete/{bookid}/{noteid}", method = RequestMethod.GET)
    public String deleteNote(
            @PathVariable(value = "bookid") String bookId,
            @PathVariable(value = "noteid") String noteId,
            ModelMap map) {
        notebook = notebooks.get(Integer.valueOf(bookId));
        note = notebook.getNote(Integer.valueOf(noteId));
        notebook.deleteNote(note);
        if (note == null) {
            map.put("msg", "Note " + noteId + " does note exist");
        } else {
            map.put("msg", "Note " + noteId + " Deleted");
        }
        return "index";
    }
}

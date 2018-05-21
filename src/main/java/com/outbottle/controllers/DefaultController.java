package com.outbottle.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

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
     *
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
     *
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
     *
     * @param id the notebook id you want to delete
     * @param map the message map for displaying a message to the user
     * @return the page where the message will show
     */
    @RequestMapping(value = "/notebooks/delete/{id}", method = RequestMethod.GET)
    public String deleteNotebook(@PathVariable(value = "id") String id, ModelMap map) {

        String response = getResponseMsgForDeleteNotebook(id);
        map.put("msg", response);
        return "index";
    }

    String getResponseMsgForDeleteNotebook(String id) {
        notebook = notebooks.remove(Integer.valueOf(id));
        if (notebook == null) {
            return "Notebook " + id + " does not exist.";
        }
        notebook = null;
        return "Notebook " + id + " deleted.";
    }

    /**
     * create note
     *
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
     *
     * @param bookid the id of the notebook that contains the note
     * @param noteid the id of the note
     * @return The Note that was retrieved
     */
    @RequestMapping(value = "/notes/get/{bookid}/{noteid}", method = RequestMethod.GET)
    public ResponseEntity<Note> getNoteById(
            @PathVariable(value = "bookid") String bookid,
            @PathVariable(value = "noteid") String noteid) {
        notebook = notebooks.get(Integer.valueOf(bookid));
        if (notebook == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        note = notebook.getNote(Integer.valueOf(noteid));
        if (note == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * update note body
     *
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
     * POST tags array as JSON
     *
     * @param bookId notebook id
     * @param noteId note id
     * @param newNote the note to update
     * @return Updated note object
     */
    @RequestMapping(value = "/notes/addTags/{bookid}/{noteid}", method = RequestMethod.POST)
    public ResponseEntity<Note> addTags(
            @PathVariable(value = "bookid") String bookId,
            @PathVariable(value = "noteid") String noteId,
            @RequestBody Note newNote) {

        notebook = notebooks.get(Integer.valueOf(bookId));
        note = notebook.getNote(Integer.valueOf(bookId));
        note.addTags(newNote.tags);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     *
     * @param bookId notebook id
     * @param noteId note id
     * @param newNote the response note from JSON POST
     * @return The updated note
     */
    @RequestMapping(value = "/notes/deleteTags/{bookid}/{noteid}", method = RequestMethod.POST)
    public ResponseEntity<Note> deleteTags(
            @PathVariable(value = "bookid") String bookId,
            @PathVariable(value = "noteid") String noteId,
            @RequestBody Note newNote) {

        notebook = notebooks.get(Integer.valueOf(bookId));
        note = notebook.getNote(Integer.valueOf(bookId));
        note.removeTags(newNote.tags);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * curl - "<http..>/NeverNote/notes/filter?id=1&tags=foo,bar" 
     * browser - remove quotes
     *
     * @param id the book id
     * @param tags array list of tags
     * @return filtered list of notes
     */
    @RequestMapping(value = "notes/filter", method = RequestMethod.GET)
    public ResponseEntity<Notebook> getNotesByTag(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "tags", required = true) ArrayList<String> tags) {
        notebook = notebooks.get(Integer.valueOf(id));
        Notebook filteredBook = notebook.getFilteredNotes(tags);
        return new ResponseEntity<>(filteredBook, HttpStatus.OK);
    }

    /**
     * delete note
     *
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

        String response = getResponseMsgForDeleteNote(bookId, noteId);
        map.put("msg", response);
        return "index";
    }

    public String getResponseMsgForDeleteNote(String bookId, String noteId) {
        notebook = notebooks.get(Integer.valueOf(bookId));
        if (notebook == null) {
            return "Notebook " + bookId + " does not exist";
        }
        note = notebook.getNote(Integer.valueOf(noteId));
        if (note == null ) {
            return "Note " + noteId + " does not exist";
        }
        notebook.deleteNote(note);
        note = null;
        return "Note " + noteId + " deleted";
    }
}

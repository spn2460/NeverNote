/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author SPN
 */
public class Notebook {

    private static final AtomicInteger COUNT = new AtomicInteger(0);
    public int id = -1;
    public String title;
    public ArrayList<Note> notes = new ArrayList<>();

    public Notebook() {
        this.id = COUNT.incrementAndGet();
    }

    public Notebook getNotebook(int id) {
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public void addNote(Note note) {
        if (null != note) {
            notes.add(note);
        }
    }

    public void deleteNote(Note note) {
        for (Note item : notes) {
            if (item == note) {
                notes.remove(item);
                break;
            }
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
    
    public Note getNote(int id) {
        for (Note note: notes) {
            if (note.id == id) {
                return note;
            }
        }
        return null;
    }

    public void deleteNotebook() {
//        this = null;
    }

    public Notebook getFilteredNotes(String[] tags) {
        Notebook copy = this;
        copy.notes.clear();
        // im pretty sure there is a better way to do this (with a map maybe)
        for (Note note: this.getNotes()) {
            for (String tag: note.getTags()) {
                for (String search: tags) {
                    if (tag.equals(search)) {
                        copy.addNote(note);
                    }
                }
            }
        }
        
        return copy;
    }

}

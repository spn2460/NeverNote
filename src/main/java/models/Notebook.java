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

    private static AtomicInteger count = new AtomicInteger(0);
    public int id = -1;
    public String title;
    public ArrayList<Note> notes = new ArrayList<>();

    public Notebook() {
        this.id = count.incrementAndGet();
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

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void deleteNotebook() {
//        this = null;
    }
    
    public List<Note> getNotesByTag(String tag) {
        return null;
    }

}

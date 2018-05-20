
package models;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import Utilities.SerialUtility;

/**
 *
 * @author nug
 */
public class Notebook {

    private static final AtomicInteger BOOKCOUNT = new AtomicInteger(0);
    public int id = -1;
    public String title;
    public ArrayList<Note> notes = new ArrayList<>();

    public Notebook() {
        this.id = BOOKCOUNT.incrementAndGet();
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
                item = null;
                break;
            }
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Note getNote(int id) {
        for (Note note : notes) {
            if (note.id == id) {
                return note;
            }
        }
        return null;
    }

    public Notebook getFilteredNotes(String[] tags) {
        // cloning the object so the original is unchanged
        Notebook copy = (Notebook) SerialUtility.cloneObject(this);
        copy.notes.clear();
        
        // creating copy to avoid concurrent modification error
        ArrayList<Note> copylist = this.getNotes();

        // im pretty sure there is a better way to do this (with a map maybe)
        for (Note note : copylist) {
            for (String noteTag : note.getTags()) {
                for (String matchTag : tags) {
                    if (noteTag.equals(matchTag)) {
                        copy.addNote(note);
                    }
                }
            }
        }
        return copy;
    }

}

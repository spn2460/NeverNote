/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author SPN
 */
public class Note {
    
    String body;
    String title;
    String tag;
    long createdTime;
    
    Note(String title, String body, String tag) {
        this.body = body;
        this.title = title;
        this.tag = tag;
        this.createdTime = System.currentTimeMillis();
    }
    
    public Note getNoteByTitle(String title) {
        if(title.equals(this.title)) {
            return this;
        }
        return null;
    }
    
    public Note getNoteByTag(String tag) {
        if (tag.equals(this.tag)) {
            return this;
        } 
        return null;
//        for (int i = 0; i < this.tags.length; i++) {
//            if (tag.equals(this.tags[i])) {
//                return this;
//            }
//        }
//        return null;
    }
    
    public void outputNote() {
        System.out.println(this.title);
        System.out.println(this.tag);
        System.out.println(this.body);
        System.out.println(this.createdTime);
    }
    
}

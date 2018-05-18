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

    // I commented out the body and tag, trying to mimic the Notebook class
    
    String title;
//    String body;
//    String tag;
    long createdTime;
    long lastUpdated;

    // I removed all the params here, as it seems like the are not needed.
    // Notebook title was being set without params, so I assume the same can happen here
    public Note() {
        // all fields are populated via curl POST
        this.createdTime = System.currentTimeMillis();
    }

//    public Note getNoteByTitle(String title) {
//        if (title.equals(this.title)) {
//            return this;
//        }
//        return null;
//    }

//    public Note getNoteByTag(String tag) {
//        if (tag.equals(this.tag)) {
//            return this;
//        }
//        return null;
////        for (int i = 0; i < this.tags.length; i++) {
////            if (tag.equals(this.tags[i])) {
////                return this;
////            }
////        }
////        return null;
//    }

//    public void outputNote() {
//        System.out.println(this.title);
//        System.out.println(this.tag);
//        System.out.println(this.body);
//        System.out.println(this.createdTime);
//    }

}

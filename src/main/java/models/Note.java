/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author SPN
 */
public class Note {

    // I commented out the body and tag, trying to mimic the Notebook class
    private static AtomicInteger count = new AtomicInteger(0);
//    public int notebookId;
    public int id = -1;
    public String title;
    public String body;
    public String[] tags;
    public long createdTime;
    long lastUpdated;

    public Note() {
        // all fields are populated via curl POST except ...
        this.id = count.incrementAndGet();
        this.createdTime = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String[] getTags() {
        return tags;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }
    
    public void updateBody(String newBody) {
        body = newBody;
        lastUpdated = System.currentTimeMillis();
    }

}

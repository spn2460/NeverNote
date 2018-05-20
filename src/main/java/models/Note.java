package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author nug
 */
public class Note {

    private static final AtomicInteger NOTECOUNT = new AtomicInteger(0);
    public int id = -1;
    public String title;
    public String body;
    public ArrayList<String> tags = new ArrayList<>();
    public long createdTime;
    long lastUpdated;

    public Note() {
        // all fields are populated via curl POST except ...
        this.id = NOTECOUNT.incrementAndGet();
        this.createdTime = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void addTags(ArrayList<String> tags) {

        this.tags.addAll(tags);
    }

    public void removeTags(ArrayList<String> tags) {

        for (String tag : tags) {
            Iterator itr = this.tags.iterator();
            while (itr.hasNext()) {
                if (itr.next().equals(tag)) {
                    itr.remove();
                }
            }
        }
        System.out.println("Size of this.tags after: " + this.tags.size());
    }

    public ArrayList<String> getTags() {
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

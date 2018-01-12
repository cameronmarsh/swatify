package edu.swarthmore.cs.cs71.swatify.models;

import javax.persistence.*;

/**
 * Comment class that extends PublicAction.
 */
@Entity
@Table
public class Comment extends PublicAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    public Comment(User user, PublicAction parent, String content) {
        super(user);
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void getParent() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

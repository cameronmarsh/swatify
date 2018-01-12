package edu.swarthmore.cs.cs71.swatify.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for PublicAction
 * Subclassed by Post, Comment, and Rating classes
 */
@MappedSuperclass
public class PublicAction {
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    public PublicAction() {
    }

    public PublicAction(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

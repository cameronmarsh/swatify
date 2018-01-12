package edu.swarthmore.cs.cs71.swatify.models;

import javax.persistence.*;

@Entity
@Table
public class Post extends PublicAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = true)
    private Discussion discussion;

    public Post() {
    }

    public Post(User user, Discussion discussion, String content) {
        super(user);
        this.content = content;
        this.discussion = discussion;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Discussion getDiscussion() {
        return this.discussion;
    }
}

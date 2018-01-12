package edu.swarthmore.cs.cs71.swatify.models;


import javax.persistence.*;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    private String content;
    private String albumId;
    private int stars;

//    @ManyToOne
//    @JoinColumn(name="discussion_id", nullable=true)
//    private Discussion discussion;

    public Review() {
    }

    public Review(String content, User user, String albumId, int stars) {
        this.user = user;
        this.content = content;
        this.albumId = albumId;
        this.stars = stars;
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

//    public void setDiscussion(Discussion discussion) {
//        this.discussion = discussion;
//    }

    public String getAlbumId() {
        return albumId;
    }


}

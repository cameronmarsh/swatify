package edu.swarthmore.cs.cs71.swatify.models;

import org.junit.Assert;
import org.junit.Test;

public class CommentsTest {
    private Comment comment;

    @Test
    public void createComment() throws Exception {
        User user = new User();
        Discussion discussion = new Discussion();
        Post parent = new Post(user, discussion, "test post");

        this.comment = new Comment(user, parent, "Nice!");
        Assert.assertEquals("Nice!", this.comment.getContent());
        Assert.assertEquals(user, this.comment.getUser());
    }

    @Test
    public void changeContent() throws Exception {
        this.comment.setContent("updated");
        Assert.assertEquals("updated", this.comment.getContent());
    }
}

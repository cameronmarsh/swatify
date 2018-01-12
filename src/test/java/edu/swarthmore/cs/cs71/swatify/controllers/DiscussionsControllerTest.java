package edu.swarthmore.cs.cs71.swatify.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.swarthmore.cs.cs71.swatify.models.Discussion;
import edu.swarthmore.cs.cs71.swatify.models.Post;
import edu.swarthmore.cs.cs71.swatify.models.User;
import edu.swarthmore.cs.cs71.swatify.test.ControllerTestBase;
import edu.swarthmore.cs.cs71.swatify.test.TestUtil;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import edu.swarthmore.cs.cs71.swatify.util.HibernateUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static edu.swarthmore.cs.cs71.swatify.test.TestUtil.request;
import static org.junit.Assert.assertEquals;

public class DiscussionsControllerTest extends ControllerTestBase {
    private static Post postFixture;
    private static Discussion discussionFixture;
    private static Discussion albumDiscussionFixture;
    private static User userFixture;

    @BeforeClass
    public static void createFixtures() {
        userFixture = new User("oliver", "onewman1@swarthmore.edu", "spotifyId");
        discussionFixture = new Discussion("Test discussion", userFixture.getId());
        albumDiscussionFixture = new Discussion("Test album discussion", userFixture.getId(), "47b7v7e");
        HibernateUtil.saveObject(userFixture);
        HibernateUtil.saveObject(discussionFixture);
        HibernateUtil.saveObject(albumDiscussionFixture);
        postFixture = new Post(userFixture, discussionFixture, "Test post");
        HibernateUtil.saveObject(postFixture);
    }

    @Test
    public void createNewDiscussion() {
        Discussion discussion = new Discussion("Testing discussion", 5);

        TestUtil.TestResponse res = request("POST", "/api/v1/discussions", GsonUtil.toJson(discussion));
        assertEquals(200, res.getStatus());

        Discussion createdDiscussion = GsonUtil.fromJson(Discussion.class, res.json().toString());

        assertEquals(discussion.getTitle(), createdDiscussion.getTitle());
    }

    @Test
    public void getDiscussion() {
        String url = String.format("/api/v1/discussions/%d", albumDiscussionFixture.getId());

        TestUtil.TestResponse res = request("GET", url);
        assertEquals(200, res.getStatus());

        Discussion gottenDiscussion = GsonUtil.fromJson(Discussion.class, res.json().toString());
        assertEquals(gottenDiscussion.getId(), albumDiscussionFixture.getId());
    }

    @Test
    public void getAllDiscussions() {
        String url = "/api/v1/discussions";

        TestUtil.TestResponse res = request("GET", url);
        assertEquals(200, res.getStatus());
        String temp = res.jsonArray().toString();

        Type listType = new TypeToken<ArrayList<Discussion>>(){}.getType();

        List<Discussion> gottenDiscussions = GsonUtil.fromJsonArray(listType, temp);
        return;
    }

    @Test
    public void updateDiscussion() {
        String url = String.format("/api/v1/discussions/%d", albumDiscussionFixture.getId());

        albumDiscussionFixture.setTitle("Updated title");

        TestUtil.TestResponse res = request("PUT", url, GsonUtil.toJson(albumDiscussionFixture));
        assertEquals(200, res.getStatus());

        Discussion updatedDiscussion = GsonUtil.fromJson(Discussion.class, res.json().toString());

        assertEquals(albumDiscussionFixture.getTitle(), updatedDiscussion.getTitle());
    }

    @Test
    public void deleteDiscussion() {
        Discussion discussionToDelete = new Discussion("Testing discussion", userFixture.getId(), albumDiscussionFixture.getAlbumSpotifyId());
        HibernateUtil.saveObject(discussionToDelete);

        String url = String.format("/api/v1/discussions/%d", discussionToDelete.getId());

        TestUtil.TestResponse res = request("DELETE", url);
        assertEquals(200, res.getStatus());

        Discussion deletedDiscussion = GsonUtil.fromJson(Discussion.class, res.json().toString());

        assertEquals(discussionToDelete.getTitle(), deletedDiscussion.getTitle());
    }

    @Test
    public void createNewPost() {
        Post post = new Post(userFixture, albumDiscussionFixture, "Test content");
        String url = String.format("/api/v1/discussions/%d/posts", albumDiscussionFixture.getId());

        TestUtil.TestResponse res = request("POST", url, GsonUtil.toJson(post));
        assertEquals(200, res.getStatus());

        Post createdPost = GsonUtil.fromJson(Post.class, res.json().toString());

        assertEquals(post.getContent(), createdPost.getContent());
    }

    @Test
    public void getAPost() {
        String url = String.format("/api/v1/discussions/%d/posts/%d", albumDiscussionFixture.getId(), postFixture.getId());

        TestUtil.TestResponse res = request("GET", url);
        assertEquals(200, res.getStatus());

        Post gottenPost = GsonUtil.fromJson(Post.class, res.json().toString());
        assertEquals(gottenPost.getId(), postFixture.getId());
    }

    @Test
    public void updateAPost() {
        String url = String.format("/api/v1/discussions/%d/posts/%d", albumDiscussionFixture.getId(), postFixture.getId());

        postFixture.setContent("Updated content");

        TestUtil.TestResponse res = request("PUT", url, GsonUtil.toJson(postFixture));
        assertEquals(200, res.getStatus());

        Post updatedPost = GsonUtil.fromJson(Post.class, res.json().toString());

        assertEquals(postFixture.getContent(), updatedPost.getContent());
    }

    @Test
    public void deleteAPost() {
        Post postToDelete = new Post(userFixture, discussionFixture, "This post will be deleted");
        HibernateUtil.saveObject(postToDelete);

        String url = String.format("/api/v1/discussions/%d/posts/%d", albumDiscussionFixture.getId(), postToDelete.getId());

        TestUtil.TestResponse res = request("DELETE", url);
        assertEquals(200, res.getStatus());

        Post deletedPost = GsonUtil.fromJson(Post.class, res.json().toString());

        assertEquals(postToDelete.getContent(), deletedPost.getContent());
    }
}

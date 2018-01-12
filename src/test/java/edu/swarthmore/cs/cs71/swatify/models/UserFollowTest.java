package edu.swarthmore.cs.cs71.swatify.models;

import org.junit.Assert;
import org.junit.Test;

public class UserFollowTest {

    public static void main(String[] args) {
        UserFollowTest test = new UserFollowTest();
        test.shouldCreateUserFollow();
    }

    @Test
    public void shouldCreateUserFollow() {
        User elsher = new User();
        User zach = new User();
        UserFollow elsherFollowsZach = new UserFollow(elsher, zach);

        Assert.assertEquals(elsher, elsherFollowsZach.getFollower());
        Assert.assertEquals(zach, elsherFollowsZach.getFollowing());
    }
}

package edu.swarthmore.cs.cs71.swatify.controllers;

import edu.swarthmore.cs.cs71.swatify.models.UserFollow;
import edu.swarthmore.cs.cs71.swatify.util.HibernateUtil;

public class UserFollowController {
    public UserFollowController() {

    }

    public static boolean saveUserFollow(UserFollow follow) {
        return HibernateUtil.saveObject(follow);
    }

    public static boolean removeArtistFollow(UserFollow follow) {
        return HibernateUtil.deleteObject(UserFollow.class, follow.getId());
    }


    public static UserFollow getUserFollow(int id) {
        return HibernateUtil.getObjectById(UserFollow.class, id);
    }
}

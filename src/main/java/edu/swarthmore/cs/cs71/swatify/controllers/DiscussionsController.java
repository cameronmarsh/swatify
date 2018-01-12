package edu.swarthmore.cs.cs71.swatify.controllers;

import edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes.*;
import edu.swarthmore.cs.cs71.swatify.models.Discussion;
import edu.swarthmore.cs.cs71.swatify.models.Post;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class DiscussionsController {
    public DiscussionsController() {
        path("/discussions", () -> {
            post("", new CreateObjectHibernateRoute() {
                @Override
                protected Object createObject(Request request, Response response) {
                    return GsonUtil.fromJson(Discussion.class, request.body());
                }
            });

            get("", new ListObjectsHibernateRoute() {
                @Override
                protected String getQueryString() {
                    return "from Discussion d";
                }
            });

            path("/:id", () -> {
                get("", new GetObjectHibernateRoute() {
                    @Override
                    protected Class<?> getObjectClass() {
                        return Discussion.class;
                    }
                });

                put("", new UpdateObjectHibernateRoute() {
                    @Override
                    protected Object createUpdatedObject(Request request, Response response) {
                        return GsonUtil.fromJson(Discussion.class, request.body());
                    }
                });

                delete("", new DeleteObjectHibernateRoute() {
                    @Override
                    protected Class<?> getObjectClass() {
                        return Discussion.class;
                    }
                });

                // /:id/posts
                // "" getAll
                //
                path("/posts", () -> {
                    get("/:postId", new GetObjectHibernateRoute() {
                        @Override
                        protected Class<?> getObjectClass() {
                            return Post.class;
                        }

                        @Override
                        protected String getIdParamName() {
                            return "postId";
                        }

                    });

                    post("", new CreateObjectHibernateRoute() {
                        @Override
                        protected Object createObject(Request request, Response response) {
                            return GsonUtil.fromJson(Post.class, request.body());
                        }
                    });

                    put("/:postId", new UpdateObjectHibernateRoute() {
                        @Override
                        protected Object createUpdatedObject(Request request, Response response) {
                            return GsonUtil.fromJson(Post.class, request.body());
                        }
                    });

                    delete("/:postId", new DeleteObjectHibernateRoute() {
                        @Override
                        protected Class<?> getObjectClass() {
                            return Post.class;
                        }
                    });

                    get("", new ListObjectsHibernateRoute() {
                        @Override
                        protected String getQueryString() {
                            return "select * from Post";
                        }
                    });
                });

            });
        });
    }
}

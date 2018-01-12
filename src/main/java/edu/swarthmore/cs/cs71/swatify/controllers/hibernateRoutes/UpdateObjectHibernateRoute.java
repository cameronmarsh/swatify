package edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes;

import org.hibernate.Session;
import spark.Request;
import spark.Response;

public abstract class UpdateObjectHibernateRoute extends BaseHibernateRoute {
    @Override
    protected Object doAction(Session session, Request request, Response response) {
        Object obj = createUpdatedObject(request, response);
        session.update(obj);
        return obj;
    }

    protected abstract Object createUpdatedObject(Request request, Response response);
}

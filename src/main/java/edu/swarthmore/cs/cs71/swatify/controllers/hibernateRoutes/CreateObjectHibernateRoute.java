package edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes;

import org.hibernate.Session;
import spark.Request;
import spark.Response;

public abstract class CreateObjectHibernateRoute extends BaseHibernateRoute {
    @Override
    public Object doAction(Session session, Request request, Response response) {
        Object obj = createObject(request, response);
        session.save(obj);
        return obj;
    }

    protected abstract Object createObject(Request request, Response response);
}

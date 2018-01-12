package edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes;

import org.hibernate.Session;
import spark.Request;
import spark.Response;

public abstract class DeleteObjectHibernateRoute extends BaseHibernateRoute {

    @Override
    public Object doAction(Session session, Request request, Response response) {
        int id = Integer.parseInt(request.params(getIdParamName()));
        Object obj = session.get(getObjectClass(), id);
        session.delete(obj);
        return obj;
    }

    protected abstract Class<?> getObjectClass();

    protected String getIdParamName() {
        return "id";
    }
}

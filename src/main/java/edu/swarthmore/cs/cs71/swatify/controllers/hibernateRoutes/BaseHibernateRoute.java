package edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes;

import edu.swarthmore.cs.cs71.swatify.errors.BaseError;
import edu.swarthmore.cs.cs71.swatify.errors.InternalServerError;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import edu.swarthmore.cs.cs71.swatify.util.HibernateUtil;
import org.hibernate.Session;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class BaseHibernateRoute implements Route {

    @Override
    public String handle(Request request, Response response) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Object obj;
        try {
            obj = doAction(session, request, response);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            obj = new InternalServerError(e.getMessage());
        } finally {
            session.close();
        }

        if (obj instanceof BaseError) {
            response.status(((BaseError) obj).getStatus());
        }

        return GsonUtil.toJson(obj);
    }

    protected abstract Object doAction(Session session, Request request, Response response);
}

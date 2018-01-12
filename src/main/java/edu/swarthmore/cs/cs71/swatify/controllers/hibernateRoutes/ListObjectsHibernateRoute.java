package edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes;

import org.hibernate.Session;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public abstract class ListObjectsHibernateRoute extends BaseHibernateRoute {
    @Override
    public List<Object> doAction(Session session, Request request, Response response) {
        String query = getQueryString();
        return session.createQuery(query).list();
    }

    protected abstract String getQueryString();
}

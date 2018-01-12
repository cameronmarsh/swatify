package edu.swarthmore.cs.cs71.swatify.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Save an object to the database.
     *
     * @param object The object to persist.
     * @param <T>    the class of the object.
     */
    public static <T> boolean saveObject(T object) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        boolean saved = false;

        try {
            session.save(object);
            session.getTransaction().commit();
            saved = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return saved;
    }

    /**
     * Retrieve an object from the database.
     *
     * @param objectClass The class of the object to retrieve.
     * @param objectId    The ID of the object to retrieve.
     * @param <T>         The class of the object.
     * @return The desired object, or null if the object doesn't exist.
     */
    public static <T> T getObjectById(Class<T> objectClass, int objectId) {
        T object;

        try (Session session = getSessionFactory().openSession()) {
            object = session.get(objectClass, objectId);
        } catch (Exception e) {
            object = null;
        }

        return objectClass.cast(object);
    }

    /**
     * Update an object in the database.
     *
     * @param object The updated object to save to the database.
     */
    public static <T> boolean updateObject(T object) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        boolean updated = false;

        try {
            session.update(object);
            session.getTransaction().commit();
            updated = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            object = null;
        } finally {
            session.close();
        }

        return updated;
    }

    /**
     * Delete an object in the database.
     *
     * @param objectClass The class of the object to delete.
     * @param objectId    The ID of the object to delete.
     * @param <T>         The class of the object.
     */
    public static <T> boolean deleteObject(Class<T> objectClass, int objectId) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        T object = null;

        boolean deleted = false;

        try {
            object = objectClass.cast(session.get(objectClass, objectId));
            session.delete(object);
            session.getTransaction().commit();
            deleted = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return deleted;
    }
}

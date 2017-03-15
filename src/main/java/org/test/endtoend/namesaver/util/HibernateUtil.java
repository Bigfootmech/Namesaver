package org.test.endtoend.namesaver.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.test.endtoend.namesaver.entities.Names;

public class HibernateUtil {
	
	private static final Logger LOGGER = Logger.getLogger( HibernateUtil.class.getName() );
	
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
        	LOGGER.error("Failed to initialize Hibernate session.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void saveName(Names name) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();

        session.save(name);
        
        session.getTransaction().commit();
        
        session.close();
    }
    
    public static List<Names> getNames() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        Query q = session.createQuery("From Names ");

        List<Names> resultList = q.list();
        
        session.close();
        
        return resultList;
    }

}

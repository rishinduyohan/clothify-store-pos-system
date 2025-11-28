package edu.icet.clothify.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final Configuration configuration = new Configuration();
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(Class getClass){
        if (null==sessionFactory){
            configuration.addAnnotatedClass(getClass);
            configuration.configure("cfg/hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}

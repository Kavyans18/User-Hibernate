package com.pw.User.util;

import org.apache.catalina.User;
import org.apache.tomcat.jni.Address;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create a StandardServiceRegistry
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // Configure from hibernate.cfg.xml if present
                    .build();

            // Create MetadataSources from annotations and mappings
            MetadataSources metadataSources = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Address.class);

            // Create a SessionFactory using the metadata sources
            return metadataSources.buildMetadata().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception and fail gracefully
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
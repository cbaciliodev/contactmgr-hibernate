package com.ajinkya.contactmgr;

import com.ajinkya.contactmgr.model.Contact;
import com.ajinkya.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Application {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new ContactBuilder("Ajinkya", "Borade")
                .withEmail("a@b.com")
                .withPhone(9820098200L)
                .build();

        // open a session


        // begin the transaction


        // use session to save contact


        // commit the transaction

        // Close the session
    }
}

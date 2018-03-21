package com.ajinkya.contactmgr;

import com.ajinkya.contactmgr.model.Contact;
import com.ajinkya.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Session;
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
        save(contact);
    }

    public static void save(Contact contact) {
        // open a session
        Session session = sessionFactory.openSession();

        // begin the transaction
        session.beginTransaction();
        // use session to save contact
        session.save(contact);
        // commit the transaction
        session.getTransaction().commit();
        // Close the session
        session.close();
    }
}

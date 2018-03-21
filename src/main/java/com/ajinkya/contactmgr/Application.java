package com.ajinkya.contactmgr;

import com.ajinkya.contactmgr.model.Contact;
import com.ajinkya.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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

        // Display contacts from DB
        fecthAllContacts().stream().forEach(System.out::println);
    }

    @SuppressWarnings("unchecked")
    public static List<Contact> fecthAllContacts() {
        Session session = sessionFactory.openSession();

        // create Criteria
        CriteriaQuery<Contact> criteriaQuery = session.getCriteriaBuilder().createQuery(Contact.class);
        criteriaQuery.from(Contact.class);

        List<Contact> contacts = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return contacts;
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

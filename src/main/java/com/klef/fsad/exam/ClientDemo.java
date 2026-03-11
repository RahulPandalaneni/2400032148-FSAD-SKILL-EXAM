package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * ClientDemo Class
 * Demonstrates HQL Operations on the Service entity:
 *
 *   I.  Insert records using a persistent object.
 *   II. Update Name and Status based on ID using HQL with named parameters.
 *
 * Database : fsadexam
 * Package  : com.klef.fsad.exam
 */
public class ClientDemo {

    // ── Shared SessionFactory ─────────────────────────────────────────────────────
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    // ═════════════════════════════════════════════════════════════════════════════
    //  OPERATION I – INSERT RECORDS USING PERSISTENT OBJECT
    // ═════════════════════════════════════════════════════════════════════════════
    public static int insertService(Service service) {
        Session session = null;
        Transaction tx  = null;
        int generatedId = -1;

        try {
            session     = getSessionFactory().openSession();
            tx          = session.beginTransaction();

            generatedId = (Integer) session.save(service);  // Persistent object INSERT

            tx.commit();

            System.out.println("  ✔ [INSERT SUCCESS] Service saved with Auto ID = " + generatedId);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("  ✘ [INSERT FAILED] " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return generatedId;
    }

    // ═════════════════════════════════════════════════════════════════════════════
    //  OPERATION II – UPDATE Name & Status BY ID USING HQL NAMED PARAMETERS
    // ═════════════════════════════════════════════════════════════════════════════
    public static void updateServiceByHQL(int id, String newName, String newStatus) {
        Session session = null;
        Transaction tx  = null;

        try {
            session = getSessionFactory().openSession();
            tx      = session.beginTransaction();

            // ── HQL Update with Named Parameters ──────────────────────────────────
            String hql = "UPDATE Service s "
                       + "SET s.name = :newName, s.status = :newStatus "
                       + "WHERE s.id = :serviceId";

            Query query = session.createQuery(hql);
            query.setParameter("newName",   newName);    // named parameter :newName
            query.setParameter("newStatus", newStatus);  // named parameter :newStatus
            query.setParameter("serviceId", id);         // named parameter :serviceId

            int rowsAffected = query.executeUpdate();
            tx.commit();

            if (rowsAffected > 0) {
                System.out.println("  ✔ [HQL UPDATE SUCCESS] " + rowsAffected
                        + " record(s) updated for ID = " + id);
            } else {
                System.out.println("  ⚠ [HQL UPDATE] No record found with ID = " + id);
            }

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("  ✘ [HQL UPDATE FAILED] " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // ═════════════════════════════════════════════════════════════════════════════
    //  DISPLAY ALL – HQL SELECT to verify results
    // ═════════════════════════════════════════════════════════════════════════════
    public static void displayAllServices() {
        Session session = null;

        try {
            session = getSessionFactory().openSession();

            // HQL SELECT ALL
            String hql = "FROM Service";
            Query<Service> query = session.createQuery(hql, Service.class);
            List<Service>  list  = query.getResultList();

            System.out.println("\n  Total Records Found : " + list.size());
            for (Service s : list) {
                System.out.println(s);
            }

        } catch (Exception e) {
            System.err.println("  ✘ [DISPLAY FAILED] " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    // ═════════════════════════════════════════════════════════════════════════════
    //  MAIN METHOD – DEMO DRIVER
    // ═════════════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {

        System.out.println("=======================================================");
        System.out.println("  KLEF FSAD EXAM – HIBERNATE HQL SERVICE PROJECT       ");
        System.out.println("  Database : fsadexam                                  ");
        System.out.println("=======================================================\n");

        // ── OPERATION I : INSERT 3 Records ───────────────────────────────────────
        System.out.println(">>> OPERATION I : Inserting Service Records...\n");

        Service s1 = new Service(
            "Web Development",
            new Date(), "Active",
            "IT Service", 15000.00,
            "30 Days", "TechSoft Solutions",
            "Hyderabad"
        );

        Service s2 = new Service(
            "Network Setup",
            new Date(), "Pending",
            "Infrastructure", 8500.00,
            "7 Days", "NetPro Services",
            "Bangalore"
        );

        Service s3 = new Service(
            "Cloud Migration",
            new Date(), "Active",
            "Cloud Service", 25000.00,
            "60 Days", "CloudMate India",
            "Chennai"
        );

        int id1 = insertService(s1);
        int id2 = insertService(s2);
        int id3 = insertService(s3);

        // ── Display All After Insert ──────────────────────────────────────────────
        System.out.println("\n--- All Records After INSERT ---");
        displayAllServices();

        // ── OPERATION II : HQL UPDATE using Named Parameters ─────────────────────
        System.out.println("\n>>> OPERATION II : Updating Name & Status using HQL Named Parameters...\n");

        // Update record 1
        System.out.println("  Updating ID = " + id1
                + " → Name: 'Web Dev & Maintenance', Status: 'Completed'");
        updateServiceByHQL(id1, "Web Dev & Maintenance", "Completed");

        // Update record 2
        System.out.println("\n  Updating ID = " + id2
                + " → Name: 'Advanced Network Setup', Status: 'Active'");
        updateServiceByHQL(id2, "Advanced Network Setup", "Active");

        // Try updating a non-existent ID
        System.out.println("\n  Updating ID = 9999 (non-existent)...");
        updateServiceByHQL(9999, "Ghost Service", "Inactive");

        // ── Display All After Update ──────────────────────────────────────────────
        System.out.println("\n--- All Records After HQL UPDATE ---");
        displayAllServices();

        // ── Shutdown ──────────────────────────────────────────────────────────────
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }

        System.out.println("\n=======================================================");
        System.out.println("  ALL HQL OPERATIONS COMPLETED SUCCESSFULLY            ");
        System.out.println("=======================================================");
    }
}

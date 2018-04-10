package com.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;



public class Main {
    static SessionFactory factory;
    public static void main(String[] args) {


        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Main mainCall = new Main();

        Integer intval = mainCall.addUser("Adam", "Alaba");


    }


    public Integer addUser(String fname, String lname) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;

        try {
            tx = session.beginTransaction();
            User user = new User(fname, lname);
//            Employee employee = new Employee(fname, lname, salary);
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }

    //update user
    // get users from db


}

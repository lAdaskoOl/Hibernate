package com.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;


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
        mainCall.listUsers();
        mainCall.updateUser(2);


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
    public void updateUser(Integer userID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, userID);
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // get users from db
    public void listUsers( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List users = session.createQuery("FROM User").list();
            for (Iterator iterator = users.iterator(); iterator.hasNext();){
                User user = (User) iterator.next();
                System.out.print("First Name: " + user.getFirstName());
                System.out.print("  Last Name: " + user.getLastName());
//                System.out.println("   ID: " + user.getId());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

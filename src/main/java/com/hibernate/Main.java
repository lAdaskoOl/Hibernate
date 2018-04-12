package com.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    static SessionFactory factory;
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {


        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Main mainCall = new Main();

        System.out.println("**************************");
        System.out.println("1. List users");
        System.out.println("2. Add a user");
        System.out.println("3. Change user's last name");
        System.out.println("4. Delete a user");
        System.out.println("**************************");
        System.out.println();
        System.out.print("Select your option: ");

        int input = in.nextInt();

        if(input == 1) {
            mainCall.listUsers();
            System.exit(10);
        }
        else if(input == 2) {
            System.out.print("New user's first name: ");
            String fname = in.next();
            System.out.print("New user's last name: ");
            String lname = in.next();
            mainCall.addUser(fname, lname);
            System.exit(10);
        }
        else if(input == 3) {
            System.out.print("Choose user's id: ");
            int id = in.nextInt();
            System.out.print("Change users las name to : ");
            String name = in.next();
            mainCall.updateUser(id, name);
            System.exit(10);
        }
        else if (input == 4) {
            System.out.print("Choose user's id: ");
            int id = in.nextInt();
            mainCall.deleteUser(id);
            System.exit(10);
        }
        else {
            System.out.println("Invalid option");
            System.exit(0);
        }
    }


    //add a user
    public Integer addUser(String fname, String lname) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;

        try {
            tx = session.beginTransaction();
            User user = new User(fname, lname);
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
    public void updateUser(Integer userID, String name){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, userID);
            user.setLastName(name);
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
                System.out.println();
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

    // Method to DELETE an employee from the records
    public void deleteUser(Integer UserID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, UserID);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

package com.hibernate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener{

    private JPanel main = new JPanel(new GridLayout(2,2));

    private JButton b1 = new JButton("List users");
    private JButton b2 = new JButton("Add user");
    private JButton b3 = new JButton("Change users last name");
    private JButton b4 = new JButton("Delete user");

    public MainWindow() {
        setSize(500, 500);
        setResizable(false);
        setTitle("Hibernate");
        setVisible(true);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        main.add(b1);
        main.add(b2);
        main.add(b3);
        main.add(b4);
        b1.setBackground(Color.white);
        b1.setForeground(Color.black);
        b2.setBackground(Color.white);
        b2.setForeground(Color.black);
        b3.setBackground(Color.white);
        b3.setForeground(Color.black);
        b4.setBackground(Color.white);
        b4.setForeground(Color.black);
        add(main);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

    }

    public static void main(String[] args) {
        new MainWindow();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == b1) {
            Main mainCall = new Main();
            //TODO: convert listUsers() into a string
//            JOptionPane.showMessageDialog(this, mainCall.listUsers());
        }
        else if(actionEvent.getSource() == b2) {
            //open new window
            new AddWindow();
        }
        else if(actionEvent.getSource() == b3) {
            //open new window
        }
        else {
            //open new window
            new DeleteWindow();
        }
    }
}


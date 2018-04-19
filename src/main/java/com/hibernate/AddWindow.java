package com.hibernate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JTextField fname, lname;
    private JButton button = new JButton("Add user");

    public AddWindow() {
        setSize(250, 500);
        setResizable(false);
        setTitle("Add user");
        setVisible(true);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        fname = new JTextField("Add first name");
        lname = new JTextField("Add last name");
        fname.setBounds(100, 200, 100, 20);
        lname.setBounds(100, 300, 100, 20);
        button.setBounds(100, 400, 100,20);
        panel.setBackground(Color.white);
        button.addActionListener(this);
        panel.add(fname);
        panel.add(lname);
        panel.add(button);
        add(panel);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == button) {
            String firstName = fname.getText();
            String lastName = lname.getText();
            Main mainCall = new Main();
            mainCall.addUser(firstName, lastName);
        }
    }
}

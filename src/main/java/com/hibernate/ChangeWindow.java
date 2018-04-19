package com.hibernate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeWindow extends JFrame implements ActionListener {

    private TextField id, newName;
    private JButton button = new JButton("Update user");
    private JPanel panel = new JPanel();

    public ChangeWindow() {
        setSize(250, 200);
        setResizable(false);
        setTitle("Update User");
        setVisible(true);
        setBackground(Color.white);
        setLocationRelativeTo(null);

        id = new TextField("User's ID");
        newName = new TextField("New name");
        id.setBounds(100, 100, 50, 20);
        newName.setBounds(100, 300, 100, 20);
        button.setBounds(100, 400, 100,20);
        button.addActionListener(this);
        panel.add(id);
        panel.add(newName);
        panel.add(button);
        add(panel);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == button) {
            String tx = id.getText();
            int IntID = Integer.parseInt(tx);
            String name = newName.getText();
            Main maincall = new Main();
            maincall.updateUser(IntID, name);
        }
    }
}

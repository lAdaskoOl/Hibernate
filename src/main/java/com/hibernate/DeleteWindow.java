package com.hibernate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteWindow extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private TextField id = new TextField("ID number");
    private JButton button = new JButton("Delete user with given ID");

    public DeleteWindow() {
        setSize(250, 500);
        setResizable(false);
        setTitle("Delete user");
        setVisible(true);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        id.setBounds(100, 100, 50, 20);
        button.setBounds(100, 200, 100, 20);
        panel.add(id);
        panel.add(button);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == button) {
            String tx = id.getText();
            int IntId = Integer.parseInt(tx);
            Main maincall = new Main();
            maincall.deleteUser(IntId);
        }
    }
}

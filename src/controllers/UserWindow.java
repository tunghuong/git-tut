package controllers;

import models.User;
import network.UserConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tungb on 12/29/2016.
 */
public class UserWindow extends JFrame implements ActionListener{
    private static final int WIDTH = 260;
    private static final int HEIGHT = 300;

    private JTextField scoreText;
    private JTextField nameText;
    private JButton saveButton;

    public UserWindow(){
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.GREEN);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        initComponent();
    }


    private void initComponent(){
        scoreText = new JTextField("Score: ");
        scoreText.setBounds(20, 5, 200, 40);
        scoreText.setForeground(Color.RED);
        this.add(scoreText);

        nameText = new JTextField();
        nameText.setBounds(20, 55, 200, 40);
        nameText.setText("Your name");
        this.add(nameText);

        saveButton = new JButton("Save");
        saveButton.setBounds(20, 105, 200, 40);
        saveButton.addActionListener(this);
        this.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            UserConnection.instance.connect();
            User user = new User(nameText.getText(), Integer.parseInt(scoreText.getText().trim()));
            UserConnection.instance.postUser(user);
        }
    }
}

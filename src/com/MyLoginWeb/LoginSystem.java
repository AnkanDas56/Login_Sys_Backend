package com.MyLoginWeb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

// I would use Other collection frameWorks if it were a search System
public class LoginSystem {
    private final HashMap<String, String> UserList = new LinkedHashMap<>();// the Username will be the key as we need to find the corresponding encoded password to the userName
    //We cant find the Keys from the values, so ,I made this.

    // My custom hasher and salt manager.
    private final SaltManager sm = new SaltManager();

    @Override
    public String toString() {
        String s = this.UserList.values().toString();
        return s;
    }

    public String addUser(String UserName, String Password) {
        return this.UserList.put(UserName, this.sm.encode(Password));
    }

    public void addUser(Person user) {
        this.UserList.put(user.UserName(), this.sm.encode(user.Password()));
    }

    public String searchUserName(String UserName) {
        if (this.UserList.containsValue(UserName)) return "Found = " + UserName;
        else if (this.UserList.containsValue(UserName.toLowerCase())) return "Found = " + UserName.toLowerCase();
        else if (this.UserList.containsValue(UserName.toUpperCase())) return "Found = " + UserName.toUpperCase();
        else return null;
    }

    public String getUserList() {
        return this.UserList.values().toString();
    }

    public boolean loginByPassword(String Password, String UserName) {
        boolean b;
        if (this.UserList.get(UserName) != null) {
            b = this.getSm().matches(Password, this.UserList.get(UserName));
            return b;
        } else if (this.UserList.get(UserList) == null || this.getSm().matches(Password, this.UserList.get(UserName)))
            return false;
        return false;
    }


    void main() {
        Person p1 = new Person("AnkanDas56", "AnkuBanku", "Hello, I am Ankan Das, a 12 y/o java developer aspiring a full stack developer seat in Facbook , Google, Amazon or ,Apple (most wanted co. is Apple)");
        Person p2 = new Person("Johnny", "None", "Look at my UserName, You will understand by yourself");
        this.addUser(p1);
        this.addUser(p2);
        System.out.println(this.loginByPassword(p1.Password(), p1.UserName()));

    }

    // getters for the encoder
    SaltManager getSm() {
        return sm;
    }
}


class LogInSystemGUI extends LoginSystem implements ActionListener {
    Runnable r;
    JPanel p = new JPanel(new GridLayout(3, 1));
    //The ones above were Variable declarations and the declatations coming below are methods
    JTextArea PArea = new JTextArea("Enter Your Password");
    JTextArea UNArea = new JTextArea("Enter Your UserName");

    public void go() {
        JFrame f = new JFrame("Login");
        f.setBackground(Color.black);
        LayoutManager mgr = new GridLayout(1, 200);
        this.UNArea.setLayout(mgr);
        this.PArea.setLayout(mgr);
        this.PArea.setCursor(new Cursor(4));
        this.UNArea.setEditable(true);
        this.UNArea.setBackground(Color.BLACK);
        this.UNArea.setForeground(Color.WHITE);
        this.PArea.setEditable(true);
        this.PArea.setBackground(Color.black);
        this.PArea.setForeground(Color.WHITE);
        this.p.add(UNArea, "North");
        this.p.add(PArea, "South");
        JButton b = new JButton("Login");
        b.setForeground(Color.green);
        b.addActionListener(this);
        this.p.add(b);
        f.add(p);
        this.p.setBackground(Color.black);
        f.setBackground(Color.black);
        f.setVisible(true);
        this.r = () -> this.UNArea.setText(Boolean.toString(this.loginByPassword(this.PArea.getText(), this.UNArea.getText())));
        if (this.loginByPassword(this.PArea.getText(), this.UNArea.getText())) {
            this.UNArea.setText("Welcome " + UNArea.getText() + " to your account");
            this.p.remove(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t = Thread.ofVirtual().unstarted(this.r);
        t.start();
        this.p.remove(this.PArea);
        this.UNArea.setEditable(false);

    }

}

record Person(String UserName, String Password, String bio) implements Comparable<Person> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i + 4 != this.Password().length()) {
            sb.append("*");
            i++;
        }
        String LastFour = this.Password.substring(this.Password.length() - 4);
        sb.append(LastFour);
        String s = "UserName = " + this.UserName() + " and , Password = " + sb;
        return s + "\n" + this.bio();
    }

    public int compareTo(Person p) {
        return this.UserName.compareTo(p.UserName);
    }
}

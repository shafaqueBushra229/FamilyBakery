package com.example.fyp.Model;

public class User {

    String mail,password;
    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }
    public User() {

    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.getrenovator;

public class Person {

    String first_name;
    String last_name;
    String mail;
    String pass;
    String phone;
    UserType userType;

    public Person(String first_name, String last_name, String mail, String phone, UserType userType) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.phone = phone;
        this.userType = userType;
    }

    public Person(){
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

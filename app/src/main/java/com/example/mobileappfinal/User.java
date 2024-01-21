package com.example.mobileappfinal;

public class User{
    public String email;
    public String password;
    public String name;
    public User(String email, String password, String name)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

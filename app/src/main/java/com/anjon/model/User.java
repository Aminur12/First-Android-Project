package com.anjon.model;

/**
 * Created by anjon on 5/11/2016.
 */
public class User {
    private String name;
    private String email;
    private String password;
    private String token;
    private long id;
    private String phone;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

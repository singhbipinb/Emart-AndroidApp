package com.emart.emartindia.models;

import com.google.gson.annotations.SerializedName;

/*
 * @author Bipin Singh
 */

public class Users {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String Name;

    @SerializedName("email")
    private String Email;

    @SerializedName("password")
    private String Password;

    @SerializedName("isAdmin")
    private Boolean isAdmin;


    @SerializedName("token")

    private String AuthToken;

    public Users() {

    }

    public Users(String id, String name, String email, String password, Boolean isAdmin, String authToken) {
        this.id = id;
        Name = name;
        Email = email;
        Password = password;
        this.isAdmin = isAdmin;
        AuthToken = authToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getAuthToken() {
        return AuthToken;
    }

    public void setAuthToken(String authToken) {
        AuthToken = authToken;
    }
}

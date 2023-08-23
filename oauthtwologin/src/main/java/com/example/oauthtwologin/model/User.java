package com.example.oauthtwologin.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Entity

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Hide field
    @Size(min = 6, max = 200, message = "password must be between 6 and 200 characters")
    private String password;

    @NotNull(message = "the username is required.")
    @Pattern(regexp = "[a-z0-9]+", message = "username can only consist of lowercase letters and number")
    private String username;

    @NotNull(message = "the email is required.")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "the full name is required.")
    private String full_name;
    private String image_url;
    private Boolean is_verified;
    private Boolean is_admin;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;


    public User(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public User(String password, String username, String email, String full_name, String image_url){
        this.password = password;
        this.username = username;
        this.email = email;
        this.full_name =full_name;
        this.image_url = image_url;
    }

    public User(String password, String username){
        this.password = password;
        this.username = username;
    }

}

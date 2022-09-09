package com.revature.yolp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id // primary key
    private String id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role = "DEFAULT";

    /*
     * @JsonMangedReference is the forward part of reference – the one that gets serialized normally.
     *      If we do not specify this, we will have an infinite recursion
     *          (Go ahead and try it without this, I dare you!)
     *
     * mappedby = Once we have defined the owning side of the relationship,
     *      Hibernate already has all the information it needs to map that relationship in our database.
     *
     * FetchType.EAGER = Eager Loading is a design pattern in which data initialization occurs on the spot.
     * FetchType.LAZY = Lazy Loading is a design pattern that we use to defer initialization of an object as long as it's possible.
     *
     * CascadeType.ALL = persistence will propagate (cascade) all EntityManager operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH) to the relating entities.
     */
    @OneToMany (
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Review> reviews;

    public User() {

    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toFileString() {
        return id + ":" + username + ":" + password + ":" + role + "\n";
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

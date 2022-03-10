package com.example.PP_311_SpringBoot.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String title;

    public Role() {
    }

    public Role(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}

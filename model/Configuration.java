package com.example.tomcattest.model;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {
    public Configuration() {
    }

    @Id
    @Column(name = "id")
    private int id;

    @Transient
    private Resolution resolution;


    public Configuration(Resolution resolution) {
        this.resolution = resolution;
    }

    public Resolution getResolution() {
        return resolution;
    }
}

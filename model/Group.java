package com.example.tomcattest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq")
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
//    @Transient

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentGroup")
    @JsonManagedReference
    private Group parentGroup;
    //    @Transient
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "subGroups")
    @JsonBackReference
    private List<Group> subGroups = new ArrayList<>();
    //    @Transient
    @OneToMany(mappedBy = "group")
    @JoinColumn(name = "parent")
    private List<Item> items = new ArrayList<>();

    public Group() {
    }

    public Group(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Group getParentGroup() {
        return parentGroup;
    }

    void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    public void addSubGroup(Group group) {
        this.subGroups.add(group);
        group.setParentGroup(this);
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.setGroup(this);
    }

    public void addItems(List<Item> items) {
        for (Item item : items) {
            addItem(item);
        }
    }

    public void print(int level) {
        System.out.printf("GROUP - id: {%d} {%s}%n", id, name);
        printSubGroups(++level);
        printItems(level);
    }

    private void printSubGroups(int level) {
//        String subLevelPrefix = "  ".repeat(level);
        for (Group group : subGroups) {
//            System.out.print(subLevelPrefix);
            group.print(level);
        }
    }

    private void printItems(int level) {
//        String subLevelPrefix = "  ".repeat(level);
        for (Item item : items) {
//            System.out.print(subLevelPrefix);
            item.print();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentGroup=" + parentGroup +
                ", subGroups=" + subGroups +
                ", items=" + items +
                '}';
    }
}

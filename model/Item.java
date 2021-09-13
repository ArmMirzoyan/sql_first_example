package com.example.tomcattest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "base_price")
    private int basePrice;
    @Column(name = "name")
    private String name;
    @Column(name = "currency")
    private  String currency;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Transient
//    @ManyToMany
//    @JoinTable (name="items_groups",
//            joinColumns=@JoinColumn (name="item_id"),
//            inverseJoinColumns=@JoinColumn(name="group_id"))
    private Group group;

    public Item() {

    }

    public Item(long id, int basePrice, String name) {
        this.id = id;
        this.basePrice = basePrice;
        this.name = name;
    }

    public Item(long id, int basePrice, String currency, String name) {
        this.id = id;
        this.basePrice = basePrice;
        this.currency = currency;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setGroup(Group group) {
        this.group = group;
    }

    public abstract int calculatePrice(Configuration configuration);

    public void print() {
        System.out.printf("ITEM(%s) - id: {%d} {%s} {%d}%n",
                this.getClass().getSimpleName(), id, name, basePrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{ " +
                "id =" + id +
                ", basePrice = " + basePrice +
                ", currency = " + currency +
                ", name = '" + name + '\'' +
                ", imageUrl = '" + imageUrl + '\'' +
                ", group = " + group +
                '}';
    }

}

package com.testJpa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NamedQuery(name = "Product.getAll", query = "SELECT p from Product p")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "measure")
    private String measure;

    @Column(name = "price")
    private Integer price;

    @Column(name = "count")
    private Integer count;

    @Column(name = "id_stock")
    private Integer id_stock;

    public Product() { }

    public Product(String name, String measure, Integer price, Integer count, Integer id_stock) {
        this.name = name;
        this.measure = measure;
        this.price = price;
        this.count = count;
        this.id_stock = id_stock;
    }

}

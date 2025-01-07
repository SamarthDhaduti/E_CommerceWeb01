package com.example.e_commerceweb01.Models;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* You can use @Data also instead of Getter and Setter */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;

    /* This cascade will help to create a category if not present while creating a product */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String imageUrl;


/*
Product Category
Relation = Cardinality

Product Category
1       1
M        1
P : C
M : 1
are you defining a fk constraint?
Category_id = act like a foreign key in the product table
fk constraints
when you delete a product, what do you want to do with that category?

1 1

In your product table, will you have a category id column?
 */



}

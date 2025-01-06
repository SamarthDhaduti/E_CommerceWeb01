package com.example.e_commerceweb01.Models;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/* You can use @Data also instead of Getter and Setter */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel {
    private String title;

    //duplicate relation(already mentioned in product class)
    @OneToMany (mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;

}

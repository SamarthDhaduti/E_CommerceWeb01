package com.example.e_commerceweb01.Repositories;

import com.example.e_commerceweb01.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* We are making this ProductRepositories an Interface because it can extend and get all the JPA query methos
from the JpaRepository interface.
one interface can extend another interface but can't implements */

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

    /* findAll() is a JPA query method */
    @Override
    List<Product> findAll();

    /* helps to save the product in to DB */
    Product save(Product p);

    /* to get a particular product using ID
    * and the reason for the Optional is that handles if product not present in the table
    * (to handle exceptions) */

    @Override
    Optional<Product> findById(Long id);
}

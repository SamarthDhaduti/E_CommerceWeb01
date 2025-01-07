package com.example.e_commerceweb01.Repositories;

import com.example.e_commerceweb01.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /* in the RequestBody of the CreateProduct we got the title of Category as String but in Product Model Category
    is an object of Category call so we will use findByTitle() JPA query method which will return a row in the table
    with that category name as a object so use it in the CreateProduct service
     */
    Category findByTitle(String title);
}

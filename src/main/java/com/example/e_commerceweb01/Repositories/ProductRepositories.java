package com.example.e_commerceweb01.Repositories;

import com.example.e_commerceweb01.Models.Category;
import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.Projections.ProductWithIdAndPriceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Override
    Page<Product> findAll(Pageable pageable);
     //pageNo, pageSize - data will given from the fronted
    //from which limit and which offset you need to fetch?
    //pageNo = 20, pageSize = 25
    //pageNo starts from 0, 20*25 = 500 retrieved earlier
    //20th page will show 501th to 526th products

    /* helps to save the product in to DB */
    Product save(Product p);

    /* to get a particular product using ID
    * and the reason for the Optional is that handles if product not present in the table
    * (to handle exceptions) */

    @Override
    Optional<Product> findById(Long id);

    /* some more JPA query methods */
    List<Product> findByCategory(Category category);

    /* Here in the above method we are passing whole category object to get the list of products
    associated with that Category but Will you always have the entire category object when you
    want to fetch all products with an associated category
     */

    /* just use title of category to */
    List<Product> findByCategory_Title(String title);

    /* using category id */
    List<Product> findByCategory_Id(long id);

     /*
    We don't have complete control over the query that JPA will execute for us?

    I am interested only in certain columns not on all the attributes of the table, I can provide the query
    with required attributes, Here the Custom Queries comes in to picture.

    There are two types of Custom queries that JPA can be done:
    1.HQL - Similar to SQL but with a small pinch of OOP.(Hibernate Query Language)
    2.Native SQL queries

    But to use custom queries we need "Projections" an interface which contains only getter methods
     */

    /* HQL method */
    @Query("select p.id, p.price from Product p where p.category.title = :categoryName")
    List<ProductWithIdAndPriceProjection> getProductTitlesAndPricesAndAGivenCategoryName(@Param("categoryName") String categoryName);

    /* Native SQL Query */
     @Query(value = "select * from products p where p.title = :title", nativeQuery = true)
    List<ProductWithIdAndPriceProjection> getIdAndPricesOfAllProductsWithGivenTitle(@Param("title") String title);
}

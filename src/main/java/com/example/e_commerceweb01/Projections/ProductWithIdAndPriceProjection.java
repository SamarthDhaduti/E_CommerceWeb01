package com.example.e_commerceweb01.Projections;

/* to use custom queries we need "Projections" an interface which contains only getter methods */
public interface ProductWithIdAndPriceProjection {
    long getId();
    double getPrice();
}

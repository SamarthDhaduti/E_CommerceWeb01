package com.example.e_commerceweb01.dtos;

import lombok.*;
/* this is to send request body to APIs To our DB */
/* You can use @Data also instead of Getter and Setter */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDto {
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

}

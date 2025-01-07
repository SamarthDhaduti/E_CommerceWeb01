package com.example.e_commerceweb01.dtos;

import lombok.Getter;
import lombok.Setter;

/* To send the response coming from the ControllerAdvices to client as an object */
@Getter
@Setter
public class ErrorDto {
    private String message;
}

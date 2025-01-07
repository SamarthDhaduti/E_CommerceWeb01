package com.example.e_commerceweb01.ControllerAdvice;

import com.example.e_commerceweb01.Exception.ProductNotFoundException;
import com.example.e_commerceweb01.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* we created this class because when an output comes from service to controller
it contains all the data including confidential info, to avoid that before sending
the output to client through controller directly controller advice helps to beautify
the things includes things only we want to show in output (in error or exception messages
 */
/* an this will return String but as output we must send object through a DTO so we created
ErrorDto and also we need some addition info we want through Response Entity

 */
@ControllerAdvice
public class ControllerAdvices {
    /* This will tell spring that wherever in my project this ProductNotFoundException comes maps that
    to here to handle it
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
         ErrorDto errorDto = new ErrorDto();
         errorDto.setMessage(productNotFoundException.getMessage());

         ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
         return responseEntity;
    }
}

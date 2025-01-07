package com.example.e_commerceweb01.Exception;

/* There are two types of Exceptions
1.Checked Exceptions : CompileTime Exceptions :Java will force to handle these kind of exceptions,
                       extended directly from Exception class
2.Unchecked Exceptions: Runtime Exceptions : we no need to handle this kind of exceptions forcefully,
                        extended from RuntimeException class
As of now we extend Exception class */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        /* This super(message) will send this to Exception class */
        super(message);
    }
}

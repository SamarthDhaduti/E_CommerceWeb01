package com.example.e_commerceweb01.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/* here we are creating configuration of RestTemplate and returning it via a method annotation by @Baen
because spring doesn't create a object of RestTemplate on its own so this will tell spring to create an object
RestTemplate and store it in Application context and inject it whenever needed
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

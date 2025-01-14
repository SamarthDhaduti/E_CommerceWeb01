package com.example.e_commerceweb01.Services;

import com.example.e_commerceweb01.Models.Product;
import com.example.e_commerceweb01.dtos.CreateProductRequestDto;
import com.example.e_commerceweb01.dtos.FakeStoreProductDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    /* creating the instance of RestTemplate to work with 3rd Party API */

    private RestTemplate restTemplate ; //using this, you will be able to call 3rd party apis

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        /* here we are getting objects of FakeStoreProductDto in the form of array */
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products" , FakeStoreProductDto[].class) ;
        /* we need the out put in the form of list of Products so create a ArrayList of products */
        List<Product> products = new ArrayList<>();
        /* using for each loop add objects from array to ArrayList */
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product p = fakeStoreProductDto.toProduct();  /* converting fakeStoreProductDto object in to Product */
            products.add(p);
        }
        return products;
    }

    /* call the external API to get single product
       https://fakestoreapi.com/products/1

       here we are creating object of FakeStoreProductDto because from here data will send to FakeStoreProductDto
       as an object and convert to Product and return to controller
     */
    @Override
    public Product getSinleProduct(long id) {
        /* This will return only the data object without any additional info */
//       FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
//        return fakeStoreProductDto.toProduct();

        /* Now we will see Response entity which will contain additional info such as status code, Header etc.. */
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/"+ id ,  FakeStoreProductDto.class);

//        if(fakeStoreProductDtoResponseEntity.getStatusCode() != HttpStatus.valueOf(200)){
//            // Handel this exception
//        }

        //fakeStoreProductDtoResponseEntity.getHeaders();

        /* this will get the body along with data into fakeStoreProductDto object */
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto == null){
            return null;
        }

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(String title,
                                 double price,
                                 String description,
                                 String imageUrl,
                                 String category) {
        /* To send this request body to FakeStore we use an object of FakeStoreProductDto */
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);

        /* This object fakeStoreProductDto1 is to receive from FakeStore */
        FakeStoreProductDto fakeStoreProductDto1 =  restTemplate.postForObject("https://fakestoreapi.com/products" ,
                fakeStoreProductDto, FakeStoreProductDto.class);

        return fakeStoreProductDto1.toProduct();

         /*
        POST /products actually doesn't create a new object in the fakestore
        It's just a dummy api, it does nothing
         */
    }

    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {
        return null;
    }
}

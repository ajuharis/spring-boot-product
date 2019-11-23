package io.haris.ProductMicroService.resources;

import io.haris.ProductMicroService.dto.ProductDTO;
import io.haris.ProductMicroService.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource {
    @Autowired
    ProductServiceImpl productService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    List<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    ProductDTO getProduct(@PathVariable("productId")Long productId){
        return productService.getProductById(productId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getByC/{categoryId}", method = RequestMethod.GET)
    List<ProductDTO> getProductsByCategory(@PathVariable("categoryId")Long categoryId){
        return productService.getProductsByCategory(categoryId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void addProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
    }
}

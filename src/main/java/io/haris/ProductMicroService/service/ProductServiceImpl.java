package io.haris.ProductMicroService.service;

import io.haris.ProductMicroService.dto.ProductDTO;
import io.haris.ProductMicroService.model.Category;
import io.haris.ProductMicroService.model.Product;
import io.haris.ProductMicroService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    public ProductRepository productRepository;
    @Override
    public void createProduct(ProductDTO productDTO) {
        productRepository.save(toEntity(productDTO));
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        productRepository.delete(toEntity(productDTO));
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = (ArrayList<Product>) productRepository.findAll();
        return products.stream().map(product -> toDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent())
            return toDTO(product.get());
        else return new ProductDTO();
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        List<Product> products = (ArrayList<Product>) productRepository.findProductByCategoryId(categoryId);
        return products.stream().map(product -> toDTO(product))
                .collect(Collectors.toList());
    }

    private Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStatus(productDTO.getStatus());
        product.setCreatedAt(productDTO.getCreatedAt());
        product.setUpdatedAt(productDTO.getUpdatedAt());
        Category category = new Category();
        category.setId(productDTO.getCategoryId());
        product.setCategory(category);
        return product;
    }

    private ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setStatus(product.getStatus());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        return productDTO;
    }
}

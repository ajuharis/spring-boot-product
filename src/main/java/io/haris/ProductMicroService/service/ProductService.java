package io.haris.ProductMicroService.service;

import io.haris.ProductMicroService.dto.ProductDTO;

import java.util.Collection;

public interface ProductService {
    public void createProduct(ProductDTO productDTO);
    public void deleteProduct(ProductDTO productDTO);
    public Collection<ProductDTO> getProducts();
    public ProductDTO getProductById(Long productId);
    public Collection<ProductDTO> getProductsByCategory(Long categoryId);
}

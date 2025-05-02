package com.example.ecoit2.service;

import com.example.ecoit2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Iterable<Product> getAllProduct();
    Page<Product> findWithPagination(Pageable pageable);
    Product saveProduct(Product product);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
    Product findInternById(Integer id);
}

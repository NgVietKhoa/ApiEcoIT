package com.example.ecoit2.service;

import com.example.ecoit2.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public interface ProductService {

    Iterable<Product> getAllProduct();
    Page<Product> findWithPagination(Pageable pageable);
    Product saveProduct(Product product);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
    Product findInternById(Integer id);

    Page<Product> searchAndFilter(
            String productName,
            String productType,
            String description,
            Date startDate,
            String status,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy,
            Pageable pageable);
}

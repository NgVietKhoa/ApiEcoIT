package com.example.ecoit2.service.impl;

import com.example.ecoit2.entity.Product;
import com.example.ecoit2.repository.ProductRepo;
import com.example.ecoit2.repository.specs.ProductSpecification;
import com.example.ecoit2.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Iterable<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Page<Product> findWithPagination(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sản phẩm với ID " + id + " không tìm thấy"));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductType(product.getProductType());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStartDate(product.getStartDate());
        existingProduct.setStatus(product.getStatus());
        existingProduct.setCreatedAt(product.getCreatedAt() != null ? product.getCreatedAt() : LocalDateTime.now());
        existingProduct.setCreatedBy(product.getCreatedBy() != null ? product.getCreatedBy() : 1);
        existingProduct.setUpdatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt() : LocalDateTime.now());
        existingProduct.setUpdatedBy(product.getUpdatedBy() != null ? product.getUpdatedBy() : 1);

        return productRepo.save(existingProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        if (!productRepo.existsById(id)) {
            throw new EntityNotFoundException("Sản phẩm với ID " + id + " không tìm thấy");
        }
        productRepo.deleteById(id);
    }

    @Override
    public Product findInternById(Integer id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sản phẩm với ID " + id + " không tìm thấy"));
    }

    @Override
    public Page<Product> searchAndFilter(String productName, String productType, String description, Date startDate, String status, LocalDateTime createdAt, Integer createdBy, LocalDateTime updatedAt, Integer updatedBy, Pageable pageable) {
        return productRepo.findAll(
                ProductSpecification.filterProducts(
                        productName, productType, description, startDate, status,
                        createdAt, createdBy, updatedAt, updatedBy),
                pageable);
    }
}

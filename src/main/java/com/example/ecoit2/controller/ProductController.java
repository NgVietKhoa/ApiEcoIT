package com.example.ecoit2.controller;

import com.example.ecoit2.entity.InternWorkSchedule;
import com.example.ecoit2.entity.Product;
import com.example.ecoit2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Iterable<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getInternsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        return ResponseEntity.ok(productService.findWithPagination(pageable));
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleted(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchAndFilter(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime createdAt,
            @RequestParam(required = false) Integer createdBy,
            @RequestParam(required = false) LocalDateTime updatedAt,
            @RequestParam(required = false) Integer updatedBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        Page<Product> result = productService.searchAndFilter(
                productName, productType, description, startDate, status,
                createdAt, createdBy, updatedAt, updatedBy, pageable);
        return ResponseEntity.ok(result);
    }
}

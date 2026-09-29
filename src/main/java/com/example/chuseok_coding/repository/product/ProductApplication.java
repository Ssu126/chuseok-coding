package com.example.chuseok_coding.repository.product;

import com.example.chuseok_coding.controller.internal.api.dto.ProductResponseDto;
import com.example.chuseok_coding.service.product.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductApplication {
    private final ProductService productService;

    public List<ProductResponseDto> retrieve() {
        List<Product> products = productService.getProducts();
        return products.stream()
            .map(ProductResponseDto::from)
            .toList();
    }

    public ProductResponseDto retrieve(Integer id) {
        Product retrieved = productService.getProduct(id);
        return ProductResponseDto.from(retrieved);
    }
}

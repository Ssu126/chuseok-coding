package com.example.chuseok_coding.service.product;

import com.example.chuseok_coding.controller.internal.api.dto.ProductResponseDto;
import com.example.chuseok_coding.repository.product.Product;
import com.example.chuseok_coding.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> retrieve() {
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(ProductResponseDto::from)
            .toList();
    }

    public ProductResponseDto retrieve(Integer id) {
        Optional<Product> wrappedProduct = productRepository.findById(id);
        Product         product = wrappedProduct
            .orElseThrow(() -> new RuntimeException("찾으시는 유저가 존재하지 않습니다"));
        return ProductResponseDto.from(product);
    }
}

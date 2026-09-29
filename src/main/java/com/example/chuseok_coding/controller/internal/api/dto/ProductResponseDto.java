package com.example.chuseok_coding.controller.internal.api.dto;

import com.example.chuseok_coding.repository.product.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductResponseDto {
    private final Integer id;
    private final String name;
    private final int price;
    private final int stock;

    public static ProductResponseDto from(Product entity) {
        return new ProductResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getStock()
        );
    }
}

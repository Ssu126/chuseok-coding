package com.example.chuseok_coding.application.product;

import com.example.chuseok_coding.controller.internal.api.dto.ProductResponseDto;
import java.util.List;

public interface IProductApplication {
    List<ProductResponseDto> retrieve();
    ProductResponseDto retrieve(Integer id);
}

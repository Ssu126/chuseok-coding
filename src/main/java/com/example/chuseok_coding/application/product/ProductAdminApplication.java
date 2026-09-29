package com.example.chuseok_coding.application.product;

import com.example.chuseok_coding.controller.admin.api.dto.ProductAdminResponseDto;
import com.example.chuseok_coding.controller.admin.api.dto.ProductAdminUpsertRequestDto;
import com.example.chuseok_coding.repository.product.Product;
import com.example.chuseok_coding.service.product.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAdminApplication {

    private final ProductService productService;

    public List<ProductAdminResponseDto> retrieve() {
        List<Product> products = productService.getProducts();
        return products.stream()
            .map(ProductAdminResponseDto::from)
            .toList();
    }

    public ProductAdminResponseDto retrieve(Integer id) {
        Product retrieved = productService.getProduct(id);
        return ProductAdminResponseDto.from(retrieved);
    }

    public ProductAdminResponseDto create(Product entity) {
        Product created = productService.create(entity);
        return ProductAdminResponseDto.from(created);
    }

    public ProductAdminResponseDto update(Integer id, ProductAdminUpsertRequestDto request) {
        Product updating = productService.getProduct(id);
        updating.update(request.getName(), request.getPrice(), request.getStock());
        Product updated = productService.update(updating);
        return ProductAdminResponseDto.from(updated);
    }

    public void active(Integer id) {
        productService.active(id);
    }

    public void softDelete(Integer id) {
        productService.softDelete(id);
    }

    public void hardDelete(Integer id) {
        productService.hardDelete(id);
    }
}

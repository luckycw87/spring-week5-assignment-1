package com.codesoom.assignment.product.common;

import com.codesoom.assignment.product.controller.ProductDto;
import com.codesoom.assignment.product.domain.Product;
import com.codesoom.assignment.product.application.ProductCommand;

import java.util.UUID;

public class ProductFactory {
    public static Product createProduct() {
        return Product.builder()
                .name("고양이 장난감")
                .maker("제조사")
                .price(randomPrice())
                .imageUrl(UUID.randomUUID().toString() + ".png")
                .build();
    }

    public static Product createProduct(Long id) {
        Product.ProductBuilder product = Product.builder();

        System.out.println(product.toString());

        product.id(id)
                .name("고양이 장난감" + id)
                .maker("제조사" + id)
                .price(randomPrice())
                .imageUrl(UUID.randomUUID().toString() + ".png")
                .build();

        return product.build();
    }

    public static ProductDto.RequestParam createRequestParam() {
        ProductDto.RequestParam request = new ProductDto.RequestParam();
        request.setName("고양이 장난감");
        request.setMaker("제조사");
        request.setPrice(randomPrice());
        request.setImageUrl(UUID.randomUUID().toString() + ".png");

        return request;
    }

    public static ProductCommand.Register of(Product product) {
        ProductCommand.Register.RegisterBuilder registerBuilder = ProductCommand.Register.builder();

        System.out.println(registerBuilder.toString());

        return registerBuilder.name(product.getName())
                .maker(product.getMaker())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static ProductCommand.UpdateReq of(Long id, Product product) {
        return ProductCommand.UpdateReq.builder()
                .id(id)
                .name(product.getName())
                .maker(product.getMaker())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static Long randomPrice() {
        return (long) (Math.ceil((Math.random() * 10000 + 10000) / 1000) * 1000);
    }
}

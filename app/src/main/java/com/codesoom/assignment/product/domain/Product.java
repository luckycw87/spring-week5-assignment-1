package com.codesoom.assignment.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@lombok.Generated
@Entity
@Getter
@ToString(of = {"id", "name", "maker", "price", "imageUrl"})
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String maker;

    private Long price;

    private String imageUrl;

    protected Product() {
    }

    @Builder
    public Product(
            Long id,
            String name,
            String maker,
            Long price,
            String imageUrl
    ) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }

        if (StringUtils.isEmpty(maker)) {
            throw new IllegalArgumentException("제조사가 비어있습니다.");
        }
        if (price == null) {
            throw new IllegalArgumentException("가격이 비어있습니다.");
        }

        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product modifyProduct(Product product) {
        this.name = product.getName();
        this.maker = product.getMaker();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();

        return this;
    }
}


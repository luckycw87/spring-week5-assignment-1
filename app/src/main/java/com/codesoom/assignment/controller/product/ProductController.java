package com.codesoom.assignment.controller.product;

import com.codesoom.assignment.application.product.ProductCommand;
import com.codesoom.assignment.application.product.ProductCommandService;
import com.codesoom.assignment.application.product.ProductInfo;
import com.codesoom.assignment.application.product.ProductQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    private final ProductCommandService productCommandService;

    private final ProductQueryService productQueryService;

    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInfo> list() {
        return productQueryService.getProducts().stream()
                .map(ProductInfo::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductInfo detail(@PathVariable Long id) {
        return new ProductInfo(productQueryService.getProduct(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductInfo registerProduct(@RequestBody @Valid ProductDto.RequestParam request) {
        final ProductCommand.Register command = ProductFactory.of(request);
        return new ProductInfo(productCommandService.createProduct(command));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductInfo updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto.RequestParam request) {
        final ProductCommand.UpdateRequest command = ProductFactory.of(id, request);
        return new ProductInfo(productCommandService.updateProduct(command));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productCommandService.deleteProduct(id);
    }
}

package com.example.act_non.product.controller;

import com.example.act_non.product.model.Product;
import com.example.act_non.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<?> searchAndFindAllProduct(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String price,
                                                     @RequestParam(required = false) String typeName,
                                                     @RequestParam(required = false) String description
    ) {
        Pageable pageable;
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        } else {
            pageable = Pageable.unpaged();
        }
        Page<Product> productList = productService.findAllProduct(pageable, name, price, typeName, description);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(required = false) Integer id){
        Product product = productService.getProductById(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }
}

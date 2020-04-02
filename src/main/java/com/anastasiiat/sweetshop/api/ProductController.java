package com.anastasiiat.sweetshop.api;

import com.anastasiiat.sweetshop.dto.ProductDTO;
import com.anastasiiat.sweetshop.dto.ProductPageDTO;
import com.anastasiiat.sweetshop.error.InvalidProductOperationException;
import com.anastasiiat.sweetshop.persistence.entity.Product;
import com.anastasiiat.sweetshop.service.ProductPagingService;
import com.anastasiiat.sweetshop.service.ProductService;
import com.anastasiiat.sweetshop.util.JSONUtils;
import com.anastasiiat.sweetshop.validator.ProductValidator;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPagingService productPagingService;

    @Autowired
    private ProductValidator productValidator;

    @GetMapping("/api/getProducts")
    public @ResponseBody
    Iterable<ProductDTO> getProductsWithImages() {
        List<Product> products = Lists.newArrayList(productService.getAllProducts());
        return fillProductsDTO(products);
    }

    @GetMapping("/api/getProductsPage")
    public @ResponseBody
    ProductPageDTO getProductsPage(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        Page<Product> products = productPagingService.findProducts(pageIndex, pageSize);
        List<Product> productList = Lists.newArrayList(products);
        return new ProductPageDTO(fillProductsDTO(productList), products.getTotalPages());
    }

    @GetMapping("/api/filterProducts/{categoryId}")
    public @ResponseBody
    Iterable<ProductDTO> filterProducts(@PathVariable("categoryId") Integer categoryId) {
        List<Product> products = Lists.newArrayList(productService.findByСategoryId(categoryId));
        return fillProductsDTO(products);
    }

    @RequestMapping(value = "/admin/saveProductWithImg", method = RequestMethod.POST)
    public Product saveProduct(@RequestParam("editableProduct") String productJSON, @RequestParam("file") MultipartFile file) {
        Product product = JSONUtils.jsonToObject(productJSON, Product.class);
        saveImage(file, product);
        return productService.saveProduct(product);
    }

    @RequestMapping(value = "/admin/saveProduct", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @RequestMapping(value = "/api/getProductById/{productId}", method = RequestMethod.GET)
    public ProductDTO saveProduct(@PathVariable("productId") Integer productId) {
        return buildProductDTO(productService.findProductById(productId));
    }

    @RequestMapping(value = "/admin/deleteProduct", method = RequestMethod.POST)
    public void deleteProduct(@RequestBody Product product) throws InvalidProductOperationException {
        productValidator.validateToDelete(product.getProductId());
        productService.deleteProduct(product.getProductId());
    }

    private void saveImage(MultipartFile file, Product editableProduct) {
        productService.saveImageToStorage(file, editableProduct);
    }

    private List<ProductDTO> fillProductsDTO(List<Product> products) {
        List<ProductDTO> result = new ArrayList<>();
        for(Product product : products) {
            result.add(buildProductDTO(product));
        }
        return result;
    }

    private ProductDTO buildProductDTO(Product product) {
        byte[] imageContent = productService.getProductImageContent(product);
        return new ProductDTO(product, imageContent);
    }
}

package com.project.controller;

import com.project.Util.FileUploadUtil;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.service.CategoryService;
import com.project.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/viewHomePage")
    public List<Product> viewHomePage() {
        return productService.findPaginated(1, 3, "id", "asc").getContent();
    }

    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(required = false) String keyword) {
        return productService.getAllProduct(keyword);
    }

    @GetMapping("/showNewProductForm")
    public Product createNewProduct() {
        return new Product();
    }

    /**
     * Crear un nuevo producto
     * @param product
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/products")
    public Product saveProduct(@ModelAttribute("product") Product product,
                               @RequestParam("image") MultipartFile multipartFile,
                               @RequestParam("categoryId") Long categoryId) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhotos(fileName);


        Optional<Category> category = categoryService.findOneById(categoryId);
        product.setCategory(category.orElseThrow(() -> new RuntimeException("Category not found")));

        Product savedProduct = productService.saveProduct(product);
        String uploadDir = "product-photos/" + savedProduct.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return savedProduct;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @ModelAttribute("product") Product product,
                                 @RequestParam("image") MultipartFile multipartFile,
                                 @RequestParam("categoryId") Long categoryId) throws IOException {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhotos(fileName);



        Optional<Category> category = categoryService.findOneById(categoryId);
        product.setCategory(category.orElseThrow(() -> new RuntimeException("Category not found")));

        product.setId(id);
        Product updatedProduct = productService.saveProduct(product);
        String uploadDir = "product-photos/" + updatedProduct.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return updatedProduct;
    }

    @GetMapping("/showFormForUpdate/{id}")
    public Product updateImage(@PathVariable(value = "id") long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page/{pageNo}")
    public Map<String, Object> findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                             @RequestParam("sortField") String sortField,
                                             @RequestParam("sortDir") String sortDir) {
        int pageSize = 3;
        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Product> listProduct = page.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("currentPage", pageNo);
        response.put("totalPages", page.getTotalPages());
        response.put("totalItems", page.getTotalElements());
        response.put("sortField", sortField);
        response.put("sortDir", sortDir);
        response.put("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        response.put("listProduct", listProduct);

        return response;
    }

}

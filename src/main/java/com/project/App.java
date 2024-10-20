package com.project;

import com.project.entity.*;
import com.project.enums.KARDEX_TRANSACTION;
import com.project.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        ApplicationContext context = SpringApplication.run(App.class, args);
        SupplierRepository supplierRepo = context.getBean(SupplierRepository.class);
        CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
        UserRepository userRepo = context.getBean(UserRepository.class);
        KardexRepository kardexRepo = context.getBean(KardexRepository.class);
        ProductRepository productRepo = context.getBean(ProductRepository.class);

        if (supplierRepo.count() == 0) {
            List<Supplier> suppliers = List.of(
                    new Supplier(null, "Supplier1", "supplier1@gmail", true, "15478936", "74526"),
                    new Supplier(null, "Supplier2", "supplier2@gmail", false, "1247896", "4125963"),
                    new Supplier(null, "Supplier3", "supplier3@gmail", false, "126985", "415263"),
                    new Supplier(null, "Supplier4", "supplier4@gmail", false, "748596", "1025312"),
                    new Supplier(null, "Supplier5", "supplier5@gmail", false, "1478592", "4178596")
            );
            supplierRepo.saveAll(suppliers);
        }

        if (categoryRepo.count() == 0) {
            List<Category> categories = List.of(
                    new Category(null, "Categoria1", "Buen estado", true),
                    new Category(null, "Categoria2", "Buen estado", false),
                    new Category(null, "Categoria3", "Buen estado", true),
                    new Category(null, "Categoria4", "Buen estado", false),
                    new Category(null, "Categoria5", "Buen estado", true),
                    new Category(null, "Categoria6", "Buen estado", false),
                    new Category(null, "Categoria7", "Buen estado", false),
                    new Category(null, "Categoria8", "Buen estado", true)
            );
            categoryRepo.saveAll(categories);
        }

        if (userRepo.count() == 0) {
            List<User> users = List.of(
                    new User(null, "User1", "7485961", "user1@gmail.com", "12334", true),
                    new User(null, "User2", "345345", "user2@gmail.com", "65443", true),
                    new User(null, "User3", "7234234", "user3@gmail.com", "56757", false),
                    new User(null, "User4", "4564564", "user4@gmail.com", "678678", true),
                    new User(null, "User5", "6878667", "user5@gmail.com", "123123", true)
            );
            userRepo.saveAll(users);
        }

        if (productRepo.count() == 0) {
            Category category_product_1 = categoryRepo.findById(1L).orElseThrow();
            List<Product> products = List.of(
                    new Product(null, "Product1", "codigo", category_product_1, "Description", 23.4, 12, "image1.webp", true)
            );
            productRepo.saveAll(products);

            Supplier supplier_kardex_1 = supplierRepo.findById(1L).orElseThrow();
            Supplier supplier_kardex_2 = supplierRepo.findById(2L).orElseThrow();
            Supplier supplier_kardex_3 = supplierRepo.findById(3L).orElseThrow();

            User user_kardex_1 = userRepo.findById(1L).orElseThrow();
            User user_kardex_2 = userRepo.findById(2L).orElseThrow();
            User user_kardex_3 = userRepo.findById(3L).orElseThrow();

            Product product_kardex_1 = productRepo.findById(1L).orElseThrow();

            List<Kardex> kardexes = List.of(
                    new Kardex(null, LocalDate.now(), LocalDate.now(), KARDEX_TRANSACTION.ENTRADA, 23, "descripcion1",
                            supplier_kardex_1, user_kardex_1, product_kardex_1),
                    new Kardex(null, LocalDate.now(), LocalDate.now().plusDays(30),
                            KARDEX_TRANSACTION.CADUCIDAD, 10, "descripcion2", supplier_kardex_2, user_kardex_2, product_kardex_1),
                    new Kardex(null, LocalDate.now(), LocalDate.now().plusMonths(2),
                            KARDEX_TRANSACTION.SALIDA, 50, "descripcion3", supplier_kardex_3, user_kardex_3, product_kardex_1)
            );
            kardexRepo.saveAll(kardexes);
        }
    }
}
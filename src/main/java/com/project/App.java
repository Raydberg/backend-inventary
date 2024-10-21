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
                    new Supplier(null, "Jose Miguel", "MiguelJose@gmail.com", true, "923953799", "74894280"),
                    new Supplier(null, "Carlos Ruiz", "carlos.ruiz@example.com", false, "993456789", "64899280"),
                    new Supplier(null, "Sofia Martinez", "sofia.martinez@example.com", true, "983756789", "54898280"),
                    new Supplier(null, "Andres Ortiz", "andres.ortiz@example.com", true, "973656789", "74894280"),
                    new Supplier(null, "Isabel Romero", "isabel.romero@example.com", false, "953356789", "74853280")
            );
            supplierRepo.saveAll(suppliers);
        }

        if (categoryRepo.count() == 0) {
            List<Category> categories = List.of(
                    new Category(null, "Analgesicos", "Buen estado", true),
                    new Category(null, "Antibioticos", "Buen estado", false),
                    new Category(null, "Preservativos", "Buen estado", true),
                    new Category(null, "Laxantes", "Buen estado", false),
                    new Category(null, "Lubricantes", "Buen estado", true),
                    new Category(null, "Antipireticos", "Buen estado", false),
                    new Category(null, "Antiinflamatorios", "Buen estado", false),
                    new Category(null, "Diureticos", "Buen estado", true)
            );
            categoryRepo.saveAll(categories);
        }

        if (userRepo.count() == 0) {
            List<User> users = List.of(
                    new User(null, "Jhon Rodriguez Quezada", "75894980", "I202314115@cibertec.edu.pe", "986310261", true),
                    new User(null, "Roger Concepción León", "74834230", "I202314538@cibertec.edu.pe", "978318805", true),
                    new User(null, "Raydberg Gabriel Chuquival Gil", "79824880", "I202317109@cibertec.edu.pe",
                            "923456789",
                            false),
                    new User(null, "Maria Fernanda Rojas", "54894580", "mariaFr@gmail.com", "923456789", true),
                    new User(null, "Ana Belen Rosaura", "64894200", "BRosauraAna@gmail.com", "923456789", true)
            );
            userRepo.saveAll(users);
        }

        Category category_product_1 = categoryRepo.findById(1L).orElseThrow();
        Category category_product_2 = categoryRepo.findById(2L).orElseThrow();
        Category category_product_3 = categoryRepo.findById(3L).orElseThrow();
        Category category_product_4 = categoryRepo.findById(4L).orElseThrow();
        Category category_product_5 = categoryRepo.findById(5L).orElseThrow();

        List<Product> products = List.of(
                new Product(null, "Paracetamol 500mg", "PRD001", category_product_1, "Analgesico y antipiretico", 5.99, 12
                        , "image1.webp", true),
                new Product(null, "Ibuprofeno 400mg", "PRD002", category_product_2, "Antiinflamatorio no " +
                        "esteroideo", 23.4, 50, "image2.jpg", true),
                new Product(null, "Amoxicilina 500mg", "PRD003", category_product_3, "Antibiotico de amplio " +
                        "espectro", 23.4, 5, "image3.png", true),
                new Product(null, "Durex Lubricante", "PRD004", category_product_4, "Lubricante íntimo a base de " +
                        "agua", 23.4, 36, "image4.jpg", true),
                new Product(null, "K-Y Gel", "PRD005", category_product_5, "Lubricante íntimo a base de gel",
                        23.4, 50, "image5.jpg", true)
        );
        productRepo.saveAll(products);

        Supplier supplier_kardex_1 = supplierRepo.findById(1L).orElseThrow(() -> new RuntimeException("Supplier not found"));

        Supplier supplier_kardex_2 = supplierRepo.findById(2L).orElseThrow();
        Supplier supplier_kardex_3 = supplierRepo.findById(3L).orElseThrow();

        User user_kardex_1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        User user_kardex_2 = userRepo.findById(2L).orElseThrow();
        User user_kardex_3 = userRepo.findById(3L).orElseThrow();

        Product product_kardex_1 = productRepo.findById(1L).orElseThrow();
        Product product_kardex_2 = productRepo.findById(2L).orElseThrow();
        Product product_kardex_3 = productRepo.findById(3L).orElseThrow();

        List<Kardex> kardexes = List.of(
                new Kardex(null, LocalDate.now(), LocalDate.now(), KARDEX_TRANSACTION.ENTRADA, 23, "Descripcion1",
                        product_kardex_1.getNameProduct(), supplier_kardex_1, user_kardex_1),
                new Kardex(null, LocalDate.now(), LocalDate.now(), KARDEX_TRANSACTION.ENTRADA, 23, "Descripcion2",
                        product_kardex_2.getNameProduct(), supplier_kardex_2, user_kardex_2),
                new Kardex(null, LocalDate.now(), LocalDate.now(), KARDEX_TRANSACTION.ENTRADA, 23, "Descripcion3",
                        product_kardex_3.getNameProduct(), supplier_kardex_3, user_kardex_3)
        );
        kardexRepo.saveAll(kardexes);

    }
}
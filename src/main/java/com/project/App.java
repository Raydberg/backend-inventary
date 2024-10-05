package com.project;

import com.project.entity.*;
import com.project.enums.KARDEX_TRANSACTION;
import com.project.enums.Roles;
import com.project.enums.STATUS;
import com.project.repository.CategoryRepository;
import com.project.repository.KardexRepository;
import com.project.repository.SupplierRepository;
import com.project.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.setAdditionalProfiles("DemoJasperReportApplication");
        ApplicationContext context = SpringApplication.run(App.class, args);
        SupplierRepository supplierRepo = context.getBean(SupplierRepository.class);
        CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
        UserRepository userRepo = context.getBean(UserRepository.class);
        KardexRepository kardexRepo = context.getBean(KardexRepository.class);

        List<Supplier> suppliers = List.of(
                new Supplier(null, "Supplier1", "supplier1@gmail", true, "15478936", STATUS.ACTIVO),
                new Supplier(null, "Supplier2", "supplier2@gmail", false, "1247896", STATUS.ACTIVO),
                new Supplier(null, "Supplier3", "supplier3@gmail", false, "126985", STATUS.ACTIVO),
                new Supplier(null, "Supplier4", "supplier4@gmail", false, "748596", STATUS.ACTIVO),
                new Supplier(null, "Supplier5", "supplier5@gmail", false, "1478592", STATUS.ACTIVO)
        );
        supplierRepo.saveAll(suppliers);

        List<Category> categories = List.of(
                new Category(null, "Categoria1", "Buen estado", true, STATUS.ACTIVO),
                new Category(null, "Categoria2", "Buen estado", false, STATUS.ACTIVO),
                new Category(null, "Categoria3", "Buen estado", true, STATUS.ACTIVO),
                new Category(null, "Categoria4", "Buen estado", false, STATUS.ACTIVO),
                new Category(null, "Categoria5", "Buen estado", true, STATUS.ACTIVO),
                new Category(null, "Categoria6", "Buen estado", false, STATUS.ACTIVO),
                new Category(null, "Categoria7", "Buen estado", false, STATUS.ACTIVO),
                new Category(null, "Categoria8", "Buen estado", true, STATUS.ACTIVO)
        );
        categoryRepo.saveAll(categories);

        List<User> users = List.of(
                new User(null, "User1", "7485961", "user1@gmail.com", Roles.ADMIN, "12334", STATUS.ACTIVO),
                new User(null, "User2", "345345", "user2@gmail.com", Roles.EMPLOYEE, "65443", STATUS.ACTIVO),
                new User(null, "User3", "7234234", "user3@gmail.com", Roles.ADMIN, "56757", STATUS.ACTIVO),
                new User(null, "User4", "4564564", "user4@gmail.com", Roles.EMPLOYEE, "678678", STATUS.ACTIVO),
                new User(null, "User5", "6878667", "user5@gmail.com", Roles.ADMIN, "123123", STATUS.ACTIVO)
        );
        userRepo.saveAll(users);

        // Fetch suppliers from the database to ensure they are managed by the current session
        Supplier supplier_kardex_1 = supplierRepo.findById(suppliers.get(0).getId()).orElseThrow();
        Supplier supplier_kardex_2 = supplierRepo.findById(suppliers.get(1).getId()).orElseThrow();
        Supplier supplier_kardex_3 = supplierRepo.findById(suppliers.get(2).getId()).orElseThrow();

        // Fetch users from the database to ensure they are managed by the current session
        User user_kardex_1 = userRepo.findById(users.get(0).getId()).orElseThrow();
        User user_kardex_2 = userRepo.findById(users.get(1).getId()).orElseThrow();
        User user_kardex_3 = userRepo.findById(users.get(2).getId()).orElseThrow();

        // Add test data to Kardex
        List<Kardex> kardexes = List.of(
                new Kardex(null, LocalDate.now(), LocalDate.now(), "producto1", KARDEX_TRANSACTION.ENTRADA, 23, "descripcion1",
                        supplier_kardex_1, user_kardex_1),
                new Kardex(null, LocalDate.now(), LocalDate.now().plusDays(30), "producto2", KARDEX_TRANSACTION.CADUCIDAD, 10, "descripcion2", supplier_kardex_2, user_kardex_2),
                new Kardex(null, LocalDate.now(), LocalDate.now().plusMonths(2), "producto3", KARDEX_TRANSACTION.SALIDA, 50, "descripcion3", supplier_kardex_3, user_kardex_3)
        );
        kardexRepo.saveAll(kardexes);
    }
}
package com.project;

import com.project.entity.Category;
import com.project.entity.Supplier;
import com.project.repository.CategoryRepository;
import com.project.repository.SupplierRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        SupplierRepository supplierRepo = context.getBean(SupplierRepository.class);
        CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
        List<Supplier> suppliers = List.of(
                new Supplier(null, "Supplier1", "supplier1@gmail", true, "15478936"),
                new Supplier(null, "Supplier2", "supplier2@gmail", false, "1247896"),
                new Supplier(null, "Supplier3", "supplier3@gmail", false, "126985"),
                new Supplier(null, "Supplier4", "supplier4@gmail", false, "748596"),
                new Supplier(null, "Supplier5", "supplier5@gmail", false, "1478592")


        );
        supplierRepo.saveAll(suppliers);

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

}

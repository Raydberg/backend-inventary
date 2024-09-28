package com.project;

import com.project.entity.Category;
import com.project.entity.Roles;
import com.project.entity.Supplier;
import com.project.entity.User;
import com.project.repository.CategoryRepository;
import com.project.repository.SupplierRepository;
import com.project.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.management.relation.Role;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        SupplierRepository supplierRepo = context.getBean(SupplierRepository.class);
        CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
        UserRepository userRepo = context.getBean(UserRepository.class);
        
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
     
        List<User> users = List.of(
                new User(null,"User1","7485961","user1@gmail.com", Roles.ADMIN,"12334"),
                new User(null,"User2","345345"," user2@gmail.com", Roles.EMPLOYEE,"65443"),
                new User(null,"User3","7234234","user3@gmail.com", Roles.ADMIN,"56757"),
                new User(null,"User4","4564564","user4@gmail.com", Roles.EMPLOYEE,"678678"),
                new User(null,"User5","6878667","user5@gmail.com", Roles.ADMIN,"123123")
        );
        userRepo.saveAll(users);

    }

}

package com.project;

import com.project.entity.*;
import com.project.repository.CategoryRepository;
import com.project.repository.KardexRepository;
import com.project.repository.SupplierRepository;
import com.project.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        SupplierRepository supplierRepo = context.getBean(SupplierRepository.class);
        CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
        UserRepository userRepo = context.getBean(UserRepository.class);
        KardexRepository kardexRepo = context.getBean(KardexRepository.class);

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

        /**
         * AÑADIR DATOS DE PRUEBA PARA KARDEX
         */
        Supplier supplier_kardex_1 = new Supplier(null,"supplier_kardex_1","supplier_kardex_1@gmail.com",true,"78945664");
        Supplier supplier_kardex_2 = new Supplier(null,"supplier2_kardex_3","supplier_kardex_2@gmail.com",false,"95847588");
        Supplier supplier_kardex_3 = new Supplier(null,"supplier3_kardex_2","supplier_kardex_3@gmail.com",true,"98745632");
        User user_kardex_1 = new User(null,"user_kardex_1","789456","user_kardex_1@gmail.com",Roles.ADMIN,"97845641");
        User user_kardex_2 = new User(null,"user_kardex_2","789456","user_kardex_2@gmail.com",Roles.EMPLOYEE,"123698751");
        User user_kardex_3 = new User(null,"user_kardex_3","1452369877","user_kardex_3@gmail.com",Roles.ADMIN,"145859");
        List<Kardex> kardexes = List.of(
                new Kardex(null, LocalDate.now(), LocalDate.now(),"producto1","ENTRADA",23,"descripcion1",supplier_kardex_1,user_kardex_1),
                new Kardex(null, LocalDate.now(), LocalDate.now().plusDays(30),"producto2","SALIDA",10,"descripcion2",supplier_kardex_2,user_kardex_2),
                new Kardex(null, LocalDate.now(), LocalDate.now().plusMonths(2),"producto3","ENTRADA",50,"descripcion3",supplier_kardex_3,user_kardex_3)
        );
        kardexRepo.saveAll(kardexes);


    }

}

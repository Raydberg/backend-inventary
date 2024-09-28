package com.project.service;

import com.project.entity.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers(){
       return  repository.findAll();
   }
   public Optional<User> findOneById(Long id){
       return repository.findById(id);
   }
  public User create(User user){
       return repository.save(user);
  }
  public User update(Long id,User user){
       user.setId(id);
       return repository.save(user);
  }
  public void delete(Long id){
       repository.deleteById(id);
  }


}

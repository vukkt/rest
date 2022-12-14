package com.restapi.app.rest.Controller;

import com.restapi.app.rest.Models.User;
import com.restapi.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {


    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }


    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved...";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updateUser = userRepo.findById(id).get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAge(user.getAge());
        updateUser.setOccupation(user.getOccupation());
        userRepo.save(updateUser);
        return "Updated...";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with the id: " + id;
    }

}

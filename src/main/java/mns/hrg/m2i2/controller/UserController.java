package mns.hrg.m2i2.controller;

import lombok.AllArgsConstructor;
import mns.hrg.m2i2.dao.entity.User;
import mns.hrg.m2i2.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        try {
            userService.createUser(user.getPsoeudo(), user.getPassword(), user.isAdmin());
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contrainte violée en base");
        }
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getAll();
    }
}

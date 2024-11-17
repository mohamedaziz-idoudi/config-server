package org.example.user_microservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.user_microservice.entities.User;
import org.example.user_microservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint pour récupérer tous les utilisateurs.
     * Méthode: GET
     * URL: http://localhost:8082/users
     */
    @GetMapping
    @CircuitBreaker(name = "usermicroService")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Endpoint pour récupérer un utilisateur par son ID.
     * Méthode: GET
     * URL: http://localhost:8082/users/{id}
     */
    @GetMapping("/{id}")
    @CircuitBreaker(name = "usermicroService")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint pour créer un nouvel utilisateur.
     * Méthode: POST
     * URL: http://localhost:8082/users
     */
    @PostMapping
    @CircuitBreaker(name = "usermicroService")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Endpoint pour mettre à jour un utilisateur existant.
     * Méthode: PUT
     * URL: http://localhost:8082/users/{id}
     */
    @PutMapping("/{id}")
    @CircuitBreaker(name = "usermicroService")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint pour supprimer un utilisateur par son ID.
     * Méthode: DELETE
     * URL: http://localhost:8082/users/{id}
     */
    @DeleteMapping("/{id}")
    @CircuitBreaker(name = "usermicroService")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

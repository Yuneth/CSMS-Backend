package com.meta.csms_backend.controller;

import com.meta.csms_backend.dto.UserRequest;
import com.meta.csms_backend.dto.UserResponse;
import com.meta.csms_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

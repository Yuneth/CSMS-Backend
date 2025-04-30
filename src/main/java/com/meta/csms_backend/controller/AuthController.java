package com.meta.csms_backend.controller;

import com.meta.csms_backend.dto.UserRequest;
import com.meta.csms_backend.dto.UserResponse;
import com.meta.csms_backend.entity.User;
import com.meta.csms_backend.repository.UserRepository;
import com.meta.csms_backend.security.JwtUtil;
import com.meta.csms_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(originPatterns = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername());
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
//        if(authentication!=null){
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String userToken=jwtUtil.generateToken(request.getUsername());
            UserResponse userResponse=new UserResponse(user.getId(), user.getUsername(),userToken);
//        }
//        userResponse.se(jwtUtil.generateToken(request.getUsername()));
        return userResponse;
    }
}

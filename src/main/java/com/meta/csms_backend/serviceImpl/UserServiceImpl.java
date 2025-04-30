package com.meta.csms_backend.serviceImpl;

import com.meta.csms_backend.dto.UserRequest;
import com.meta.csms_backend.dto.UserResponse;
import com.meta.csms_backend.entity.User;
import com.meta.csms_backend.repository.UserRepository;
import com.meta.csms_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // For password hashing

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user = userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userRequest.getUsername());
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user = userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}

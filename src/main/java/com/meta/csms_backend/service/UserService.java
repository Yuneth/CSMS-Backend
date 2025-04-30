package com.meta.csms_backend.service;

import com.meta.csms_backend.dto.UserRequest;
import com.meta.csms_backend.dto.UserResponse;
import com.meta.csms_backend.entity.User;

import java.util.List;

public interface UserService {
    //User findByUsername (String username);
    //User register (UserRequest request);
    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
}

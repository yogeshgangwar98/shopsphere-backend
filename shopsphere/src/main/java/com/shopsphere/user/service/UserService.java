package com.shopsphere.user.service;

import com.shopsphere.user.dto.UserCreateRequest;
import com.shopsphere.user.dto.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    void deleteUser(Long id);
}

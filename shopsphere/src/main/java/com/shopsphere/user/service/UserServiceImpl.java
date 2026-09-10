package com.shopsphere.user.service;

import com.shopsphere.common.exception.DuplicateResourceException;
import com.shopsphere.common.exception.ResourceNotFoundException;
import com.shopsphere.user.dto.UserCreateRequest;
import com.shopsphere.user.dto.UserResponse;
import com.shopsphere.user.entity.User;
import com.shopsphere.user.entity.UserRole;
import com.shopsphere.user.entity.UserStatus;
import com.shopsphere.user.mapper.UserMapper;
import com.shopsphere.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    public UserRepository userRepository;
    public UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("User already exists.");
        }
        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(UserRole.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        Instant now = Instant.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);

    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {throw new ResourceNotFoundException("User Not Found: "+id);});
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> {throw new ResourceNotFoundException("User Not Found: "+id);});
        userRepository.delete(user);
//        userRepository.findById(id).ifPresentOrElse(
//                user -> userRepository.delete(user),
//                () -> new ResourceNotFoundException("User Not Found: "+id)
//        );
    }
}

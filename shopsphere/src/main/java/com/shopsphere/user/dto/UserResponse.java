package com.shopsphere.user.dto;

import com.shopsphere.user.entity.UserRole;
import com.shopsphere.user.entity.UserStatus;

import java.time.Instant;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        UserRole userRole,
        UserStatus userStatus,
        Instant createdAt
) {
}

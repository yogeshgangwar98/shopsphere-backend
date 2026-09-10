package com.shopsphere.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank
        @Size(max=50)
        String firstName,


        @NotBlank
        @Size(max=50)
        String lastName,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 0, max = 100)
        String password
) {

}

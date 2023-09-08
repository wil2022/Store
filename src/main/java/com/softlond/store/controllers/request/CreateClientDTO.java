package com.softlond.store.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDTO {

    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String document;

    private Set<String> roles;

}

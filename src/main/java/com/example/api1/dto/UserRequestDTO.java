package com.example.api1.dto;

import jakarta.validation.constraints.*;

public record UserRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @Min(value = 0, message = "Idade inválida")
        Integer idade

) {}
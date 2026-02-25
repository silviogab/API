package com.example.api1.dto;

public record UserResponseDTO(
        Long id,
        String nome,
        String email,
        Integer idade
) {}

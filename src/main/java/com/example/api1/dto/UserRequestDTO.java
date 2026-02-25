package com.example.api1.dto;

public record UserRequestDTO(
        String nome,
        String email,
        Integer idade
) {}
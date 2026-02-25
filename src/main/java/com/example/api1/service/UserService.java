package com.example.api1.service;

import com.example.api1.domain.User;
import com.example.api1.dto.UserRequestDTO;
import com.example.api1.dto.UserResponseDTO;
import com.example.api1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO create(UserRequestDTO dto) {

        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (dto.idade() == null || dto.idade() < 18) {
            throw new RuntimeException("Usuário deve ser maior de idade");
        }

        User user = new User();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setIdade(dto.idade());

        User saved = repository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getNome(),
                saved.getEmail(),
                saved.getIdade()
        );
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getNome(),
                        user.getEmail(),
                        user.getIdade()
                ))
                .toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getIdade()
        );
    }

    public UserResponseDTO update(Long id, UserRequestDTO dto) {

        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existing.setNome(dto.nome());
        existing.setEmail(dto.email());
        existing.setIdade(dto.idade());

        User updated = repository.save(existing);

        return new UserResponseDTO(
                updated.getId(),
                updated.getNome(),
                updated.getEmail(),
                updated.getIdade()
        );
    }

    public void delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado");

        repository.delete(user);
    }

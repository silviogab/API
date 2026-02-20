package com.example.api1.service;

import com.example.api1.domain.User;
import com.example.api1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (user.getIdade() == null || user.getIdade() < 18) {
            throw new RuntimeException("Usuário deve ser maior de idade");
        }

        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public User update(Long id, User user) {
        User existing = findById(id);

        existing.setNome(user.getNome());
        existing.setEmail(user.getEmail());
        existing.setIdade(user.getIdade());

        return repository.save(existing);
    }

    public void delete(Long id) {
        User user = findById(id);
        repository.delete(user);
    }
}



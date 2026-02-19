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

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User update(Long id, User user) {
        User existing = repository.findById(id)
                .orElseThrow();

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}



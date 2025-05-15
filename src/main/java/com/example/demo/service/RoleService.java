package com.example.demo.service;

import com.example.demo.model.Role;
import java.util.List;

import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Role findByName(String name);
    Optional<Role> findById(Long id);  // <- добавь этот метод
}


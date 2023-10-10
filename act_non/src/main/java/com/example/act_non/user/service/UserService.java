package com.example.act_non.user.service;


import com.example.act_non.user.model.Roles;
import com.example.act_non.user.model.UserPrinciple;
import com.example.act_non.user.model.Users;
import com.example.act_non.user.model.dto.UserDTO;
import com.example.act_non.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (Users u : iUserRepository.findAll()) {
            userDTOS.add(toDTO(u));
        }
        return userDTOS;
    }

    public UserDTO findById(Long id) {
        Optional<Users> user = iUserRepository.findById(id);
        return user.map(this::toDTO).orElse(null);
    }

    public Users findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    public boolean add(Users user) {
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        iUserRepository.save(user);
        return true;
    }

    public void delete(Long id) {
        iUserRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String username) {
        Users user = iUserRepository.findByUsername(username);
        if (user != null) {
            return UserPrinciple.build(user);
        }
        return null;
    }

    public UserDTO toDTO(Users user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}

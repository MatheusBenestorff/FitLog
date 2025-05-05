package com.benestorff.FitLog_spring_app.service;

import com.benestorff.FitLog_spring_app.model.User;
import com.benestorff.FitLog_spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return UserRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return UserRepository.findById(id);
    }

    public User save(User user) {
        return UserRepository.save(user);
    }

    public void deleteById(Long id) {
        UserRepository.deleteById(id);
    }
}

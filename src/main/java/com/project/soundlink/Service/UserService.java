package com.project.soundlink.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.soundlink.Entity.User;
import com.project.soundlink.Repository.UserRepository;
import com.project.soundlink.dto.RegisterRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Add service methods here to interact with UserRepository
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    //Login operation 
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    //C-UD operations
    //Create (Register) operation
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User registerUser(RegisterRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword(), request.getAge());
        return saveUser(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

}

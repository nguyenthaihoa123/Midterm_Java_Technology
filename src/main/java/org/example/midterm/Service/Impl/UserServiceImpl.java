package org.example.midterm.Service.Impl;

import org.example.midterm.Service.UserService;
import org.example.midterm.model.Role;
import org.example.midterm.model.User;
import org.example.midterm.repository.RoleRepository;
import org.example.midterm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean save(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findByName("ROLE_USER");
            if(role == null){
                role = checkRoleExist();
            }
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUserName(String name) {
        return userRepository.findByName(name);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}

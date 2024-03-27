package org.example.midterm.Service.Impl;


import org.example.midterm.model.Role;
import org.example.midterm.model.User;
import org.example.midterm.repository.RoleRepository;
import org.example.midterm.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("Test User");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(new Role("ROLE_USER"));
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        assertTrue(userService.save(user));
        verify(roleRepository, times(1)).findByName("ROLE_USER");
        verify(userRepository, times(1)).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("Test User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertEquals(user, userService.getById(1L));
    }

    @Test
    void testDeleteUserById() {
        assertTrue(userService.deleteById(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        assertEquals(user, userService.getByEmail("test@example.com"));
    }

    @Test
    void testGetUserByUserName() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");

        when(userRepository.findByName("Test User")).thenReturn(user);

        assertEquals(user, userService.getByUserName("Test User"));
    }
}

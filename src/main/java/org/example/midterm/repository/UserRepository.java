package org.example.midterm.repository;

import org.example.midterm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findByName(String name);
}

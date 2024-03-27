package org.example.midterm.Service;

import org.example.midterm.model.User;

public interface UserService {
    boolean save(User user);
    User getById(Long id);

    boolean deleteById(Long id);

    User getByEmail(String email);

    User getByUserName(String name);
}

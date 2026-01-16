package com.accounting.data;

import com.accounting.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getUserById(int userId);
    User getByUserName(String username);
    int getIdByUsername(String username);
    User create(User user);
    boolean exists(String username);
}

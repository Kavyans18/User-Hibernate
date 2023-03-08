package com.pw.User.service;

import com.pw.User.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User upadteUser(User user, int id);
    User getUserById(int id);
    List<User> getAllUsers();
    void deleteUser(int id);
}

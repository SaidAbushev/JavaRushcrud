package com.crud.spring.service;

import com.crud.spring.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(int id);
    public User getUserId(int id);
    public List<User> listUsers();
    public List<User> getAllUsers(String userName);
}

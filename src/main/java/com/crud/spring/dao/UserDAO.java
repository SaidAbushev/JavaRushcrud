package com.crud.spring.dao;

import com.crud.spring.model.User;

import java.util.List;

/**
 * Created by Саид on 10.04.2017.
 */
public interface UserDAO {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(int id);
    public User getUserId(int id);
    public List<User> listUsers();
    public List<User> getAllUsers(String userName);
}

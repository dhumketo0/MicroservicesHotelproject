package com.lcwd.userService.Services;

import com.lcwd.userService.entities.User;

import java.util.List;

public interface UserService{
    User saveUser(User user);
    List<User> getAllUsers();
    User getUSer(String UserId);

}

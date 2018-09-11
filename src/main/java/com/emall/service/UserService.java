package com.emall.service;

import com.emall.model.User;

public interface UserService {

    User getUser(String username, String password);
}

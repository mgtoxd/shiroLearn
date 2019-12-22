package pers.mtx.service;

import pers.mtx.domain.User;

public interface UserService {
    public User findByName(String name);
}

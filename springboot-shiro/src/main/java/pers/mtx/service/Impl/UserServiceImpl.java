package pers.mtx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mtx.domain.User;
import pers.mtx.mapper.UserMapper;
import pers.mtx.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    //注入Mapper接口
    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}

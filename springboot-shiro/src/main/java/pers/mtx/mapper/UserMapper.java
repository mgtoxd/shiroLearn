package pers.mtx.mapper;

import org.springframework.context.annotation.Bean;
import pers.mtx.domain.User;
public interface UserMapper {
    public User findByName(String name);
    public User findById(Integer id);
}

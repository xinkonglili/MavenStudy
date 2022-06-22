package com.jinli.dao;

import com.jinli.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();
    User getUserId(int a); //User{id=1, name='Jinli', pwd='123'}
    //插入一个用户
    int insertUser(User user);

    int updateUser(User user);

}

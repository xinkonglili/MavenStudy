package com.jinli.dao;

import com.jinli.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> getUserList();
    User getUserId(int a); //User{id=1, name='Jinli', pwd='123'}
    //插入一个用户
    int insertUser(User user);

    //万能的map
    int insertUser01(Map<String,Object> map);

    int updateUser(User user);

    int deleteUser(int id);

}

package com.jinli.dao;

import com.jinli.pojo.User;
import com.jinli.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test(){
        //第一步：获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //第二步、执行sql  方式一：getMapper（）
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();
        //方式二，sqlSession.selectList
//        List<User> userList = sqlSession.selectList("com.jinli.dao.UserDao.getUserList");
        for (User user : userList) {
            System.out.println(user);
        }
        //第三步：关闭资源
        sqlSession.close();
    }

    @Test
    public void getUserId(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User userId = userDao.getUserId(1);
        System.out.println(userId);
        sqlSession.close();
    }
    @Test
    public void insertUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int number = mapper.insertUser(new User(3,"lili","567"));
        if(number>0){
            System.out.println("用户添加成功");
        }

        //数据库事务的一致性,一定要commit()
        sqlSession.commit();
        sqlSession.close(); 
    }
}

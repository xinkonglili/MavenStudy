package com.jinli.dao;

import com.jinli.pojo.User;
import com.jinli.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void insertUser01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String, Object> map = new HashMap<String,Object>();
        //通过map可以随意制造参数，只传id或者name都是可以的，如果有的时候我们并不需要去修改用户的用户名和id的情况下，我们只传一个密码记好了，map就可以很好的实现这个功能
        //如果通过对象，要把对象所有的属性都要写出来，就会非常冗余
        //假设我们的实体类中的字段参数过多的情况下，我们应该过多的考虑map
        //创建一个map对象：Map<String, Object> map = new HashMap<String,Object>();
        map.put("Userid",5);
        map.put("Username","JinLi");
        map.put("Userpwd","000");
        int i = mapper.insertUser01(map);
        if (i>0){
            System.out.println("使用map添加用户成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int updateUser = mapper.updateUser(new User(3, "lph", "890")); //更新已有用户
        if(updateUser>0){
            System.out.println("更新用户成功 ");
        }


        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        int userNumber = mapper.deleteUser(3);
        if (userNumber>0){
            System.out.println("删除成功");
        }


        sqlSession.commit();
        sqlSession.close();

    }
}

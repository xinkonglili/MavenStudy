
## 一、基础知识
### 1、编写mybatis工具类
```aidl
public class MybatisUtils {
    //提升作用域
    public static SqlSessionFactory sqlSessionFactory;
    static{
        try {
            //使用Mybatis第一步：获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

/**
 * 既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
 SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
 你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。**/

//创建一个可以执行sql语句的对象
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();;

    }
}
```
### 2、编写代码-实体类--->实体类的实现类
- 编写实体类
- 编写dao接口
- 编写dao接口实现类（调用接口实现类就可以去操作实体类了）

### 3、两种方法的对比
- 之前是写一个接口，编写一个实现类，引用jdbc的代码
![img.png](imgs/img.png)
- mybatis去掉了jdbc的手动配置
   
### 4、每一个Mapper.xml都需要在核心配置文件中注册
```aidl
    <mappers>
       <mapper class="com/jinli/mapper/UserMapper.xml"/>
    </mappers>
```
### 5、二种方式
```aidl
       //第二步、执行sql 
       //方式一：getMapper（）
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();
      //方式二，sqlSession.selectList
       List<User> userList = sqlSession.selectList("com.jinli.dao.UserDao.getUserList");
```

### 6、三步走
- 1、先写工具类-（不用变）
- 2、去resources里面写mybatis-config.xml配置文件（以后可以不用改变）
- 3、写实体类（不用变）
- 4、写接口
- 5、写Mapper.xml
- 6、最后写test
- 7、配置maven
##  二、TIPS
![img.png](imgs/img.png)



##  三、Questions
### 1、mybatis可能会遇到的问题
- 配置文件没有注册
    - The error may exist in SQL Mapper Configuration //加入以下代码
    - ```aidl
    
         <build>
                <resources>
                    <resource>
                        <directory>src/main/resources</directory>
                        <includes>
                            <include>**/*.xml</include>
                        </includes>
                        <filtering>true</filtering>
                    </resource>
    
                <resource>
                    <directory>src/main/java</directory>
                    <includes>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>true</filtering>
                </resource>
      
            </resources>
        </build>



- 绑定接口错误
- 方法名不对
- 返回类型不对
- maven导出资源的问题

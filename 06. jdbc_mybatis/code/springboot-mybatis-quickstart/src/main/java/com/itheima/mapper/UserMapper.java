package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //这里表示用mybatis简化jdbc，
// 应用程序在运行时, 会自动将该实现类对象存入IOC容器 - bean，也就是说你可以用@Autowired 快速创建它
public interface UserMapper {

    /**
     * 查询所有用户
     * MyBatis 极其聪明。它看你的返回值是 List<User>，它就会自动去反射 User 这个类。
     */
    //@Select("select id, username, password, name, age from user") 这个写在了xml文件里
    public List<User> findAll();

    /**
     * 根据ID删除用户
     */
    @Delete("delete from user where id = #{id}")
    //public void deleteById(Integer id);
    public Integer deleteById(Integer id);

    /**
     * 新增用户
     */
    @Insert("insert into user(username, password, name, age) values (#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    /**
     * 更新用户
     */
    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public void update(User user);

    /**
     * 根据用户名和密码查询用户信息
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    //public User findByUsernameAndpassword(@Param("username") String username, @Param("password") String password);
    public User findByUsernameAndpassword(String username, String password);

}

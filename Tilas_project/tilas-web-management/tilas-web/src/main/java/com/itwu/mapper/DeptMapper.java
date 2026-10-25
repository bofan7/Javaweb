package com.itwu.mapper;

import com.itwu.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     *
     */
    @Select("select id,name,create_time,update_time from dept order by update_time desc;")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //id是主键，可以自增，不用管
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);//一个类传多个参数

    @Select("select id,name,create_time,update_time from dept where id =#{id};")
    Dept getById(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id};")
    void update(Dept dept);
}

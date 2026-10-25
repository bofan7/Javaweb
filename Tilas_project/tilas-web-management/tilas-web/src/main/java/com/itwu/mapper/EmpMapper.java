package com.itwu.mapper;

import com.itwu.pojo.*;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /**
     * 原始分页查询的实现
     *
     * @Select("select count(*) from emp e left join dept d on
     * e.dept_id=d.id;")//查询员工数量
     * public Long count();
     * 
     * @Select("select e.*,d.name deptName from emp e left join dept d on
     * e.dept_id=d.id order by update_time desc limit #{start},#{pageSize};")
     * public List<Emp> list(Integer start,Integer pageSize);
     * //由于这里用了d.name,所以Emp类里需要加上对应的参数，由于“name”已经被用了，则取个别名：deptName
     * 
     */

    /** 现在主流的分页查询函数list()会借助了PageHelper类 */
    // 0.注意不能sql加分号; 否则会报错
    // 1. 注意这里：没有 LIMIT 子句了！
    // 2. 依然保留 order by (排序逻辑 PageHelper 不会帮你加，得自己写)
    // 3. 依然保留 deptName 别名 (为了封装到 Emp 对象)
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id
    // = d.id where order by update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);
    // 4. 注意这里：不需要传 start 和 pageSize

    /**
     * 新增员工
     */

    @Options(useGeneratedKeys = true, keyProperty = "id") // 开启主键返回功能
    /**
     * 这行注解 @Options(useGeneratedKeys = true, keyProperty = "id") 相当于告诉 MyBatis：
     * useGeneratedKeys = true
     * 人话：“麻烦数据库大哥，插完这条数据后，把你自动生成的那个身份证号（主键 ID）拿给我。”
     * keyProperty = "id"：
     * 人话：“拿回来的这个 ID，请帮我填到 emp 对象的 id 属性里去。”
     */
    @Insert("insert into emp(username,salary,password,name,gender,image,job,entry_date,dept_id,create_time,update_time,phone) values(#{username},#{salary},#{password},#{name},#{gender},#{image},#{job},#{entryDate},#{deptId},#{createTime},#{updateTime},#{phone})")
    public void insert(Emp emp);

    /**
     * 批量删除员工
     */
    void delete(@Param("ids") List<Integer> ids);

    /**
     * 查询员工工作信息
     */
    public Emp getById(Integer id);

    /**
     * 根据用户名和密码查询员工
     * 
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 更新员工信息
     */
    public void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * 
     * @return
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * 
     * @return
     */
    List<Map<String, Object>> countEmpGenderData();
}

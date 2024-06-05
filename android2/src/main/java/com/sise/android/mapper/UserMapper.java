package com.sise.android.mapper;

import com.sise.android.pojo.Customer;
import com.sise.android.pojo.LoginInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    /**
     * 登录
     * @param no
     * @return
     */
    @Select("select * from customer, class where customer.classId = class.id and no = #{no} limit 1;")
    Customer login(@Param("no")String no);

    /**
     * 修改密码
     * @param password
     * @return
     */
    @Update("update customer set password = #{password} where id = #{id} ")
    int changePassword(@Param("password")String password, @Param("id")String id);

    /**
     * 查找该学号学生
     * @param no
     * @return
     */
    @Select("select * from customer, class where customer.classId = class.id and no = #{no} and role = 0 limit 1;")
    Customer findStudentInfoBySno(@Param("no")String no);

    /**
     * 通过id修改学生信息
     * @param
     * @return
     */
    @Update("update customer set password = #{password} , classId = #{classId} , phone = #{phone} where id = #{id} ")
    int updateStudentInfoById(@Param("password")String password, @Param("classId")String classId, @Param("phone")String phone, @Param("id")String id);

    /**
     * 更新用户头像通过用户id
     * @param name
     * @param id
     * @return
     */
    @Update("update customer set avatar = #{name} where id = #{id}")
    int updateCustomerAvatarById(@Param("name") String name, @Param("id") String id);
}

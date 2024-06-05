package com.sise.android.service;

import com.sise.android.pojo.Customer;
import com.sise.android.pojo.LoginInfo;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Map;

public interface UserService {
    /**
     * 登录
     */
    Customer login(LoginInfo loginInfo);

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    int changePassword(String password, String id);


    /**
     * 查看学生信息
     * @param sno
     * @return
     */
    Customer findStudentInfoBySno(String sno);

    /**
     * 老师修改学生信息
     * @param
     * @return
     */
    int updateStudentInfoById(Map<String, String> map);

    /**
     * 更新用户头像
     * @param name
     * @param id
     * @return
     */
    int updateCustomerAvatarById(MultipartRequest request, String id);
}

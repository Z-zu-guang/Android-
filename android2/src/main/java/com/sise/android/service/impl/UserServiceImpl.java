package com.sise.android.service.impl;

import com.sise.android.mapper.UserMapper;
import com.sise.android.pojo.Customer;
import com.sise.android.pojo.LoginInfo;
import com.sise.android.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional(rollbackForClassName = "Exception")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public Customer login(LoginInfo loginInfo) {
        Customer customer = userMapper.login(loginInfo.getNo());
        if (customer != null && customer.getPassword().equals(loginInfo.getPassword())) {

            return customer;
        }
        return null;
    }

    @Override
    public int changePassword(String password, String id) {
        return userMapper.changePassword(password, id);
    }

    @Override
    public Customer findStudentInfoBySno(String sno) {
        return userMapper.findStudentInfoBySno(sno);
    }

    @Override
    public int updateStudentInfoById(Map<String, String> map) {
        return userMapper.updateStudentInfoById(map.get("password"), map.get("classId"), map.get("phone"), map.get("id"));
    }

    @Override
    public int updateCustomerAvatarById(MultipartRequest request, String id) {
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        System.out.println(multiFileMap);
        return 0;
//        return userMapper.updateCustomerAvatarById(name, id);
    }
}

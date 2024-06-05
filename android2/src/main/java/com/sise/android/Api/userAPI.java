package com.sise.android.Api;

import com.sise.android.pojo.Customer;
import com.sise.android.pojo.LoginInfo;
import com.sise.android.pojo.Message;
import com.sise.android.service.UserService;
import com.sise.android.util.ReturnJsonMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "user", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class userAPI {
    @Resource
    UserService userService;
    @PostMapping("login")
    public String login(@RequestBody LoginInfo loginInfo) {
        Customer customer = userService.login(loginInfo);
        if (customer != null) {
            String message = ReturnJsonMessage.returnMessage(customer);
            System.out.println(message);
            return message;
        } else {
            return ReturnJsonMessage.DIYMessage(new Message("500", "学号或密码错误"));
        }
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestBody Map<String, String> map) {
        int i = userService.changePassword(map.get("password"), map.get("id"));
        if (map.size() != 0 && i != 0) {
            return ReturnJsonMessage.returnMessage(i);
        } else {
            return ReturnJsonMessage.DIYMessage(new Message("500", "修改失败"));
        }
    }


    @PostMapping("updateStudent")
    public String updateStudentInfoById(@RequestBody Map<String, String> map) {
        if (map.size() != 0) {
            return ReturnJsonMessage.returnMessage(userService.updateStudentInfoById(map));
        }
        return ReturnJsonMessage.DIYMessage(new Message("500", "服务器忙碌"));
    }


    @PostMapping("updateAvatar")
    public String updateCustomerAvatarById(MultipartRequest request) {
        System.out.println("xixi");
        userService.updateCustomerAvatarById(request, "0");
        return null;
    }
}

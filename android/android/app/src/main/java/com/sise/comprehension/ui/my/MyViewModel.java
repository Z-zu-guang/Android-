package com.sise.comprehension.ui.my;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.util.Global;

import java.util.HashMap;
import java.util.Map;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Map<String, String >> nameAndinfo;
    public MyViewModel() {
        String info = null;
        Customer customer = Global.getCustomer();
        String role = "";
        if ("0".equals(customer.getRole())) {
            role = "学生";
        } else if ("1".equals(customer.getRole())) {
            role = "老师";
        }
        String name = "姓名:" + customer.getName();
        if ("0".equals(customer.getRole())) {
            info = "学号:" + customer.getNo() + "    性别:" + customer.getSex() + "    " + role;
        } else if ("1".equals(customer.getRole())) {
            info = "工号:" + customer.getNo() + "    性别:" + customer.getSex() + "    " + role;
        }
        Map<String, String > map = new HashMap<>();
        map.put("name", name);
        map.put("info", info);
        map.put("avatar", customer.getAvatar());
        nameAndinfo = new MutableLiveData<>();
        nameAndinfo.setValue(map);
    }

    public LiveData<Map<String, String>> getText() {
        return nameAndinfo;
    }
}
package com.sise.comprehension.ui.teacher.informationManage;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.comprehension.pojo.Classed;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.util.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationManageViewModel extends ViewModel {
    private MutableLiveData<Map<String, Object>> liveData;
    private String sno;
    private List<Classed> list = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private Handler handler;


    public void setSno(String sno) {
        this.sno = sno;
    }


    public InformationManageViewModel() {
        liveData = new MutableLiveData<>();
        handler = new Handler(message -> {
            liveData.setValue(this.map);
            return true;
        });
        new Thread(()->{
            Map<String, String> map = new HashMap<>();
            map.put("no", sno);
            Info info = util.toInfo(util.sendJson("/information/findStudent", util.gson().toJson(map)), Map.class);
            if ("200".equals(info.getCode())) {
                Map<String, Object> map1 = (Map<String, Object>)info.getPojo();
                Customer customer = util.gson().fromJson(util.gson().toJson(map1.get("customer")), Customer.class);
                List list1 = util.gson().fromJson(util.gson().toJson(map1.get("class")), List.class);
                for (Object o : list1) {
                    list.add(util.gson().fromJson(util.gson().toJson(o), Classed.class));
                }
                this.map.put("customer", customer);
                this.map.put("list", list);
            }
            handler.sendMessage(handler.obtainMessage());
        }).start();
    }

    public MutableLiveData<Map<String, Object>> getLiveData() {
        return liveData;
    }
}
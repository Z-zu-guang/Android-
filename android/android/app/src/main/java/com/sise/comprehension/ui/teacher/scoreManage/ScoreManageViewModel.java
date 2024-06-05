package com.sise.comprehension.ui.teacher.scoreManage;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.comprehension.pojo.Classed;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.pojo.Result;
import com.sise.comprehension.util.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreManageViewModel extends ViewModel {
    private MutableLiveData<Map<String, Object>> liveData;
    private String sno;
    private List<Result> results = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();


    public void setSno(String sno) {
        this.sno = sno;
    }

    public ScoreManageViewModel() {
        liveData = new MutableLiveData<>();
        Handler handler = new Handler(message -> {
            liveData.setValue(map);
            return true;
        });
        new Thread(() -> {
            Map<String, String> map = new HashMap<>();
            Result result;
            map.put("no", sno);
            Info info = util.toInfo(util.sendJson("/information/score", util.toJson(map)), List.class);
            Info info2 = util.toInfo(util.sendJson("/information/findStudent", util.gson().toJson(map)), Map.class);
            if ("200".equals(info.getCode())) {
                List list = (List) info.getPojo();
            for (Object o : list) {
                result = util.gson().fromJson(util.toJson(o), Result.class);
                results.add(result);
            }}
            if ("200".equals(info2.getCode())) {
                Map<String, Object> map1 = (Map<String, Object>)info2.getPojo();
                Customer customer = util.gson().fromJson(util.gson().toJson(map1.get("customer")), Customer.class);
                this.map.put("customer", customer);
            }
            this.map.put("results", results);
            handler.sendMessage(handler.obtainMessage());
        }).start();
    }

    public MutableLiveData<Map<String, Object>> getLiveData() {
        return liveData;
    }
}
package com.sise.comprehension.ui.student.information;

import android.os.Handler;
import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.pojo.Result;
import com.sise.comprehension.util.Global;
import com.sise.comprehension.util.ShowComponent;
import com.sise.comprehension.util.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationViewModel extends ViewModel {
    List<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> result;
    Handler handler;
    public InformationViewModel() {
        result = new MutableLiveData<>();
        handler = new Handler(message -> {
            result.setValue(results);
            return true;
        });
        new Thread(()->{
            Map<String, String> map = new HashMap<>();
            map.put("no", Global.getCustomer().getNo());
            Info info = util.toInfo(util.sendJson("/information/score", util.toJson(map)), List.class);
            System.out.println(info);
            if ("200".equals(info.getCode())) {
                List list = (List) info.getPojo();
                for (Object o : list) {
                    Result result = new Gson().fromJson(util.toJson(o), Result.class);
                    results.add(result);
                }
                handler.sendMessage(handler.obtainMessage());
            }
        }).start();
    }

    public LiveData<List<Result>> getText() {
        return result;
    }
}
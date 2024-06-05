package com.sise.comprehension.ui.student.schoolTimeTable;

import android.os.Handler;
import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.sise.comprehension.pojo.CourseStudent;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.util.Global;
import com.sise.comprehension.util.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolTimeTableViewModel extends ViewModel {

    private MutableLiveData<List<List<CourseStudent>>> timeTable;
    Handler handler;

    public SchoolTimeTableViewModel() {
        timeTable = new MutableLiveData<>();
        List<CourseStudent> monday = new ArrayList();
        List<CourseStudent> tuesday = new ArrayList();
        List<CourseStudent> wednesday = new ArrayList();
        List<CourseStudent> thursday = new ArrayList();
        List<CourseStudent> friday = new ArrayList();
        List<List<CourseStudent>> all = new ArrayList<>();
        Gson gson = new Gson();

        handler = new Handler(message -> {
            timeTable.setValue(all);
            return true;
        });
        new Thread(() -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", Global.getCustomer().getId());
            Info info = util.toInfo(util.sendJson("/information/findCourse", util.toJson(map)), List.class);
            List list = (List) info.getPojo();
            for (Object o : list) {
                CourseStudent courseStudent = gson.fromJson(gson.toJson(o), CourseStudent.class);
                if ("1".equals(courseStudent.getDay())) {
                    monday.add(courseStudent);
                }
                if ("2".equals(courseStudent.getDay())) {
                    tuesday.add(courseStudent);
                }
                if ("3".equals(courseStudent.getDay())) {
                    wednesday.add(courseStudent);
                }
                if ("4".equals(courseStudent.getDay())) {
                    thursday.add(courseStudent);
                }
                if ("5".equals(courseStudent.getDay())) {
                    friday.add(courseStudent);
                }
            }
            toSeven(monday);
            toSeven(tuesday);
            toSeven(wednesday);
            toSeven(thursday);
            toSeven(friday);
            all.add(monday);
            all.add(tuesday);
            all.add(wednesday);
            all.add(thursday);
            all.add(friday);
            Message message = handler.obtainMessage();
            handler.sendMessage(message);
        }).start();

    }

    public LiveData<List<List<CourseStudent>>> getText() {
        return timeTable;
    }
    private void toSeven(List<CourseStudent> list) {
        CourseStudent courseStudent = list.get(list.size() - 1);
        if (Integer.parseInt(courseStudent.getTime()) < 7) {
            for (int i = Integer.parseInt(courseStudent.getTime()); i < 7; i++) {
                CourseStudent courseStudent1 = new CourseStudent();
                courseStudent1.setTime(i + 1 + "");
                list.add(courseStudent1);
            }
        }
        for (int i = 0; i < 7; i++) {
            int time = Integer.parseInt(list.get(i).getTime());
            if (time != (i + 1)) {
                CourseStudent courseStudent1 = new CourseStudent();
                courseStudent1.setTime((i + 1) + "");
                list.add(i, courseStudent1);
            }
        }
    }
}
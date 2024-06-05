package com.sise.comprehension.util;

import android.app.Application;
import android.content.Context;

import com.sise.comprehension.pojo.Classed;
import com.sise.comprehension.pojo.Customer;


public class Global extends Application {
    private static Global instance;
    private static Customer customer;
    private static Context context;

    public static void setInstance(Global instance) {
        Global.instance = instance;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Global.context = context;
    }

    public static Global getInstance() {
        return instance;
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        Global.customer = customer;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        customer = new Customer();
//        customer.setId("1");
//        customer.setName("张三");
//        customer.setPassword("1234567");
//        customer.setNo("1");
//        customer.setRole("1");
//        customer.setSex("男");
//        customer.setPhone("111");
//        customer.setBirthday("111");
//        customer.setEnrollmentDate("111");
//        customer.setAvatar("https://tfs.alipayobjects.com/images/partner/TB1QFdHXm8yDuNkUvMHXXbCvXXa");
//        customer.setClassed(new Classed("1", "111", "111", "111"));
        instance = this;
        context = getApplicationContext();
    }
}

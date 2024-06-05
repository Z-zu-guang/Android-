package com.sise.comprehension.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.sise.comprehension.R;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.pojo.LoginInfo;
import com.sise.comprehension.ui.student.activity.StudentActivity;
import com.sise.comprehension.ui.teacher.activity.TeacherActivity;
import com.sise.comprehension.util.Global;
import com.sise.comprehension.util.ShowComponent;
import com.sise.comprehension.util.util;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button button;
    LoginInfo loginInfo;
    Handler handler;
    Info info;
    Gson gson = new Gson();
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Global.getCustomer() != null) {
            finish();
        }
        button = findViewById(R.id.login);
        EditText editText1 = findViewById(R.id.userName);
        EditText editText2 = findViewById(R.id.password);
        button.setOnClickListener(view -> {
            loginInfo = new LoginInfo(editText1.getText().toString(), editText2.getText().toString());
            if ("".equals(loginInfo.getNo()) || "".equals(loginInfo.getPassword())) {
//                    Toast.makeText(LoginActivity.this, "请填写用户名", Toast.LENGTH_LONG).show();
                new ShowComponent().showMessageAlertDialog("请输入学号/工号或密码", this);
                return;
            }
            handler = new Handler(message -> {
                if (info.getCode().equals("200")) {
                    try {
                        Map<String, String> map = (Map<String, String>) info.getPojo();
                        customer = util.mapToCustomer(map);
                        Global.setCustomer(customer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if ("0".equals(customer.getRole())) {
                        Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                        startActivity(intent);
                        finish();
                    } else if ("1".equals(customer.getRole())) {
                        Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                        startActivity(intent);
                    }
                } else {
                    ShowComponent.showMessageAlertDialog(info.getPojo().toString(), this);
                }
                return true;
            });
            new Thread(() -> {
                try {
                    info = gson.fromJson(util.sendJson("/user/login", util.toJson(loginInfo)), Info.class);

                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }).start();
        });
    }
}
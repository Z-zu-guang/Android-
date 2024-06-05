package com.sise.comprehension.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sise.comprehension.R;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.ui.student.activity.StudentActivity;
import com.sise.comprehension.ui.activity.InformationActivity;
import com.sise.comprehension.ui.activity.ChangePasswordActivity;
import com.sise.comprehension.ui.activity.LoginActivity;
import com.sise.comprehension.util.Global;
import com.sise.comprehension.util.ShowComponent;
import com.sise.comprehension.util.util;

import static android.app.Activity.RESULT_OK;
public class ChangePasswordActivity extends AppCompatActivity {

    EditText oldPassword;
    EditText newPassword;
    Button button;
    Button cancel;
    Customer customer;
    Info info;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        button = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);
        customer = new Customer();
        button.setOnClickListener(view -> {
            ShowComponent.showConfirmAlertDialog("确定修改吗", this, (view1, i) -> {
                if ("".equals(oldPassword.getText().toString())) {
                    ShowComponent.showMessageAlertDialog("请输入旧密码", this);
                    return;
                }
                if ("".equals(newPassword.getText().toString())) {
                    ShowComponent.showMessageAlertDialog("请输入新密码", this);
                    return;
                }
                if (!(oldPassword.getText().toString()).equals(Global.getCustomer().getPassword())) {
                    ShowComponent.showMessageAlertDialog("旧密码不正确，请重新输入", this);
                    return;
                }
                if ((oldPassword.getText().toString()).equals(newPassword.getText().toString())) {
                    ShowComponent.showMessageAlertDialog("旧密码不能跟新密码相同", this);
                    return;
                }
                customer.setPassword(newPassword.getText().toString());
                customer.setId(Global.getCustomer().getId());
                handler = new Handler(message -> {
                    if ("200".equals(info.getCode())) {
                        Global.getCustomer().setPassword(newPassword.getText().toString());
                        ShowComponent.showConfirmAlertDialog("修改成功", this, (dialogInterface, k) -> {
                            Intent intent = new Intent(this, StudentActivity.class);
                            startActivity(intent);
                        }, null);
                    }
                    return true;
                });
                new Thread(() -> {
                    info = util.toInfo(util.sendJson("/user/changePassword", util.toJson(customer)), String.class);
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }).start();
            }, null);
        });
        cancel.setOnClickListener(view -> {
            finish();
        });
    }


}

package com.sise.comprehension.ui.teacher.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sise.comprehension.R;
import com.sise.comprehension.ui.teacher.informationManage.InformationManageFragment;
import com.sise.comprehension.ui.teacher.scoreManage.ScoreManageFragment;
import com.sise.comprehension.util.ShowComponent;

public class ManageActivity extends AppCompatActivity {
    Button find;
    Button back;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_manage);
        find = findViewById(R.id.find);
        back = findViewById(R.id.back);
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");
        find.setOnClickListener(view -> {
            EditText editText = findViewById(R.id.findNo);
            String findNo = editText.getText().toString();
            if ("".equals(findNo)) {
                ShowComponent.showMessageAlertDialog("请输入要查找的学号", this);
                return;
            }

            if ("studentInfoManage".equals(tag)) {
                InformationManageFragment fragment = new InformationManageFragment(findNo);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.replace, fragment, "tag");
                transaction.commit();
            }
            if ("scoreManage".equals(tag)) {
                ScoreManageFragment fragment = new ScoreManageFragment(findNo);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.replace, fragment, "tag");
                transaction.commit();
            }
        });

        back.setOnClickListener(view -> {
            finish();
        });
    }
}
package com.sise.comprehension.ui.activity;




import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sise.comprehension.pojo.Result;
import com.sise.comprehension.R;
import com.sise.comprehension.util.ShowComponent;


public class TestActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        button = findViewById(R.id.btn);
        button.setOnClickListener(view1 -> {
            Result result = new Result("1", "aa", "111", "150");
            LinearLayout linearLayout = ShowComponent.newScoreManageMessage(result, this, 0);
            LinearLayout linearLayout1 = findViewById(R.id.test);
            linearLayout1.addView(linearLayout);
        });
    }
}

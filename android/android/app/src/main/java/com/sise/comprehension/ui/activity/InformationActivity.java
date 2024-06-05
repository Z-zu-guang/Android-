package com.sise.comprehension.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sise.comprehension.R;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.util.Global;
import com.sise.comprehension.util.util;

import java.io.File;

public class InformationActivity extends AppCompatActivity {
    Customer customer = Global.getCustomer();
    TextView name;
    TextView info;
    TextView phone;
    TextView birthday;
    TextView enrollmentDate;
    TextView classed;
    TextView teacher;
    TextView counselor;
    ImageView avatar;
    Handler handler;
    Bitmap bitmap;
    Button backed;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        name = findViewById(R.id.name2);
        info = findViewById(R.id.info2);
        phone = findViewById(R.id.phone);
        birthday = findViewById(R.id.birthday);
        enrollmentDate = findViewById(R.id.enrollmentDate);
        classed = findViewById(R.id.classed);
        teacher = findViewById(R.id.teacher);
        counselor = findViewById(R.id.counselor);
        backed = findViewById(R.id.backed);
        avatar = findViewById(R.id.avatar);
        handler = new Handler(message -> {
            avatar.setImageBitmap(bitmap);
            return true;
        });
        String role = "";
        if ("0".equals(customer.getRole())) {
            role = "学生";
        } else if ("1".equals(customer.getRole())) {
            role = "老师";
        }
        new Thread(() -> {
            bitmap = util.getPicture(customer.getAvatar());
            handler.sendMessage(handler.obtainMessage());
        }).start();
        String names = "姓名:" + customer.getName();
        String infoes = "学号:" + customer.getNo() + "    性别:" + customer.getSex() + "    " + role;
        name.setText(names);
        info.setText(infoes);
        phone.setText(customer.getPhone());
        birthday.setText(customer.getBirthday());
        enrollmentDate.setText(customer.getEnrollmentDate());
        classed.setText(customer.getClassed().getClassName());
        teacher.setText(customer.getClassed().getTeacher());
        counselor.setText(customer.getClassed().getCounselor());
        System.out.println(customer);
        backed.setOnClickListener(view -> {
            finish();
        });
        avatar.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (data != null) {
                    Uri uri = data.getData();
                    crop(uri);
                }
            } else if (requestCode == 2) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.avatar.setImageBitmap(bitmap);
            }
        }

    }
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, 2);
    }
    private File uriToFile(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = this.managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }
}
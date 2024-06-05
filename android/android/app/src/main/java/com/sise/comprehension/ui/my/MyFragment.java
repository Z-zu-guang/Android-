package com.sise.comprehension.ui.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sise.comprehension.ui.activity.InformationActivity;
import com.sise.comprehension.ui.activity.ChangePasswordActivity;
import com.sise.comprehension.ui.activity.LoginActivity;
import com.sise.comprehension.R;
import com.sise.comprehension.util.Global;
import com.sise.comprehension.util.util;

import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends Fragment {

    private MyViewModel homeViewModel;
    Bitmap bitmap;
    TextView name;
    TextView info;
    ImageView avatar;
    Button changePassword;
    Button exit;
    LinearLayout information;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my, container, false);
        name = root.findViewById(R.id.name);
        info = root.findViewById(R.id.info);
        avatar = root.findViewById(R.id.avater);
        changePassword = root.findViewById(R.id.changePassword);
        exit = root.findViewById(R.id.exit);
        information = root.findViewById(R.id.information);
        Handler handler = new Handler(view -> {
            avatar.setImageBitmap(bitmap);
            return true;
        });

        changePassword.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ChangePasswordActivity.class);
            startActivity(intent);
        });
        exit.setOnClickListener(view -> {
            Global.setCustomer(null);
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), (Map<String, String> map) -> {
            name.setText(map.get("name"));
            info.setText(map.get("info"));
            new Thread(() -> {
                bitmap = util.getPicture(map.get("avatar"));
                Message message = handler.obtainMessage();
                handler.sendMessage(message);
            }).start();
        });
        information.setOnClickListener(view->{
            Intent intent = new Intent(getContext(), InformationActivity.class);
            startActivity(intent);
        });
//        avatar.setOnClickListener(view -> {
//            Intent intent = new Intent(Intent.ACTION_PICK);
//            startActivityForResult(intent, 1);
//        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                crop(uri);
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
}
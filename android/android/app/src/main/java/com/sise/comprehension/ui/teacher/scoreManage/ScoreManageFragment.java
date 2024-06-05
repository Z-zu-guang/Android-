package com.sise.comprehension.ui.teacher.scoreManage;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sise.comprehension.R;
import com.sise.comprehension.databinding.FragmentScoreManageBinding;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.pojo.Result;
import com.sise.comprehension.util.ShowComponent;
import com.sise.comprehension.util.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ScoreManageFragment extends Fragment {

    private ScoreManageViewModel scoreManageViewModel;
    private String findNo;
    private FragmentScoreManageBinding binding;
    private LinearLayout score;
    Button button;
    private List<Integer> editId;
    Info info;
    Handler handler;

    public ScoreManageFragment(String findNo) {
        this.findNo = findNo;
    }

    public ScoreManageFragment() {

    }

    public static ScoreManageFragment newInstance() {
        return new ScoreManageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scoreManageViewModel = new ViewModelProvider(this).get(ScoreManageViewModel.class);
        scoreManageViewModel.setSno(findNo);

        binding = FragmentScoreManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        score = root.findViewById(R.id.score_layout);
        button = root.findViewById(R.id.score_modify);


        scoreManageViewModel.getLiveData().observe(getViewLifecycleOwner(), map -> {
            editId = new ArrayList<>();
            System.out.println(map);
            List<Result> results = (List<Result>)map.get("results");
            Customer customer = (Customer) map.get("customer");
            if (customer != null && results.size() != 0) {
                score.addView(customerInfo(customer, getActivity()));
                for (Result result : results) {
                    LinearLayout linearLayout = ShowComponent.newScoreManageMessage(result, getActivity(), Integer.parseInt(result.getId()));
                    editId.add(Integer.parseInt(result.getId()));
                    score.addView(linearLayout);
                }
            } else {
                ShowComponent.showToast("学生不存在", getActivity());
            }
        });
        handler = new Handler(message -> {
            if ("200".equals(info.getCode())) {
                ShowComponent.showToast("修改成功",getActivity());
            } else if ("500".equals(info.getCode())) {
                ShowComponent.showToast("修改失败", getActivity());
            }
            return true;
        });
        button.setOnClickListener(view -> {
            List<Result> results2 = new ArrayList<>();
            for (Integer integer : editId) {
                Result result = new Result();
                result.setId(integer + "");
                EditText editText = root.findViewById(integer);
                result.setScore(editText.getText().toString());
                results2.add(result);
            }
            Map<String, List<Result>> map = new HashMap<>();
            map.put("list", results2);
            new Thread(()->{
                info = util.toInfo(util.sendJson("/information/updateScore", util.toJson(map)), Integer.class);
                handler.sendMessage(handler.obtainMessage());
            }).start();
        });
        return root;
    }

    public LinearLayout customerInfo(Customer customer, Context context) {
        LinearLayout all = new LinearLayout(context);
        LinearLayout names = new LinearLayout(context);
        LinearLayout nos = new LinearLayout(context);
        TextView name = new TextView(context);
        TextView name1 = new TextView(context);
        TextView no = new TextView(context);
        TextView no1 = new TextView(context);

        LinearLayout.LayoutParams textViewParams1 = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        LinearLayout.LayoutParams linearLayoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);
        linearLayoutParams.setMargins(0, 10, 0, 10);


        names.setLayoutParams(linearLayoutParams2);
        names.setOrientation(LinearLayout.HORIZONTAL);

        nos.setLayoutParams(linearLayoutParams2);
        nos.setOrientation(LinearLayout.HORIZONTAL);

        all.setLayoutParams(linearLayoutParams);
        all.setOrientation(LinearLayout.HORIZONTAL);
        all.setBackgroundResource(R.drawable.textview_border);


        name.setText("姓名:");
        name.setGravity(Gravity.CENTER);
        name.setLayoutParams(textViewParams1);

        name1.setText(customer.getName());
        name1.setGravity(Gravity.CENTER);
        name1.setLayoutParams(textViewParams1);

        no.setText("学号:");
        no.setGravity(Gravity.CENTER);
        no.setLayoutParams(textViewParams1);

        no1.setText(customer.getNo());
        no1.setGravity(Gravity.CENTER);
        no1.setLayoutParams(textViewParams1);



        names.addView(name);
        names.addView(name1);

        nos.addView(no);
        nos.addView(no1);

        all.addView(names);
        all.addView(nos);
        return all;
    }
}
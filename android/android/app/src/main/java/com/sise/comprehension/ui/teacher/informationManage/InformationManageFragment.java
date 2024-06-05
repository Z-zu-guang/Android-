package com.sise.comprehension.ui.teacher.informationManage;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sise.comprehension.R;
import com.sise.comprehension.databinding.FragmentInformationManageBinding;
import com.sise.comprehension.pojo.Classed;
import com.sise.comprehension.pojo.Customer;
import com.sise.comprehension.pojo.Info;
import com.sise.comprehension.util.ShowComponent;
import com.sise.comprehension.util.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationManageFragment extends Fragment {

    private InformationManageViewModel informationManageViewModel;
    private Customer customer;
    private String findNo;
    private FragmentInformationManageBinding binding;
    int item = 0;
    Info info;

    public InformationManageFragment(String findNo) {
        this.findNo = findNo;
    }

    public InformationManageFragment() {

    }

    public static InformationManageFragment newInstance() {
        return new InformationManageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (customer == null) {
            onDestroy();
        }

        informationManageViewModel = new ViewModelProvider(this).get(InformationManageViewModel.class);
        informationManageViewModel.setSno(findNo);
        binding = FragmentInformationManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        informationManageViewModel.getLiveData().observe(getViewLifecycleOwner(), map -> {
            Customer customer = (Customer) map.get("customer");
            if (customer == null) {
                ShowComponent.showToast("学生不存在", getActivity());
            }
            this.customer = customer;
            List<Classed> classeds = (List<Classed>) map.get("list");
            Map<Integer, Classed> classMap = new HashMap();
            List<String> classNames = new ArrayList<>();


            Button button = root.findViewById(R.id.manage_modify);
            EditText sno = root.findViewById(R.id.manage_sno);
            EditText name = root.findViewById(R.id.manage_name);
            EditText password = root.findViewById(R.id.manage_password);
            EditText sex = root.findViewById(R.id.manage_sex);
            Spinner classed = root.findViewById(R.id.manage_classed);
            EditText teacher = root.findViewById(R.id.manage_teacher);
            EditText counselor = root.findViewById(R.id.manage_counselor);
            EditText role = root.findViewById(R.id.manage_role);
            EditText phone = root.findViewById(R.id.manage_phone);
            EditText birthday = root.findViewById(R.id.manage_birthday);
            EditText enrollmentDate = root.findViewById(R.id.manage_enrollmentDate);


            if (customer != null && classeds != null){
                for (int i = 0; i < classeds.size() - 1; i++) {
                    classMap.put(i + 1, classeds.get(i));
                    classNames.add(classeds.get(i).getClassName());
                }

            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, classNames);
            arrayAdapter.setDropDownViewResource(R.layout.list_item);
            classed.setAdapter(arrayAdapter);
            classed.setDropDownVerticalOffset(80);
            classed.setDropDownHorizontalOffset(80);
            classed.setPopupBackgroundResource(R.drawable.view_radius);
            classed.setLayoutMode(Spinner.MODE_DROPDOWN);
            classed.setSelection(Integer.parseInt(customer.getClassed().getId()) - 1);
            classed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Classed classed1 = classMap.get(i + 1);
                    teacher.setText(classed1.getTeacher());
                    counselor.setText(classed1.getCounselor());
                    item = i;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            sno.setText(customer.getNo());
            name.setText(customer.getName());
            password.setText(customer.getPassword());
            sex.setText(customer.getSex());
            if ("0".equals(customer.getRole())) {
                role.setText("学生");
            } else if ("1".equals(customer.getRole())) {
                role.setText("老师");
            }
            phone.setText(customer.getPhone());
            birthday.setText(String.valueOf(customer.getBirthday()));
            enrollmentDate.setText(String.valueOf(customer.getEnrollmentDate()));


            sno.setFocusable(false);
            name.setFocusable(false);
            sex.setFocusable(false);
            role.setFocusable(false);
            teacher.setFocusable(false);
            counselor.setFocusable(false);
            birthday.setFocusable(false);
            enrollmentDate.setFocusable(false);

            Handler handler = new Handler(message -> {
                if ("200".equals(info.getCode())) {
                    ShowComponent.showToast("修改成功", getActivity());
                } else if ("500".equals(info.getCode())) {
                    ShowComponent.showToast((String) info.getPojo(), getActivity());
                }
                return true;
            });

            button.setOnClickListener(view -> {
                Map<String, String> map1 = new HashMap<>();
                map1.put("password", password.getText().toString());
                map1.put("classId", classMap.get(item + 1).getId());
                map1.put("phone", phone.getText().toString());
                map1.put("id", findNo);
                new Thread(() -> {
                    info = util.toInfo(util.sendJson("/user/updateStudent", util.gson().toJson(map1)), Integer.class);
                    handler.sendMessage(handler.obtainMessage());
                }).start();
            });
        }
        });

        return root;
    }

}
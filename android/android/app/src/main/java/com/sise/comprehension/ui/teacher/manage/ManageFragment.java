package com.sise.comprehension.ui.teacher.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sise.comprehension.R;
import com.sise.comprehension.ui.teacher.activity.ManageActivity;
import com.sise.comprehension.databinding.FragmentManageBinding;


public class ManageFragment extends Fragment {

    private FragmentManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button studentInfoManage = root.findViewById(R.id.student_information_manage);
        Button scoreManage = root.findViewById(R.id.score_manage);
//        Button schoolTimeTableManage = root.findViewById(R.id.school_time_table_manage);

        studentInfoManage.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ManageActivity.class);
            intent.putExtra("tag", "studentInfoManage");
            startActivity(intent);
        });

        scoreManage.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ManageActivity.class);
            intent.putExtra("tag", "scoreManage");
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.sise.comprehension.ui.student.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sise.comprehension.R;
import com.sise.comprehension.pojo.Result;
import com.sise.comprehension.util.ShowComponent;


public class InformationFragment extends Fragment {

    private InformationViewModel informationViewModel;
    LinearLayout score;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        informationViewModel = new ViewModelProvider(this).get(InformationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_information, container, false);
        score = root.findViewById(R.id.score);

        informationViewModel.getText().observe(getViewLifecycleOwner(), results -> {
            for (Result result : results) {
                LinearLayout linearLayout = ShowComponent.newStudentScoreMessage(result, getActivity());
                score.addView(linearLayout);
            }
        });
        return root;
    }
}
package com.sise.comprehension.ui.student.schoolTimeTable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sise.comprehension.R;
import com.sise.comprehension.pojo.CourseStudent;
import com.sise.comprehension.util.ShowComponent;

import java.util.List;

public class SchoolTimeTableFragment extends Fragment {

    private SchoolTimeTableViewModel schoolTimeTableViewModel;
    private TableRow tableRowMorning1;
    private TableRow tableRowMorning2;
    private TableRow tableRowAfternoon1;
    private TableRow tableRowAfternoon2;
    private TableRow tableRowAfternoon3;
    private TableRow tableRowEvening1;
    private TableRow tableRowEvening2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_school_time_table, container, false);
        tableRowMorning1 = root.findViewById(R.id.tableRowMorning1);
        tableRowMorning2 = root.findViewById(R.id.tableRowMorning2);
        tableRowAfternoon1 = root.findViewById(R.id.tableRowAfternoon1);
        tableRowAfternoon2 = root.findViewById(R.id.tableRowAfternoon2);
        tableRowAfternoon3 = root.findViewById(R.id.tableRowAfternoon3);
        tableRowEvening1 = root.findViewById(R.id.tableRowEvening1);
        tableRowEvening2 = root.findViewById(R.id.tableRowEvening2);
        schoolTimeTableViewModel = new ViewModelProvider(this).get(SchoolTimeTableViewModel.class);
        schoolTimeTableViewModel.getText().observe(getViewLifecycleOwner(), (List<List<CourseStudent>> list) -> {
            for (List<CourseStudent> list1 : list) {
                for (CourseStudent courseStudent : list1) {
                    switchCourseStudent(courseStudent);
                }
            }
        });
        return root;
    }

    private void switchCourseStudent(CourseStudent courseStudent) {
        if ("1".equals(courseStudent.getTime())) {
            LinearLayout morning1 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowMorning1.addView(morning1);
        }
        if ("2".equals(courseStudent.getTime())) {
            LinearLayout morning2 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowMorning2.addView(morning2);
        }
        if ("3".equals(courseStudent.getTime())) {
            LinearLayout afternoon1 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowAfternoon1.addView(afternoon1);
        }
        if ("4".equals(courseStudent.getTime())) {
            LinearLayout afternoon2 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowAfternoon2.addView(afternoon2);
        }
        if ("5".equals(courseStudent.getTime()))  {
            LinearLayout afternoon3 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowAfternoon3.addView(afternoon3);
        }
        if ("6".equals(courseStudent.getTime())) {
            LinearLayout evening1 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowEvening1.addView(evening1);
        }
        if ("7".equals(courseStudent.getTime())) {
            LinearLayout evening2 = ShowComponent.newSchoolTime(courseStudent, getActivity());
            tableRowEvening2.addView(evening2);
        }
    }
}
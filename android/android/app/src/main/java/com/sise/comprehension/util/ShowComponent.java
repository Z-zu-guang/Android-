package com.sise.comprehension.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sise.comprehension.R;
import com.sise.comprehension.pojo.CourseStudent;
import com.sise.comprehension.pojo.Result;

public class ShowComponent {
    public static AlertDialog showMessageAlertDialog(String message, Context context){
        return new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定",null)
                .show();
    }
    public static AlertDialog showConfirmAlertDialog(String message, Context context, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancelClickListener){
        return new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定",onOkClickListener)
                .setNegativeButton("取消", onCancelClickListener)
                .show();
    }

    public static LinearLayout newSchoolTime(CourseStudent courseStudent, Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        TextView name = new TextView(context);
        TextView teacher = new TextView(context);
        TextView room = new TextView(context);

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.2f);
        LinearLayout.LayoutParams textViewParams2 = new LinearLayout.LayoutParams(170, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f);

        name.setText(courseStudent.getCourseName());
        name.setGravity(Gravity.CENTER);
        name.setLayoutParams(textViewParams2);

        teacher.setText(courseStudent.getInstructor());
        teacher.setGravity(Gravity.CENTER);
        teacher.setLayoutParams(textViewParams);

        room.setText(courseStudent.getClassroom());
        room.setGravity(Gravity.CENTER);
        room.setLayoutParams(textViewParams);

        TableRow.LayoutParams layoutParams =   new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.15f);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(name);
        linearLayout.addView(teacher);
        linearLayout.addView(room);
        linearLayout.setBackgroundResource(R.drawable.textview_border);


        return linearLayout;
    }

    public static LinearLayout newStudentScoreMessage(Result result, Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        TextView courseName = new TextView(context);
        TextView courseCode = new TextView(context);
        TextView score = new TextView(context);

        LinearLayout.LayoutParams textViewParams1 = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT, 0.2f);
        LinearLayout.LayoutParams textViewParams2 = new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.MATCH_PARENT, 0.6f);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        linearLayoutParams.setMargins(0, 10, 0, 10);

        courseName.setText(result.getCourseName());
        courseName.setGravity(Gravity.CENTER);
        courseName.setLayoutParams(textViewParams1);

        courseCode.setText(result.getCourseCode());
        courseCode.setGravity(Gravity.CENTER);
        courseCode.setLayoutParams(textViewParams1);

        score.setText(result.getScore());
        score.setGravity(Gravity.CENTER);
        score.setLayoutParams(textViewParams2);

        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundResource(R.drawable.textview_border);

        linearLayout.addView(courseName);
        linearLayout.addView(courseCode);
        linearLayout.addView(score);
        return linearLayout;
    }

    public static LinearLayout newScoreManageMessage(Result result, Context context, int scoreId) {
        LinearLayout linearLayout = new LinearLayout(context);
        TextView courseName = new TextView(context);
        TextView courseCode = new TextView(context);
        EditText score = new EditText(context);

        LinearLayout.LayoutParams textViewParams1 = new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT, 0.2f);
        LinearLayout.LayoutParams textViewParams2 = new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.MATCH_PARENT, 0.6f);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        linearLayoutParams.setMargins(0, 10, 0, 10);

        courseName.setText(result.getCourseName());
        courseName.setGravity(Gravity.CENTER);
        courseName.setLayoutParams(textViewParams1);

        courseCode.setText(result.getCourseCode());
        courseCode.setGravity(Gravity.CENTER);
        courseCode.setLayoutParams(textViewParams1);

        score.setText(result.getScore());
        score.setGravity(Gravity.CENTER);
        score.setLayoutParams(textViewParams2);
        score.setId(scoreId);

        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundResource(R.drawable.textview_border);

        linearLayout.addView(courseName);
        linearLayout.addView(courseCode);
        linearLayout.addView(score);
        return linearLayout;
    }
    public static void showToast(String message, Context context) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
        toast.show();
    }

}

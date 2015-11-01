package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar rememberKeyPtsRb;
    RatingBar understandReexplainRb;
    RatingBar confidentTransferRb;

    RatingBar instructorPreparedRb;
    RatingBar instructorDeliveryRb;
    RatingBar instructorEngagementRb;

    EditText workedWellFeedback;
    EditText improvementFeedback;
    EditText overallFeedback;
    LinearLayout firstPartQuestion;
    LinearLayout secondPartQuestion;
    LinearLayout thirdPartQuestion;
    LinearLayout fourthPartQuestion;
    LinearLayout fifthPartQuestion;

    Button submitBtn;

    static final String TAG = "FeedbackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        initView();

        setButtonListener();

    }

    private void initView() {

        firstPartQuestion = (LinearLayout) findViewById(R.id.aff_firstPartQuestion);
        secondPartQuestion = (LinearLayout) findViewById(R.id.aff_secondPartQuestion);
        thirdPartQuestion = (LinearLayout) findViewById(R.id.aff_thirdPartQuestion);
        fourthPartQuestion = (LinearLayout) findViewById(R.id.aff_fourthPartQuestion);
        fifthPartQuestion = (LinearLayout) findViewById(R.id.aff_fifthPartQuestion);

        rememberKeyPtsRb = (RatingBar) findViewById(R.id.aff_p1_Q1_Rb);
        understandReexplainRb = (RatingBar) findViewById(R.id.aff_p1_Q2_Rb);
        confidentTransferRb = (RatingBar) findViewById(R.id.aff_p1_Q3_Rb);

        instructorPreparedRb = (RatingBar) findViewById(R.id.aff_p2_Q1_Rb);
        instructorDeliveryRb = (RatingBar) findViewById(R.id.aff_p2_Q2_Rb);
        instructorEngagementRb = (RatingBar) findViewById(R.id.aff_p2_Q3_Rb);

        workedWellFeedback = (EditText) findViewById(R.id.aff_workedWellEt);
        improvementFeedback = (EditText) findViewById(R.id.aff_improvementEt);
        overallFeedback = (EditText) findViewById(R.id.aff_feedbackEt);

        submitBtn = (Button) findViewById(R.id.aff_submitBtn);

    }

    public void focus(View view){
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
    }
    public void setButtonListener(){

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (rememberKeyPtsRb.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Your feedback is important to us. Please rate this.", Toast.LENGTH_LONG).show();
                    focus(firstPartQuestion);
                }else if (understandReexplainRb.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Your feedback is important to us. Please rate this.", Toast.LENGTH_LONG).show();
                    focus(firstPartQuestion);
                } else if (confidentTransferRb.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Your feedback is important to us. Please rate this.", Toast.LENGTH_LONG).show();
                    focus(firstPartQuestion);
                } else if (instructorPreparedRb.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Your feedback is important to us. Please rate this.", Toast.LENGTH_LONG).show();
                    focus(secondPartQuestion);
                } else if (instructorDeliveryRb.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Your feedback is important to us. Please rate this.", Toast.LENGTH_LONG).show();
                    focus(secondPartQuestion);
                } else if (instructorEngagementRb.getRating() == 0){
                    Toast.makeText(getApplicationContext(), "Your feedback is important to us. Please rate this.", Toast.LENGTH_LONG).show();
                    focus(secondPartQuestion);
                } else if (workedWellFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "This field cannot be empty", Toast.LENGTH_LONG).show();
                    workedWellFeedback.requestFocus();
                } else if (improvementFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "This field cannot be empty", Toast.LENGTH_LONG).show();
                    improvementFeedback.requestFocus();
                } else if (overallFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "This field cannot be empty", Toast.LENGTH_LONG).show();
                    overallFeedback.requestFocus();
                } else {
                    Log.d(TAG, "Collected Data:" + "rememberKeyPtsRb:" + rememberKeyPtsRb.getRating() + "understandReexplainRb:" + understandReexplainRb.getRating()
                            + "confidentTransferRb:" + confidentTransferRb.getRating() + "instructorPreparedRb:"
                            + instructorPreparedRb.getRating() + "instructorDeliveryRb:" + instructorDeliveryRb.getRating() + "instructorEngagementRb:"
                            + instructorEngagementRb.getRating() + "workedWellFeedback:" + workedWellFeedback.getText() + "improvementFeedback:"
                            + improvementFeedback.getText() + "overallFeedback:" + overallFeedback.getText() );
                }
            }

        });



    }


}

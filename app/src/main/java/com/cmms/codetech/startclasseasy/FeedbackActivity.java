package com.cmms.codetech.startclasseasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

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

    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        initView();
    }

    private void initView() {

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

}

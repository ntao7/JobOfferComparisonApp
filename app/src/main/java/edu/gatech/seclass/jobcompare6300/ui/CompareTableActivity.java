package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.data.model.Job;

public class CompareTableActivity extends AppCompatActivity {
    private static final String TAG = "CompareTableActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_table);
        Log.i(TAG, "CompareTableActivity onCreate()");
        Intent inIntent = getIntent();
        String source = inIntent.getSerializableExtra("source", String.class);
        Job offer1 = inIntent.getSerializableExtra("offer1", Job.class);
        Job offer2 = inIntent.getSerializableExtra("offer2", Job.class);
        displayJobInfo(offer1, offer2, source);

        Button backToMainBtn = findViewById(R.id.backToMainBtn);
        backToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "backToMainBtn is clicked!");
                finish();
            }
        });

        Button compareBtn = findViewById(R.id.compareBtn);
        if(source.equals("OfferSuccessActivity")){
            compareBtn.setVisibility(View.GONE);
        } else{
            compareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "compareBtn is clicked!");
                    Intent intent = new Intent(CompareTableActivity.this, CompareActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void displayJobInfo(Job job1, Job job2, String source){
        if(source.equals("OfferSuccessActivity")){
            TextView offer1Text = findViewById(R.id.offer1Text);
            offer1Text.setText("Current Job");
            TextView offer2Text = findViewById(R.id.offer2Text);
            offer2Text.setText("Added Offer");
        }
        
        // display offer1
        TextView titleText1 = findViewById(R.id.titleOffer1Text);
        titleText1.setText(String.valueOf(job1.getTitle()));

        TextView companyText1 = findViewById(R.id.companyOffer1Text);
        companyText1.setText(String.valueOf(job1.getCompany()));

        TextView cityText1 = findViewById(R.id.cityOffer1Text);
        cityText1.setText(String.valueOf(job1.getCity()));

        TextView stateText1 = findViewById(R.id.stateOffer1Text);
        stateText1.setText(String.valueOf(job1.getState()));

        TextView salaryText1 = findViewById(R.id.salaryOffer1Text);
        salaryText1.setText(String.valueOf(job1.getSalary() / job1.getCost()));

        TextView bonusText1 = findViewById(R.id.bonusOffer1Text);
        bonusText1.setText(String.valueOf(job1.getBonus() / job1.getCost()));

        TextView optionText1 = findViewById(R.id.optionOffer1Text);
        optionText1.setText(String.valueOf(job1.getOption()));

        TextView hbpText1 = findViewById(R.id.hbpOffer1Text);
        hbpText1.setText(String.valueOf(job1.getHbp()));

        TextView holidayText1 = findViewById(R.id.holidayOffer1Text);
        holidayText1.setText(String.valueOf(job1.getHoliday()));

        TextView stipendText1 = findViewById(R.id.stipendOffer1Text);
        stipendText1.setText(String.valueOf(job1.getStipend()));

        // display Offer2
        TextView titleText2 = findViewById(R.id.titleOffer2Text);
        titleText2.setText(String.valueOf(job2.getTitle()));

        TextView companyText2 = findViewById(R.id.companyOffer2Text);
        companyText2.setText(String.valueOf(job2.getCompany()));

        TextView cityText2 = findViewById(R.id.cityOffer2Text);
        cityText2.setText(String.valueOf(job2.getCity()));

        TextView stateText2 = findViewById(R.id.stateOffer2Text);
        stateText2.setText(String.valueOf(job2.getState()));

        TextView salaryText2 = findViewById(R.id.salaryOffer2Text);
        salaryText2.setText(String.valueOf(job2.getSalary() / job2.getCost()));

        TextView bonusText2 = findViewById(R.id.bonusOffer2Text);
        bonusText2.setText(String.valueOf(job2.getBonus() / job2.getCost()));

        TextView optionText2 = findViewById(R.id.optionOffer2Text);
        optionText2.setText(String.valueOf(job2.getOption()));

        TextView hbpText2 = findViewById(R.id.hbpOffer2Text);
        hbpText2.setText(String.valueOf(job2.getHbp()));

        TextView holidayText2 = findViewById(R.id.holidayOffer2Text);
        holidayText2.setText(String.valueOf(job2.getHoliday()));

        TextView stipendText2 = findViewById(R.id.stipendOffer2Text);
        stipendText2.setText(String.valueOf(job2.getStipend()));

    }
}
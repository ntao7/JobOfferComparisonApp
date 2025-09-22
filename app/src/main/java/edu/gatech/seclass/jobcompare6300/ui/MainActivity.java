package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.data.model.UserManager;
import edu.gatech.seclass.jobcompare6300.data.model.Weights;
import edu.gatech.seclass.jobcompare6300.db.DBHelper;
import edu.gatech.seclass.jobcompare6300.util.JobComparator;
import edu.gatech.seclass.jobcompare6300.util.JobComparatorManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper dbHelper = DBHelper.getInstance(this, 1);
        Log.i(TAG, "MainActivity onCreate()");
        setContentView(R.layout.activity_main);
        // use UserManager to manage User
        // here we init it
        // use sqlite db to get persist data, and construct it
        User user = dbHelper.getFirstUser();
        if(user == null){
            // if no record in db, create one
            // at this time there will be no jobs
            long userId = dbHelper.insertUser();
            user = new User(userId);
        } else {
            // if have record, need to get jobs
            user.setJobs(dbHelper.getJobListByUserId(user.getUserId()));
            user.setCurrentJob(dbHelper.getCurrentJobByUserId(user.getUserId()));
        }
        UserManager.getInstance().setUser(user);

        // weights
        Weights weights = dbHelper.getWeightsByUserId(user.getUserId());
        if(weights==null){
            weights = new Weights(user.getUserId());
            dbHelper.insertWeights(weights);
        }
        // use JobComparatorManager to manage JobComparator
        // here we init it
        // use sqlite db to get persist data, and construct it; update: cancel, as it is not required by doc
        JobComparatorManager.getInstance().setJobComparator(new JobComparator(weights.toMap()));

        // button goes to edit/enter current job
        Button currentBtn = findViewById(R.id.currentBtn);
        currentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentJobActivity.class);
                startActivity(intent);
            }
        });

        // button goes to enter job offers
        Button offerBtn = findViewById(R.id.offerBtn);
        offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JobOfferActivity.class);
                startActivity(intent);
            }
        });

        // button goes to adjust weights
        Button adjSettingsBtn = findViewById(R.id.adjSettingsBtn);
        adjSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdjustActivity.class);
                startActivity(intent);
            }
        });

        // button goes to compare offers
        Button compareBtn = findViewById(R.id.compareBtn);
        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompareActivity.class);
                startActivity(intent);
            }
        });

    }
}
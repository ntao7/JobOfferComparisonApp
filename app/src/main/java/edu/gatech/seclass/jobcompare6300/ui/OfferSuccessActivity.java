package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.data.model.UserManager;

public class OfferSuccessActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_success);
        Log.i(TAG, "OfferSuccessActivity onCreate()");

        // add more offer
        Button addMoreBtn = findViewById(R.id.addMoreBtn);
        addMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "addMoreBtn is clicked!");
                Intent outIntent = new Intent(OfferSuccessActivity.this, JobOfferActivity.class);
                startActivity(outIntent);
                finish();
            }
        });

        // return to main
        Button backToMainBtn = findViewById(R.id.backToMainBtn);
        backToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "backToMainBtn is clicked!");
                finish();
            }
        });

        // compare with current
        Button compareBtn = findViewById(R.id.compareBtn);
        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "compareBtn is clicked!");
                Intent inIntent = getIntent();
                Job addedOffer = inIntent.getSerializableExtra("added_offer", Job.class);
                User user = UserManager.getInstance().getUser();
                if (user == null) {
                    Toast.makeText(OfferSuccessActivity.this, "User information is not available.", Toast.LENGTH_LONG).show();
                    return;
                }
                Job currentJob = user.getCurrentJob();
                if(currentJob != null){
                    Intent outIntent = new Intent(OfferSuccessActivity.this, CompareTableActivity.class);
                    outIntent.putExtra("source", "OfferSuccessActivity");
                    outIntent.putExtra("offer1", currentJob);
                    outIntent.putExtra("offer2", addedOffer);
                    startActivity(outIntent);
                    finish();
                }else {
                    Toast.makeText(OfferSuccessActivity.this, "Can't Compare: No Current Job!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
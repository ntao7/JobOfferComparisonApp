package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.data.model.UserManager;
import edu.gatech.seclass.jobcompare6300.data.model.Weights;
import edu.gatech.seclass.jobcompare6300.db.DBHelper;
import edu.gatech.seclass.jobcompare6300.util.JobComparator;
import edu.gatech.seclass.jobcompare6300.util.JobComparatorManager;

public class AdjustActivity extends AppCompatActivity {
    private static final String TAG = "AdjustActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust);
        Log.i(TAG, "AdjustActivity onCreate()");
        // please always use usermanager to get user object
        JobComparator jobComparator = JobComparatorManager.getInstance().getJobComparator();
        User user = UserManager.getInstance().getUser();
        // display infomation if exist
        displayWeights(jobComparator);

        // register save button
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "saveBtn is clicked!");
                AlertDialog.Builder builder = new AlertDialog.Builder(AdjustActivity.this);
                builder.setMessage("Are you sure to save the information?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                EditText salaryText = findViewById(R.id.salaryText);
                                if (String.valueOf(salaryText.getText()).isEmpty()) {
                                    Toast.makeText(AdjustActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int salary = Integer.parseInt(String.valueOf(salaryText.getText()));
                                EditText bonusTest = findViewById(R.id.bonusText);
                                if (String.valueOf(bonusTest.getText()).isEmpty()) {
                                    Toast.makeText(AdjustActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int bonus = Integer.parseInt(String.valueOf(bonusTest.getText()));
                                EditText optionText = findViewById(R.id.optionText);
                                if (String.valueOf(optionText.getText()).isEmpty()) {
                                    Toast.makeText(AdjustActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int option = Integer.parseInt(String.valueOf(optionText.getText()));
                                EditText hbpText = findViewById(R.id.hbpText);
                                if (String.valueOf(hbpText.getText()).isEmpty()) {
                                    Toast.makeText(AdjustActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int hbp = Integer.parseInt(String.valueOf(hbpText.getText()));
                                EditText holidayText = findViewById(R.id.holidayText);
                                if (String.valueOf(holidayText.getText()).isEmpty()) {
                                    Toast.makeText(AdjustActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int holiday = Integer.parseInt(String.valueOf(holidayText.getText()));
                                EditText stipendText = findViewById(R.id.stipendText);


                                if (String.valueOf(stipendText.getText()).isEmpty()) {
                                    Toast.makeText(AdjustActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int stipend = Integer.parseInt(String.valueOf(stipendText.getText()));


                                Weights weights = new Weights(user.getUserId(),salary, bonus, option, hbp, holiday, stipend);
                                jobComparator.setWeights(weights.toMap());
                                DBHelper dbHelper = DBHelper.getInstance(AdjustActivity.this, 1);
                                dbHelper.updateWeightsByUserId(weights);


                                Toast.makeText(AdjustActivity.this, "weights saved!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // register cancel button
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "cancelBtn is clicked!");
                AlertDialog.Builder builder = new AlertDialog.Builder(AdjustActivity.this);
                builder.setMessage("Are you sure to discard the change?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    private void displayWeights(JobComparator jobComparator){
        if(jobComparator == null) return;
        Map<String, Integer> weights = jobComparator.getWeights();

        EditText salaryText = findViewById(R.id.salaryText);
        salaryText.setText(String.valueOf(weights.get("salary")));

        EditText bonusText = findViewById(R.id.bonusText);
        bonusText.setText(String.valueOf(weights.get("bonus")));

        EditText optionText = findViewById(R.id.optionText);
        optionText.setText(String.valueOf(weights.get("option")));

        EditText hbpText = findViewById(R.id.hbpText);
        hbpText.setText(String.valueOf(weights.get("hbp")));

        EditText holidayText = findViewById(R.id.holidayText);
        holidayText.setText(String.valueOf(weights.get("holiday")));

        EditText stipendText = findViewById(R.id.stipendText);
        stipendText.setText(String.valueOf(weights.get("stipend")));
    }


}
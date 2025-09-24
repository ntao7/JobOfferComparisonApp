package edu.gatech.seclass.jobcompare6300.ui;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import edu.gatech.seclass.jobcompare6300.db.DBHelper;


public class JobOfferActivity extends AppCompatActivity {

    private static final String TAG = "JobOfferActivity";
    long jobId;
    long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);
        Log.i(TAG, "JobOfferActivity onCreate()");
        // please always use usermanager to get user object
        User user = UserManager.getInstance().getUser();

        // register save button
        Button saveBtn = findViewById(R.id.addBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "saveBtn is clicked!");
                AlertDialog.Builder builder = new AlertDialog.Builder(JobOfferActivity.this);
                builder.setMessage("Are you sure to save the information?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // todo: 3. save in the sqlite db, so reopening app still ok
                                EditText titleText = findViewById(R.id.titleText);
                                String title = String.valueOf(titleText.getText());
                                if (title.isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText companyText = findViewById(R.id.companyText);
                                String company = String.valueOf(companyText.getText());
                                if (company.isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText cityText = findViewById(R.id.cityText);
                                String city = String.valueOf(cityText.getText());
                                if (city.isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText stateText = findViewById(R.id.stateText);
                                String state = String.valueOf(stateText.getText());
                                if (state.isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText costText = findViewById(R.id.costText);
                                if (String.valueOf(costText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int cost = Integer.parseInt(String.valueOf(costText.getText()));
                                EditText salaryText = findViewById(R.id.salaryText);
                                if (String.valueOf(salaryText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float salary = Float.parseFloat(String.valueOf(salaryText.getText()));
                                EditText bonusText = findViewById(R.id.bonusText);
                                if (String.valueOf(bonusText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float bonus = Float.parseFloat(String.valueOf(bonusText.getText()));
                                EditText optionText = findViewById(R.id.optionText);
                                if (String.valueOf(optionText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float option = Float.parseFloat(String.valueOf(optionText.getText()));
                                EditText hbpText = findViewById(R.id.hbpText);
                                if (String.valueOf(hbpText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float hbp = Float.parseFloat(String.valueOf(hbpText.getText()));
                                EditText holidayText = findViewById(R.id.holidayText);
                                if (String.valueOf(holidayText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int holiday = Integer.parseInt(String.valueOf(holidayText.getText()));
                                EditText stipendText = findViewById(R.id.stipendText);
                                if (String.valueOf(stipendText.getText()).isEmpty()) {
                                    Toast.makeText(JobOfferActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float stipend = Float.parseFloat(String.valueOf(stipendText.getText()));

                                if (hbp > salary * 0.15) {
                                    Toast.makeText(JobOfferActivity.this, "The Home Buying Program Fund cannot be higher than 15% Yearly salary.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                if (holiday < 0 || holiday > 20) {
                                    Toast.makeText(JobOfferActivity.this, "The Personal Choice Holiday value should between 0 and 20.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                if (stipend < 0 || stipend > 75) {
                                    Toast.makeText(JobOfferActivity.this, "The Monthly Internet Stipend value should between 0 and 75.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }

                                Job addedJob = new Job(user.getUserId(), title, company, city, state, cost, salary, bonus, option, hbp, holiday, stipend);

                                // db data persistent
                                DBHelper dbHelper = DBHelper.getInstance(JobOfferActivity.this, 1);
                                dbHelper.insertJobOffer(addedJob);
                                // no need to update
                                // app data persistent
                                user.getJobs().add(addedJob);

                                Intent intent = new Intent(JobOfferActivity.this, OfferSuccessActivity.class);
                                intent.putExtra("source", "CompareActivity");
                                intent.putExtra("added_offer", addedJob);
                                startActivity(intent);
//                                // example: get text from the input
//                                Job offer = new Job(title, company, location, cost, salary, bonus, option, hbp, holiday, stipend);
//                                User user = UserManager.getInstance().getUser();
//
//                                List<Job> jobs = user.getJobs();
//                                jobs.add(offer);
//                                DBHelper dbHelper = DBHelper.getInstance(JobOfferActivity.this, 1);
//                                jobId=dbHelper.insertJobOffer(offer);
//                                long userid=UserManager.getInstance().getUser().getUserId();
//                                dbHelper.updateUserCurrentJobId(userid,jobId);


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
                AlertDialog.Builder builder = new AlertDialog.Builder(JobOfferActivity.this);
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

}
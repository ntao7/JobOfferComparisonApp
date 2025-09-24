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

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.data.model.UserManager;
import edu.gatech.seclass.jobcompare6300.db.DBHelper;

public class CurrentJobActivity extends AppCompatActivity {
    private static final String TAG = "CurrentJobActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);
        Log.i(TAG, "CurrentJobActivity onCreate()");
        // please always use usermanager to get user object
        User user = UserManager.getInstance().getUser();
        // display infomation if exist
        displayJobInfo(user.getCurrentJob());

        // register save button
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "saveBtn is clicked!");
                AlertDialog.Builder builder = new AlertDialog.Builder(CurrentJobActivity.this);
                builder.setMessage("Are you sure to save the information?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // confirm logic, read the input information and save it
                                // save in the sqlite db, so reopening app still ok
                                // example: get text from the input
                                EditText titleText = findViewById(R.id.titleText);
                                String title = String.valueOf(titleText.getText());
                                if (title.isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText companyText = findViewById(R.id.companyText);
                                String company = String.valueOf(companyText.getText());
                                if (company.isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText cityText = findViewById(R.id.cityText);
                                String city = String.valueOf(cityText.getText());
                                if (city.isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText stateText = findViewById(R.id.stateText);
                                String state = String.valueOf(stateText.getText());
                                if (state.isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                EditText costText = findViewById(R.id.costText);
                                if (String.valueOf(costText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int cost = Integer.parseInt(String.valueOf(costText.getText()));
                                EditText salaryText = findViewById(R.id.salaryText);
                                if (String.valueOf(salaryText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float salary = Float.parseFloat(String.valueOf(salaryText.getText()));
                                EditText bonusText = findViewById(R.id.bonusText);
                                if (String.valueOf(bonusText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float bonus = Float.parseFloat(String.valueOf(bonusText.getText()));
                                EditText optionText = findViewById(R.id.optionText);
                                if (String.valueOf(optionText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float option = Float.parseFloat(String.valueOf(optionText.getText()));
                                EditText hbpText = findViewById(R.id.hbpText);
                                if (String.valueOf(hbpText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float hbp = Float.parseFloat(String.valueOf(hbpText.getText()));
                                EditText holidayText = findViewById(R.id.holidayText);
                                if (String.valueOf(holidayText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                int holiday = Integer.parseInt(String.valueOf(holidayText.getText()));
                                EditText stipendText = findViewById(R.id.stipendText);
                                if (String.valueOf(stipendText.getText()).isEmpty()) {
                                    Toast.makeText(CurrentJobActivity.this, "The field cannot be null.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                float stipend = Float.parseFloat(String.valueOf(stipendText.getText()));

                                if (hbp > salary * 0.15) {
                                    Toast.makeText(CurrentJobActivity.this, "The Home Buying Program Fund cannot be higher than 15% Yearly salary.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                if (holiday < 0 || holiday > 20) {
                                    Toast.makeText(CurrentJobActivity.this, "The Personal Choice Holiday value should between 0 and 20.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                if (stipend < 0 || stipend > 75) {
                                    Toast.makeText(CurrentJobActivity.this, "The Monthly Internet Stipend value should between 0 and 75.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    return;
                                }
                                Job addedJob = new Job(user.getUserId(), title, company, city, state, cost, salary, bonus, option, hbp, holiday, stipend);

                                Job currentJob = user.getCurrentJob();
                                DBHelper dbHelper = DBHelper.getInstance(CurrentJobActivity.this, 1);

                                if(currentJob==null){
                                    // db data persistent
                                    long jobId = dbHelper.insertJobOffer(addedJob);
                                    addedJob.setId(jobId);
                                    dbHelper.updateUserCurrentJobId(user.getUserId(), jobId);
                                    // app data persistent
                                    user.getJobs().add(addedJob);
                                }else {
                                    // update
                                    // we assume that currentJobId is fixed
                                    addedJob.setId(currentJob.getId());
                                    dbHelper.updateCurrentJob(addedJob);
                                    for (Job j : user.getJobs()){
                                        if(j.getId()== currentJob.getId()){
                                            user.getJobs().remove(j);
                                            user.getJobs().add(addedJob);
                                        }
                                    }
                                }
                                // app data persistent
                                user.setCurrentJob(addedJob);

//                                long userId = UserManager.getInstance().getUser();
//                                String userid = String.valueOf(userId);
//                                String comp=String.valueOf(company);
//                                String tit=String.valueOf(title);
//                                String loc=String.valueOf(location);
//                                if (userId != -1) {
//                                    return;
//                                } else {
//                                    String salar = String.valueOf(salary);
//                                    Long currentJobId = dbHelper.getCurrentJobIdByUserId(userid,tit,comp,loc,salar);
//                                    Job updatedJob = new Job();
//                                    updatedJob.setTitle(title);
//                                    updatedJob.setCompany(company);
//                                    updatedJob.setLocation(location);
//                                    updatedJob.setCost(cost);
//                                    updatedJob.setSalary(salary);
//                                    updatedJob.setBonus(bonus);
//                                    updatedJob.setOption(option);
//                                    updatedJob.setHbp(hbp);
//                                    updatedJob.setHoliday(holiday);
//                                    updatedJob.setStipend(stipend);
//                                    if (currentJobId != null) {
//                                        String condition = "id = " + currentJobId;
//                                        int count = dbHelper.update(updatedJob, condition);
//                                        if (count > 0) {
//                                            Log.d(TAG, "Job updated successfully.");
//                                        } else {
//
//                                            Log.d(TAG, "Failed to update the job.");
//                                        }
//                                    } else {
//
//                                        Log.d(TAG, "Current job ID not found for the user.");
//                                    }
//                                }
                                Toast.makeText(CurrentJobActivity.this, "job saved!", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(CurrentJobActivity.this);
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

    private void displayJobInfo(Job job){
        if(job == null) return;

        EditText titleText = findViewById(R.id.titleText);
        titleText.setText(String.valueOf(job.getTitle()));

        EditText companyText = findViewById(R.id.companyText);
        companyText.setText(String.valueOf(job.getCompany()));

        EditText cityText = findViewById(R.id.cityText);
        cityText.setText(String.valueOf(job.getCity()));

        EditText stateText = findViewById(R.id.stateText);
        stateText.setText(String.valueOf(job.getState()));

        EditText costText = findViewById(R.id.costText);
        costText.setText(String.valueOf(job.getCost()));

        EditText salaryText = findViewById(R.id.salaryText);
        salaryText.setText(String.valueOf(job.getSalary()));

        EditText bonusText = findViewById(R.id.bonusText);
        bonusText.setText(String.valueOf(job.getBonus()));

        EditText optionText = findViewById(R.id.optionText);
        optionText.setText(String.valueOf(job.getOption()));

        EditText hbpText = findViewById(R.id.hbpText);
        hbpText.setText(String.valueOf(job.getHbp()));

        EditText holidayText = findViewById(R.id.holidayText);
        holidayText.setText(String.valueOf(job.getHoliday()));

        EditText stipendText = findViewById(R.id.stipendText);
        stipendText.setText(String.valueOf(job.getStipend()));
    }
}
package edu.gatech.seclass.jobcompare6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import android.util.TypedValue;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.data.model.UserManager;
import edu.gatech.seclass.jobcompare6300.util.JobComparator;
import edu.gatech.seclass.jobcompare6300.util.JobComparatorManager;

public class CompareActivity extends AppCompatActivity {
    private static final String TAG = "CompareActivity";
    private Map<Integer, Integer> rowIdToJobIdMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        Log.i(TAG, "CompareActivity onCreate()");

        User user = UserManager.getInstance().getUser();
        JobComparator jobComparator = JobComparatorManager.getInstance().getJobComparator();
        // display information if exist
        // from user
//
//        jobs.add(new Job("Software EngineerSoftware EngineerSoftware Engineer", "Google", 100, 10, 1));
//        jobs.add(new Job("Data Scientist", "Facebook", 100, 10, 1));
//        jobs.add(new Job("Product Manager", "Amazon", 100, 10, 1));
//        jobs.add(new Job("UX Designer", "Apple", 100, 10, 1));
//        jobs.add(new Job("Quality Assurance", "Netflix", 100, 10, 1));
//        jobs.add(new Job("Software EngineerSoftware EngineerSoftware Engineer", "Google", 100, 10, 1));
//        jobs.add(new Job("Software EngineerSoftware EngineerSoftware Engineer", "Google", 100, 10, 1));
//        jobs.add(new Job("Data Scientist", "Facebook", 100, 10, 1));
//        jobs.add(new Job("Product Manager", "Amazon", 100, 10, 1));
//        jobs.add(new Job("UX Designer", "Apple", 100, 10, 1));
//        jobs.add(new Job("Quality Assurance", "Netflix", 100, 10, 1));
//        jobs.add(new Job("Software EngineerSoftware EngineerSoftware Engineer", "Google", 100, 10, 1));
//        jobs.add(new Job("Software EngineerSoftware EngineerSoftware Engineer", "Google", 100, 10, 1));
//        jobs.add(new Job("Data Scientist", "Facebook", 100, 10, 1));
//        jobs.add(new Job("Product Manager", "Amazon", 100, 10, 1));
//        jobs.add(new Job("UX Designer", "Apple", 100, 10, 1));
//        jobs.add(new Job("Quality Assurance", "Netflix", 100, 10, 1));
//        jobs.add(new Job("Software EngineerSoftware EngineerSoftware Engineer", "Google", 100, 10, 1));
//        user.setJobs(jobs);
        List<Job> jobs = user.getJobs();
        Collections.sort(jobs, Collections.reverseOrder(jobComparator));
        Long currentJobId = null;
        if(user.getCurrentJob() != null){
            currentJobId = user.getCurrentJob().getId();
        }
        displayJobs(jobs, currentJobId);

        // register compare btn
        Button compareBtn = findViewById(R.id.compareBtn);
        // Enable the compare button if there is a current job and at least one job offer,
        // or if there is no current job but at least two job offers
        if ((user.getCurrentJob() != null && !jobs.isEmpty()) ||
                (user.getCurrentJob() == null && jobs.size() >= 2)) {
            compareBtn.setEnabled(true);
        } else {
            compareBtn.setEnabled(false);
        }

        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "compareBtn is clicked!");
                List<Job> selectedJobs = new ArrayList<>();
                List<String> message = new ArrayList<>();
                for (Integer rowId : rowIdToJobIdMap.keySet()) {
                    Integer jobInd = rowIdToJobIdMap.get(rowId);
                    Job job = jobs.get(jobInd);
                    message.add(job.toString());
                    selectedJobs.add(job);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(CompareActivity.this);
                builder.setTitle("Are you sure to compare these jobs?")
                        .setItems(message.toArray(new String[0]), null)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent outIntent = new Intent(CompareActivity.this, CompareTableActivity.class);
                                outIntent.putExtra("source", "CompareActivity");
                                outIntent.putExtra("offer1", selectedJobs.get(0));
                                outIntent.putExtra("offer2", selectedJobs.get(1));
                                startActivity(outIntent);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(CompareActivity.this);
                builder.setMessage("Are you sure to cancel the operation?")
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

    private void displayJobs(List<Job> jobs, Long currentJobId) {
        for (int i = 0; i < jobs.size(); i++) {
            int jobInd = i;
            Job offer = jobs.get(jobInd);
            // a row = relativelayout(checkbox) + textview1 + textview2
            TableRow row = new TableRow(this);
            row.setId(View.generateViewId());
            // new a checkbox
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText("");
            checkBox.setTag("CheckBox_" + i);
            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            rlp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            checkBox.setLayoutParams(rlp);
            // set checkbox listener
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (rowIdToJobIdMap.size() < 2) {
                        rowIdToJobIdMap.put(row.getId(), jobInd);
                    } else {
                        checkBox.setChecked(false);
                        Toast.makeText(this, "You can only select two items!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    rowIdToJobIdMap.remove(row.getId());
                }
            });
            // put check box into relativelayout
            RelativeLayout relativeLayout = new RelativeLayout(this);
            TableRow.LayoutParams relativeParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 20);
            relativeLayout.setLayoutParams(relativeParams);
            relativeLayout.setBackgroundResource(R.drawable.textview_border);
            relativeLayout.addView(checkBox);
            // put relativelayout into row
            row.addView(relativeLayout);
            // new textview, put into row
            TextView titleView = new TextView(this);
            titleView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 40));
            titleView.setPadding(8, 8, 8, 8);
            titleView.setBackgroundResource(R.drawable.textview_border);
            titleView.setGravity(Gravity.CENTER);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            // indicated current job
            if(currentJobId!=null && currentJobId.equals(offer.getId())){
                titleView.setText(offer.getTitle()+" (Current Job)");
            }else {
                titleView.setText(offer.getTitle());
            }
            row.addView(titleView);
            // new textview, put into row
            TextView companyView = new TextView(this);
            companyView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 40));
            companyView.setPadding(8, 8, 8, 8);
            companyView.setBackgroundResource(R.drawable.textview_border);
            companyView.setGravity(Gravity.CENTER);
            companyView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            companyView.setText(offer.getCompany());
            row.addView(companyView);
            // put row into table
            TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
            tableLayout.addView(row, i + 1);
        }
    }


}
package edu.gatech.seclass.jobcompare6300.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.data.model.Weights;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CompareOffers.db";
    private static final int DATABASE_VERSION = 1;
    private static DBHelper mHelper;
    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    public static synchronized DBHelper getInstance(Context context, int version) {
        if (version > 0 && mHelper == null) {
            mHelper = new DBHelper(context, version);
        } else if (mHelper == null) {
            mHelper = new DBHelper(context, DATABASE_VERSION);
        }
        return mHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String drop_sql = "DROP TABLE IF EXISTS JobOffers;";

//        db.execSQL(drop_sql);

        String create_sql = (
                "CREATE TABLE IF NOT EXISTS JobOffers (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "userId INTEGER," +
                        "title VARCHAR NOT NULL," +
                        "company VARCHAR," +
                        "location VARCHAR," +
                        "cost INTEGER," +
                        "salary FLOAT," +
                        "bonus FLOAT," +
                        "option FLOAT," +
                        "hbp FLOAT," +
                        "holiday INTEGER," +
                        "stipend FLOAT," +
                        "FOREIGN KEY(userId) REFERENCES Users(userId))" // 添加外键约束，与Users表的userId关联
        );
        db.execSQL(create_sql);



        String createUserTableSql = "CREATE TABLE IF NOT EXISTS Users (" +
                "userId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "currentJobId INTEGER," +
                "FOREIGN KEY(currentJobId) REFERENCES JobOffers(id))";
        db.execSQL(createUserTableSql);

        // weights
        String createWeightsTableSql = "CREATE TABLE IF NOT EXISTS Weights (" +
                "userId INTEGER PRIMARY KEY," +
                "salary INTEGER," +
                "bonus INTEGER," +
                "option INTEGER," +
                "hbp INTEGER," +
                "holiday INTEGER," +
                "stipend INTEGER," +
                "FOREIGN KEY(userId) REFERENCES Users(userId))";
        db.execSQL(createWeightsTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS JobOffers");
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Weights");
        onCreate(db);
    }

    /*
        IMPORTANT:
            A real app needs to have a login page where the user enters their
        user ID to retrieve relevant information.
            However, this requirement is not mentioned in the document.
            Therefore, we directly retrieve the user ID of the first record to simulate the behavior of being
        logged in.
    * */
    public User getFirstUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users LIMIT 1", null);
        User user = null;
        if (cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex("userId");
            int currentJobIdIndex = cursor.getColumnIndex("currentJobId");

            if (userIdIndex != -1 && currentJobIdIndex != -1) {
                int userId = cursor.getInt(userIdIndex);
                int currentJobId = cursor.getInt(currentJobIdIndex);
                List<Job> jobs = getJobListByUserId(userId);
                Job currentJob = getJobById(currentJobId);
                user = new User(userId, jobs, currentJob);
            }
        }
        cursor.close();
        return user;
    }


    public long insertJobOffer(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", job.getUserId()); // add userId
        values.put("title", job.getTitle());
        values.put("company", job.getCompany());
        values.put("location", job.getCity());
        values.put("cost", job.getCost());
        values.put("salary", job.getSalary());
        values.put("bonus", job.getBonus());
        values.put("option", job.getOption());
        values.put("hbp", job.getHbp());
        values.put("holiday", job.getHoliday());
        values.put("stipend", job.getStipend());
        long newRowId = db.insert("JobOffers", null, values);
        // db.close();
        return newRowId;
    }

    public int updateCurrentJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", job.getUserId());
        values.put("title", job.getTitle());
        values.put("company", job.getCompany());
        values.put("location", job.getCity());
        values.put("cost", job.getCost());
        values.put("salary", job.getSalary());
        values.put("bonus", job.getBonus());
        values.put("option", job.getOption());
        values.put("hbp", job.getHbp());
        values.put("holiday", job.getHoliday());
        values.put("stipend", job.getStipend());

        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(job.getId()) };

        int count = db.update(
                "JobOffers",
                values,
                selection,
                selectionArgs);

        // db.close();
        return count;
    }


    public Job getJobById(long jobId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "userId",
                "title",
                "company",
                "location",
                "cost",
                "salary",
                "bonus",
                "option",
                "hbp",
                "holiday",
                "stipend"
        };

        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(jobId)};

        Cursor cursor = db.query(
                "JobOffers",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Job job = null;

        if (cursor != null && cursor.moveToFirst()) {
            job = new Job();
            job.setTitle(cursor.getString(1));
            job.setCompany(cursor.getString(2));
            job.setCity(cursor.getString(3));
            job.setCost(cursor.getInt(4));
            job.setSalary(cursor.getFloat(5));
            job.setBonus(cursor.getFloat(6));
            job.setOption(cursor.getFloat(7));
            job.setHbp(cursor.getFloat(8));
            job.setHoliday(cursor.getInt(9));
            job.setStipend(cursor.getFloat(10));
        }

        if (cursor != null) {
            cursor.close();
        }

        return job;
    }

    public List<Job> getJobListByUserId(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "userId",
                "title",
                "company",
                "location",
                "cost",
                "salary",
                "bonus",
                "option",
                "hbp",
                "holiday",
                "stipend"
        };

        String selection = "userId = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                "JobOffers",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<Job> jobs = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Job job = new Job();
                job.setId(cursor.getLong(0));
                job.setUserId(cursor.getLong(1));
                job.setTitle(cursor.getString(2));
                job.setCompany(cursor.getString(3));
                job.setCity(cursor.getString(4));
                job.setCost(cursor.getInt(5));
                job.setSalary(cursor.getFloat(6));
                job.setBonus(cursor.getFloat(7));
                job.setOption(cursor.getFloat(8));
                job.setHbp(cursor.getFloat(9));
                job.setHoliday(cursor.getInt(10));
                job.setStipend(cursor.getFloat(11));
                jobs.add(job);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        // db.close();

        return jobs;
    }

    public long updateUserCurrentJobId(long userId, long newJobId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("currentJobId", newJobId);

        String selection = "userId = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        long count = db.update("Users", values, selection, selectionArgs);
        // db.close();
        return count;
    }

    public long insertUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.putNull("currentJobId");
        long userId = db.insert("Users", null, values);
        // db.close();
        return userId;
    }

    public Job getCurrentJobByUserId(Long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "userId",
                "title",
                "company",
                "location",
                "cost",
                "salary",
                "bonus",
                "option",
                "hbp",
                "holiday",
                "stipend"
        };

        String selection = "userId = ? AND id = (SELECT currentJobId FROM Users WHERE userId = ?)";
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(userId)};

        Cursor cursor = db.query(
                "JobOffers",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Job job = null;

        if (cursor != null && cursor.moveToFirst()) {
            job = new Job();
            job.setId(cursor.getLong(0));
            job.setUserId(cursor.getLong(1));
            job.setTitle(cursor.getString(2));
            job.setCompany(cursor.getString(3));
            job.setCity(cursor.getString(4));
            job.setCost(cursor.getInt(5));
            job.setSalary(cursor.getFloat(6));
            job.setBonus(cursor.getFloat(7));
            job.setOption(cursor.getFloat(8));
            job.setHbp(cursor.getFloat(9));
            job.setHoliday(cursor.getInt(10));
            job.setStipend(cursor.getFloat(11));
        }

        if (cursor != null) {
            cursor.close();
        }

        // db.close();
        return job;
    }

    // Weights persistent
    public Weights getWeightsByUserId(Long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "salary",
                "bonus",
                "option",
                "hbp",
                "holiday",
                "stipend"
        };

        String selection = "userId = ?";
        String[] selectionArgs = { String.valueOf(userId) };

        Cursor cursor = db.query(
                "Weights",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Weights weights = null;
        if (cursor.moveToFirst()) {
            weights = new Weights(
                    userId,
                    cursor.getInt(cursor.getColumnIndexOrThrow("salary")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("bonus")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("option")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("hbp")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("holiday")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("stipend"))
            );
        }
        cursor.close();

        return weights;
    }

    // 更新用户的权重
    public int updateWeightsByUserId(Weights weights) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("salary", weights.getSalary());
        values.put("bonus", weights.getBonus());
        values.put("option", weights.getOption());
        values.put("hbp", weights.getHbp());
        values.put("holiday", weights.getHoliday());
        values.put("stipend", weights.getStipend());

        String selection = "userId = ?";
        String[] selectionArgs = { String.valueOf(weights.getUserId()) };

        return db.update("Weights", values, selection, selectionArgs);
    }

    // 插入新的权重数据
    public long insertWeights(Weights weights) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", weights.getUserId());
        values.put("salary", weights.getSalary());
        values.put("bonus", weights.getBonus());
        values.put("option", weights.getOption());
        values.put("hbp", weights.getHbp());
        values.put("holiday", weights.getHoliday());
        values.put("stipend", weights.getStipend());

        long newRowId = db.insert("Weights", null, values);
        return newRowId;
    }
    /*
        IMPORTANT:
            According to the requirements document, we do not need to provide function below,
            so it will be commented out here.
    * */

//    public int update(Job job, String condition) {
//        ContentValues values = new ContentValues();
//        values.put("title", job.getTitle());
//        values.put("company", job.getCompany());
//        values.put("location", job.getLocation());
//        values.put("cost", job.getCost());
//        values.put("salary", job.getSalary());
//        values.put("bonus", job.getBonus());
//        values.put("option", job.getOption());
//        values.put("hbp", job.getHbp());
//        values.put("holiday", job.getHoliday());
//        values.put("stipend", job.getStipend());
//
//        int count = getWritableDatabase().update("JobOffers", values, condition, null);
//        return count;
//    }



//    public Long getCurrentJobIdByUserId(String userId, String title, String company, String location, String salary) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Long currentJobId = null;
//
//        String query = "SELECT JobOffers.id " +
//                "FROM Users INNER JOIN JobOffers ON Users.currentJobId = JobOffers.id " +
//                "WHERE Users.userId = ? AND JobOffers.title = ? AND JobOffers.company = ? AND JobOffers.location = ? AND JobOffers.salary = ?";
//
//
//        String salaryString = String.valueOf(salary);
//
//
//        String[] selectionArgs = new String[]{String.valueOf(userId), title, company, location, salaryString};
//
//        Cursor cursor = db.rawQuery(query, selectionArgs);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            currentJobId = cursor.getLong(cursor.getColumnIndexOrThrow("id")); // Fetch the job ID
//            cursor.close();
//        }
//
//        return currentJobId; // Return the found job ID or null if not found
//    }


//    public Cursor getUserById(long userId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] projection = {
//                "userId",
//                "currentJobId"
//        };
//        String selection = "userId = ?";
//        String[] selectionArgs = {String.valueOf(userId)};
//        Cursor cursor = db.query(
//                "Users",
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//        return cursor;
//    }


}



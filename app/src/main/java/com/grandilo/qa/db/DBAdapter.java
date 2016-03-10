package com.grandilo.qa.db;

/**
 * Created by calistus on 10/5/2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    static final String DATABASE_NAME = "questions_warehouse";
    static final String TABLE_QUESTIONS = "questions";
    static final String TABLE_ANSWERS = "answer";
    static final int DATABASE_VERSION = 5;

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Empty since DB has been pre_created
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    public Cursor getQuestions(long rowId) throws SQLException {
        Cursor mCursor =
                db.query(true, TABLE_QUESTIONS, new String[]{"id",
                                "questions_set"}, "id" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getAnswers(long rowId) throws SQLException {
        Cursor mCursor =
                db.query(true, TABLE_ANSWERS, new String[]{"id",
                                "ans1", "ans2", "ans3", "answer4", "answer_set"}, "id" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
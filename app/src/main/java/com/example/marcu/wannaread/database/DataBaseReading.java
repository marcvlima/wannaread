package com.example.marcu.wannaread.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tainaviriato on 21/06/17.
 */

public class DataBaseReading extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "banco.db";
    public static final String TABLE = "readings";
    public static final String ID = "_id";
    public static final String READING_NAME = "readingName";
    public static final String READING_AUTHOR = "readingAuthor";
    public static final String READING_PRIORITY = "readingPriority";
    public static final String READING_PRIORITY_NAME = "readingPriorityName";
    public static final String READING_GENRE = "readingGenre";
    public static final String READING_DATE = "readingDate";
    public static final String READING_SOURCE = "readingSource";
    public static final String READING_PAGES_NUMBER = "readingPagesNumber";
    public static final String READING_PAGES_CURRENT = "readingPagesCurrent";
    public static final String READING_STATUS = "readingStatus";
    public static final int VERSAO = 1;


    public DataBaseReading(Context context) {
        super(context, DATABASE_NAME, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ("
                + ID + " INTEGER primary key autoincrement, "
                + READING_NAME + " TEXT, "
                + READING_AUTHOR + " TEXT, "
                + READING_PRIORITY + " INTEGER, "
                + READING_PRIORITY_NAME + " TEXT, "
                + READING_GENRE + " TEXT, "
                + READING_SOURCE + " TEXT, "
                + READING_DATE + " text, "
                + READING_PAGES_NUMBER + " INTEGER, "
                + READING_PAGES_CURRENT + " TEXT, "
                + READING_STATUS + " INTEGER DEFAULT 1 "
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

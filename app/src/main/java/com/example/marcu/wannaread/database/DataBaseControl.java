package com.example.marcu.wannaread.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

/**
 * Created by tainaviriato on 21/06/17.
 */

public class DataBaseControl {

    private SQLiteDatabase db;
    private DataBaseReading banco;

    public DataBaseControl(Context context) {
        banco = new DataBaseReading(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String addReading(String readingName, String readingAuthor, String readingPriorityName, String readingGenre, String readingSource) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(DataBaseReading.READING_NAME, readingName);
        valores.put(DataBaseReading.READING_AUTHOR, readingAuthor);
        valores.put(DataBaseReading.READING_PRIORITY_NAME, readingPriorityName);
        valores.put(DataBaseReading.READING_PRIORITY, this.getPriorityByName(readingPriorityName));
        valores.put(DataBaseReading.READING_GENRE, readingGenre);
        valores.put(DataBaseReading.READING_SOURCE, readingSource);
        String date = this.getCurrentDate();
        valores.put(DataBaseReading.READING_DATE, date);
        valores.put(DataBaseReading.READING_STATUS, 1);
        valores.put(DataBaseReading.READING_PAGES_NUMBER, 0);
        valores.put(DataBaseReading.READING_PAGES_CURRENT, 0);

        resultado = db.insert(DataBaseReading.TABLE, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao adicionar leitura";
        } else {
            return "Leitura adicionada com sucesso";
        }

    }

    public int getPriorityByName(String priorityName) {
        switch (priorityName.toLowerCase()) {
            case "se tiver tempo":
                return 1;
            case "muito interessado":
                return 100;
            case "próxima leitura":
                return 200;
        }
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date current = new Date();
        return dateFormat.format(current);
    }

    public Cursor loadReadings() {
        Cursor cursor;
        db = banco.getReadableDatabase();
        String orderBy = DataBaseReading.READING_PRIORITY + " DESC ";
        cursor = db.query(DataBaseReading.TABLE, new String[]{}, null, null, null, null, orderBy, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public boolean updateReading(String id, int pagesNumber, int pagesCurrent) {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseReading.READING_PAGES_NUMBER, pagesNumber);
        cv.put(DataBaseReading.READING_PAGES_CURRENT, pagesCurrent);
        cv.put(DataBaseReading.READING_STATUS, 2); // Leitura iniciada

        db = banco.getWritableDatabase();
        int rows = db.update(DataBaseReading.TABLE, cv, DataBaseReading.ID + " = " + id, null);

        return rows > 0;
    }

    public void deleteReading(int id) {
        String where = DataBaseReading.ID + " = " + id;
        db = banco.getReadableDatabase();
        db.delete(DataBaseReading.TABLE, where, null);
        db.close();
    }
}

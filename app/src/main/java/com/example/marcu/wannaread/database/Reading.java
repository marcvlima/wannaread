package com.example.marcu.wannaread.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by marcus on 05/11/17.
 */

public class Reading implements Parcelable {

    private String readingName;
    private String id;
    private String readingAuthor;
    private int readingPriority;
    private String readingPriorityName;
    private String readingGenre;
    private String readingSource;
    private int readingPagesNumber;
    private int readingPagesCurrent;
    private int readingStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadingName() {
        return readingName;
    }

    public void setReadingName(String readingName) {
        this.readingName = readingName;
    }

    public String getReadingAuthor() {
        return readingAuthor;
    }

    public void setReadingAuthor(String readingAuthor) {
        this.readingAuthor = readingAuthor;
    }

    public int getReadingPriority() {
        return readingPriority;
    }

    public void setReadingPriority(int readingPriority) {
        this.readingPriority = readingPriority;
    }

    public String getReadingPriorityName() {
        return readingPriorityName;
    }

    public void setReadingPriorityName(String readingPriorityName) {
        this.readingPriorityName = readingPriorityName;
    }

    public String getReadingGenre() {
        return readingGenre;
    }

    public void setReadingGenre(String readingGenre) {
        this.readingGenre = readingGenre;
    }

    public String getReadingSource() {
        return readingSource;
    }

    public void setReadingSource(String readingSource) {
        this.readingSource = readingSource;
    }

    public int getReadingPagesNumber() {
        return readingPagesNumber;
    }

    public void setReadingPagesNumber(int readingPagesNumber) {
        this.readingPagesNumber = readingPagesNumber;
    }

    public int getReadingPagesCurrent() {
        return readingPagesCurrent;
    }

    public void setReadingPagesCurrent(int readingPagesCurrent) {
        this.readingPagesCurrent = readingPagesCurrent;
    }

    public int getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(int readingStatus) {
        this.readingStatus = readingStatus;
    }

    public Reading(String id, String readingName, String readingAuthor, String readingPriorityName, String readingGenre, String readingSource) {
        this.id = id;
        this.readingName = readingName;
        this.readingAuthor = readingAuthor;
        this.readingPriorityName = readingPriorityName;
        this.readingGenre = readingGenre;
        this.readingSource = readingSource;
        this.readingStatus = 0; // New Reading
    }

    public Reading() {

    }

    protected Reading(Parcel in) {
        id = in.readString();
        readingName = in.readString();
        readingAuthor = in.readString();
        readingPriority = in.readInt();
        readingPriorityName = in.readString();
        readingGenre = in.readString();
        readingSource = in.readString();
        readingPagesNumber = in.readInt();
        readingPagesCurrent = in.readInt();
        readingStatus = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(readingName);
        dest.writeString(readingAuthor);
        dest.writeInt(readingPriority);
        dest.writeString(readingPriorityName);
        dest.writeString(readingGenre);
        dest.writeString(readingSource);
        dest.writeInt(readingPagesNumber);
        dest.writeInt(readingPagesCurrent);
        dest.writeInt(readingStatus);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Reading> CREATOR = new Parcelable.Creator<Reading>() {
        @Override
        public Reading createFromParcel(Parcel in) {
            return new Reading(in);
        }

        @Override
        public Reading[] newArray(int size) {
            return new Reading[size];
        }
    };
}
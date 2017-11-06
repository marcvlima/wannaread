package com.example.marcu.wannaread.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marcus on 05/11/17.
 */

public class Reading implements Parcelable {

    public String getReadingName() {
        return readingName;
    }

    public void setReadingName(String readingName) {
        this.readingName = readingName;
    }

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
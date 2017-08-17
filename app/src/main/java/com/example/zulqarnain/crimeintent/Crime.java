package com.example.zulqarnain.crimeintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public class Crime {
    private String crimeTitle;
    private UUID crimeId;
    private Date crimeDate;
    private boolean mCrimeSolved;

    public Crime() {
        this.crimeDate = new Date();
        this.crimeId = UUID.randomUUID();
    }


    public String getCrimeTitle() {
        return crimeTitle;
    }

    public void setCrimeTitle(String crimeTitle) {
        this.crimeTitle = crimeTitle;
    }

    public Date getCrimeDate() {
        return crimeDate;
    }

    public boolean ismCrimeSolved() {
        return mCrimeSolved;
    }

    public void setmCrimeSolved(boolean mCrimeSolved) {
        this.mCrimeSolved = mCrimeSolved;
    }

    public void setCrimeDate(Date crimeDate) {
        this.crimeDate = crimeDate;
    }

    public UUID getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(UUID crimeId) {
        this.crimeId = crimeId;
    }
}


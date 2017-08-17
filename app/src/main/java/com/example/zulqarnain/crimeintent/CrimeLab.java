package com.example.zulqarnain.crimeintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public class CrimeLab {

    List<Crime> mCrimes;
    public static CrimeLab mCrimeLab;
    public static CrimeLab get(Context context) {

        if (mCrimeLab == null) {
            mCrimeLab = new CrimeLab(context);
        }
        return mCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setCrimeTitle("Crime " + i);
            crime.setmCrimeSolved(i%2==0);
            mCrimes.add(crime);
        }

    }


    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID crimeId) {
        for (Crime crime : mCrimes) {
            if (crime.getCrimeId().equals(crimeId)) {
                return crime;
            }
        }
        return null;
    }
}

package com.example.zulqarnain.crimeintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private final static String CRIME_ID="com.zeelog.crimeactivity.crimeid";

    @Override
    public Fragment createFragement() {
        UUID cimeID = (UUID) getIntent().getSerializableExtra(CRIME_ID);
       return new CrimeFragment(cimeID);
    }

    public static Intent newIntent(Context packageName, UUID crimID){
        Intent intent = new Intent(packageName , CrimeActivity.class);
        intent.putExtra(CRIME_ID,crimID);
        return intent;
    }


}

package zaadjava.com.crimetest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

import static zaadjava.com.crimetest.CrimeFragment.newInstance;

public class CrimeActivity extends SingleFragmentActivity {


    private static final String EXTRA_CRIME_ID="com.zeelog.android.crimtest.crimr_id";

    public static Intent newIntent(Context pakageContext , UUID crimId){
        Intent intent = new Intent(pakageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimId);
        return intent;
    }
    @Override
    protected Fragment createFragement()
    {

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return newInstance(crimeId);
    }

    public static boolean hasCrimeChanged(Intent intent){
        return CrimeFragment.hasCrimeChanged(intent);
    }
    public static UUID getCrimeID(Intent intent){
        return CrimeFragment.getCrimeID(intent);
    }
}

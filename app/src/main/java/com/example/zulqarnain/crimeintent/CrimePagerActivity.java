package com.example.zulqarnain.crimeintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Zul Qarnain on 8/18/2017.
 */

public class CrimePagerActivity extends AppCompatActivity {
    List<Crime> mCrimes;
     private static final String CRIME_ID = "com.zeelog.crime_id";
    private UUID crimeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_pager_activity);

        mCrimes = CrimeLab.get(this).getCrimes();
        crimeId = (UUID) getIntent().getSerializableExtra(CRIME_ID);
        ViewPager viewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return CrimeFragment.newInstance(mCrimes.get(position).getCrimeId());
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mCrimes.get(position).getCrimeTitle();
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
                if(mCrimes.get(i).getCrimeId().equals(crimeId)){
                    viewPager.setCurrentItem(i);
                    break;
                }
        }
    }


    public static Intent newIntent(Context packageName, UUID crimID) {
        Intent intent = new Intent(packageName, CrimePagerActivity.class);
        intent.putExtra(CRIME_ID, crimID);
        return intent;
    }

}

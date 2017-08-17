package zaadjava.com.crimetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public class CrimePagerActivity extends AppCompatActivity{
    private final static  String CRIME_ID_KEY="com.zeelog.crime_id";
    private ViewPager viewPager;
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId= (UUID) getIntent().getSerializableExtra(CRIME_ID_KEY);
        viewPager =(ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimLab.get(this).getmCrimes();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                Fragment crimeFragment = zaadjava.com.crimetest.CrimeFragment.newInstance(crime.getId());
                return crimeFragment;
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }

            @Override
            public CharSequence getPageTitle(int position){

                return mCrimes.get(position).getmTitle();
            }

        });

        for(int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                viewPager.setCurrentItem(i);
                break;

            }
        }


    }

    public static Intent newIntent(Context packagename, UUID crimeId){
        Intent intent = new Intent(packagename,CrimePagerActivity.class);
        intent.putExtra(CRIME_ID_KEY,crimeId);
        return intent;
    }
}

package com.example.zulqarnain.crimeintent;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public class CrimeListActivty extends SingleFragmentActivity {

    @Override
    public Fragment createFragement() {
        return new CrimeListFragment();
    }
}

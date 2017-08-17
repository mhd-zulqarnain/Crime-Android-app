package com.example.zulqarnain.crimeintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public abstract Fragment createFragement();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragement();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }
}

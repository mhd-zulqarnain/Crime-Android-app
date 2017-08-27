package com.example.zulqarnain.crimeintent;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Zul Qarnain on 8/17/2017.
 */

public class Messege {
    public  static void show(Context context,String messege){
        Toast.makeText(context,messege,Toast.LENGTH_SHORT).show();
    }
}

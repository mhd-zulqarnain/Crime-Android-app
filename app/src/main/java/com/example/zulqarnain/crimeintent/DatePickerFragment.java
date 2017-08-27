package com.example.zulqarnain.crimeintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Zul Qarnain on 8/22/2017.
 */

public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.date_dailog,null);
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("Date").setPositiveButton("Ok",null).create();

    }


    public void show(FragmentManager manager, String dialogDate) {
    }
}

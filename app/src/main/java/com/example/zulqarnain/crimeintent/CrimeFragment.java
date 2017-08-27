package com.example.zulqarnain.crimeintent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public class CrimeFragment extends Fragment {

    private EditText crimeTitle;
    Crime crime;
    private TextView mCrimeDate;
    private EditText mCrimeTitle;
    private CheckBox mCheckBox;
    private final static String AGR_CRIME_ID="com.zeelog.agr_id";
    private final static String DIALOG_DATE="DialogeDate";

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args= new Bundle();
        args.putSerializable(AGR_CRIME_ID,crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getArguments().getSerializable(AGR_CRIME_ID);
         crime = CrimeLab.get(getActivity()).getCrime(crimeID);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.crime_fragment,container,false);
        mCrimeDate = (Button) view.findViewById(R.id.crime_date);
        mCrimeTitle = (EditText) view.findViewById(R.id.crime_title);
        mCheckBox= (CheckBox) view.findViewById(R.id.is_crime_solved);

        mCrimeTitle.setText(crime.getCrimeTitle());
        mCrimeDate.setText(crime.getCrimeDate().toString());
        mCheckBox.setChecked(crime.ismCrimeSolved());
        mCrimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    crime.setCrimeTitle(charSequence.toString());
                    mCrimeDate.setText(mCrimeDate.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mCrimeDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager manager= getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager,"A");
                Toast.makeText(getActivity(),"The Value is clicked ",Toast.LENGTH_SHORT).show();
            }
        });
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                crime.setmCrimeSolved(b);
            }
        });
        return view;
    }




}

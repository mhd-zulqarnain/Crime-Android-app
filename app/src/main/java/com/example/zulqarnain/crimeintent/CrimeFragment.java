package com.example.zulqarnain.crimeintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.CRIME_ID);
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
                    mCrimeDate.setEnabled(false);
                    mCrimeDate.setText(mCrimeDate.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }
}

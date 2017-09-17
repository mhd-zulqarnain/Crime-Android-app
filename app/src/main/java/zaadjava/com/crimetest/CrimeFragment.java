package zaadjava.com.crimetest;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mCheckBox;

    private boolean hasCrimeChanged = false;
    private  static final String ARG_CRIME_ID="com.zeelog.crimeid";
    private  static final String HAS_CRIME_CHANGED="com.zeelog.crimeid";


    public static CrimeFragment newInstance(UUID crimeId){

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimLab.get(getActivity()).getCrime(crimeId);

    }


    //--------CrimeFragment returing result----
    private  void resultReturn(){
        Intent data = new Intent();
        data.putExtra(HAS_CRIME_CHANGED,hasCrimeChanged);
        data.putExtra(ARG_CRIME_ID,mCrime.getId());
        getActivity().setResult(Activity.RESULT_OK,data);

    }


    public static boolean hasCrimeChanged(Intent result){
        return  result.getBooleanExtra(HAS_CRIME_CHANGED,false);
    }
    public static UUID getCrimeID(Intent result){
        return (UUID) result.getSerializableExtra(ARG_CRIME_ID);
    }


    //-----------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);

        mTitleField.setText(mCrime.getmTitle());
        mCheckBox.setChecked(mCrime.getSolved());
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
                mDateButton.setText(mCrime.getDate().toString());
                mDateButton.setEnabled(false);
                resultReturn();
                hasCrimeChanged = true;
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);
                resultReturn();
                hasCrimeChanged = true;
            }
        });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
       CrimLab.get(getActivity()).updateCrime(mCrime);
    }
}
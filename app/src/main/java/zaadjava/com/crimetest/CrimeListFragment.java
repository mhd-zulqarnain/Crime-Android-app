package zaadjava.com.crimetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by Zul Qarnain on 8/2/2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private static final int CRIME_RESULT = 1;

    //chalange variable--------

    private UUID mItemChangedId;
    private boolean mhasItemChanged = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }

    private void updateUI() {
        CrimLab crimLab = CrimLab.get(getActivity());
        List<Crime> crimes = crimLab.getmCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else if (mhasItemChanged) {
            int mItemChangedPosition = mAdapter.getCrimeIndex(mItemChangedId);
            mAdapter.notifyItemChanged(mItemChangedPosition);
            Toast.makeText(getActivity(), "back trace", Toast.LENGTH_LONG).show();

        }

    }

    //---------CrimeHolder---------------//
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckbox;
        private Crime mCrime;

        public CrimeHolder(View viewItem) {
            super(viewItem);
            mTitleTextView = (TextView) viewItem.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) viewItem.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckbox = (CheckBox) viewItem.findViewById(R.id.list_item_crime_check_box);
            mTitleTextView.setOnClickListener(this);
        }

        public void binder(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(crime.getmTitle());
            mDateTextView.setText(crime.getDate().toString());
            mSolvedCheckbox.setChecked(crime.getSolved());

        }

        @Override
        public void onClick(View view) {
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivityForResult(intent, CRIME_RESULT);
        }
    }


    //--------Intent returing result---------

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Log.d(TAG, "not Ok " + requestCode);
            return;
        }
        if (requestCode == CRIME_RESULT) {
            if (data != null) {
                Toast.makeText(getActivity(), "returning successfully problem" + CrimeActivity.hasCrimeChanged(data), Toast.LENGTH_LONG).show();
                mItemChangedId = CrimeActivity.getCrimeID(data);
                mhasItemChanged = CrimeActivity.hasCrimeChanged(data);
            }
        }
    }

    //----------Crimeholder---------//
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimeList) {

            this.mCrimes = crimeList;

        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CrimeHolder holder = new CrimeHolder(LayoutInflater.from(getActivity()).inflate(R.layout.single_crime_row_view, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            Log.d(TAG, "onClick:uuid " + crime.getId());
            holder.binder(crime);

        }

        @Override
        public int getItemCount() {

            return mCrimes.size();
        }

        //Chalange crime task
        public int getCrimeIndex(UUID crimeId) {
            for (int i = 0; i <= mCrimes.size(); i++) {
                Crime crime = mCrimes.get(i);
                if (crime.getId().equals(crimeId))
                    return i;
            }
            return -1;
        }
    }
}


package com.example.zulqarnain.crimeintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zul Qarnain on 8/16/2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Crime> mCrimes;
    private CrimeRecyclerAdpater crimeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_list_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    public void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        mCrimes=crimeLab.getCrimes();
        crimeAdapter = new CrimeRecyclerAdpater(mCrimes);
        recyclerView.setAdapter(crimeAdapter);
    }

    //-------------------------------------Adapter-----------------------------------------------

    class CrimeRecyclerAdpater extends RecyclerView.Adapter<CrimeRecyclerAdpater.CrimeViewHolder> {
        private List<Crime> crimes;

        CrimeRecyclerAdpater(List<Crime> crimes){
            this.crimes = crimes;
        }

        @Override
        public CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CrimeViewHolder crimeViewHolder = new CrimeViewHolder(LayoutInflater.from(getActivity()).
                    inflate(R.layout.single_crime_row_view, parent, false));
            return crimeViewHolder;
        }

        @Override
        public void onBindViewHolder(CrimeViewHolder holder, int position) {
            holder.bindView(crimes.get(position));
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        //--------------------------viewholder-------------------------------/////
        class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView crimeTitle;
            TextView crimeDate;
            CheckBox mCrimeSolved;
            Crime mCrime;
            public CrimeViewHolder(View itemView) {
                super(itemView);
                crimeTitle = (TextView)itemView.findViewById(R.id.list_item_crime_title_text_view) ;
                crimeDate = (TextView)itemView.findViewById(R.id.list_item_crime_date_text_view) ;
                mCrimeSolved = (CheckBox) itemView.findViewById(R.id.list_item_crime_check_box) ;
                mCrimeSolved.setOnClickListener(this);
            }

            public void bindView(Crime crime){
                crimeTitle.setText(crime.getCrimeTitle());
                crimeDate.setText(crime.getCrimeDate().toString());
                mCrimeSolved.setChecked(crime.ismCrimeSolved());
            }

            @Override
            public void onClick(View view) {
                Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getCrimeId());
                startActivity(intent);
            }
        }
    }

}

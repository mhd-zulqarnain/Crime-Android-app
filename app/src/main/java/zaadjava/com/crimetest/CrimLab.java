package zaadjava.com.crimetest;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Zul Qarnain on 8/2/2017.
 * singleton class because we had only one Lab for crimes according to scenerio
 */

public class CrimLab {
    Context context;
    public static CrimLab sCrimeLab;
     List<Crime> mCrimes;

    public static CrimLab get(Context context){
        if(sCrimeLab==null)
            sCrimeLab = new CrimLab(context);

        return sCrimeLab;
    }
    private CrimLab(Context context){
        mCrimes= new ArrayList<>();
        for(int i= 0 ; i<100;i++){
            Crime crime = new Crime();
            crime.setmTitle("Crime "+i);
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getmCrimes(){

        return mCrimes;
    }

    //to get crime by id
    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes)
            if(crime.getId().equals(id)){
                return crime;
            }
        return null;
    }
}

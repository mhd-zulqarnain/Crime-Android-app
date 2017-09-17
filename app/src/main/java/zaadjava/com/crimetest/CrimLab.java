package zaadjava.com.crimetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import zaadjava.com.crimetest.database.CrimeBaseHelper;
import zaadjava.com.crimetest.database.CrimeCursorWrapper;
import zaadjava.com.crimetest.database.CrimeDbSchema;
import zaadjava.com.crimetest.database.CrimeDbSchema.CrimeTable;

/**
 * Created by Zul Qarnain on 8/2/2017.
 * singleton class because we had only one Lab for crimes according to scenerio
 */

public class CrimLab {
    private Context mContext;
    public static CrimLab sCrimeLab;
    private SQLiteDatabase mDatabase;

    public static CrimLab get(Context context){

        if(sCrimeLab==null)
            sCrimeLab = new CrimLab(context);

        return sCrimeLab;
    }
    private CrimLab(Context context){
            mContext = context;
            mDatabase =new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getmCrimes(){
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor= queryCrime(null,null);
        try {
            while (cursor.moveToNext()){ //*may got error

            }
        }finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor = queryCrime(CrimeTable.Cols.UUID+"= ?",
                                                    new String[]{id.toString()}); //queryCrimbe require arry of where args and contion which table to be selected
        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToNext();
            return cursor.getCrime();

        }finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID,crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getmTitle());
        values.put(CrimeTable.Cols.DATE,crime.getDate().getDate());
        values.put(CrimeTable.Cols.SOLVED,crime.getSolved()?1:0);
        return values;
    }
    public void addCrime(Crime c){
        ContentValues values=getContentValues(c);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME,null,values);
    }
    public void updateCrime(Crime crime){
        String uuidString =crime.getId().toString();
        ContentValues contentValues = getContentValues(crime);
        mDatabase.update(CrimeDbSchema.CrimeTable.NAME,contentValues,
               CrimeTable.Cols.UUID+" =?",new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrime(String whereClause, String[] whereArgs){
        Cursor cursor  =mDatabase.query(CrimeDbSchema.CrimeTable.NAME ,
                    null,//slect all columns
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
                    );
            return new CrimeCursorWrapper(cursor);
    }
}

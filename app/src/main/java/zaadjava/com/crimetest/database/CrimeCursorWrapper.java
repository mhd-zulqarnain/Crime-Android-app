package zaadjava.com.crimetest.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.widget.CursorAdapter;

import java.util.Date;
import java.util.UUID;

import zaadjava.com.crimetest.Crime;
import zaadjava.com.crimetest.database.CrimeDbSchema.CrimeTable;


/**
 * Created by Zul Qarnain on 9/16/2017.
 */

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {

        super(cursor);
    }
    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title=getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date= getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved= getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        Crime crime =  new Crime(UUID.fromString(uuidString));
        crime.setmTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved!=0);
        return crime;
    }
}

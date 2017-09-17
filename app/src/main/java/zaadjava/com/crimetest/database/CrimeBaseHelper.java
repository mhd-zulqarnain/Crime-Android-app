package zaadjava.com.crimetest.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zul Qarnain on 9/15/2017.
 */
import zaadjava.com.crimetest.database.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="crimeBase.db";
    private static final int VERSION=1;
    public CrimeBaseHelper(Context context) {
        super(context, DB_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+CrimeDbSchema.CrimeTable.NAME+ "( "+
                    "_id integer primary key autoincrement, "+
                    CrimeTable.Cols.UUID+
                    CrimeTable.Cols.TITLE+
                    CrimeTable.Cols.DATE+
                    CrimeTable.Cols.SOLVED
                    +")"
                    );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

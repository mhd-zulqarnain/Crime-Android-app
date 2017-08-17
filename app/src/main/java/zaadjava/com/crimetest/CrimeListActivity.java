package zaadjava.com.crimetest;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Zul Qarnain on 8/2/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    private final String TAG="com.zeelogcm";
    @Override
    protected Fragment createFragement() {

        return new CrimeListFragment();
    }
}

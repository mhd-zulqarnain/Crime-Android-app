package zaadjava.com.crimetest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Zul Qarnain on 8/2/2017.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragement();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        android.support.v4.app.FragmentManager fm= getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment = createFragement();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }
}

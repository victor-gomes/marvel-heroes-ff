package vgomes.marvelheroes.ui.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by victorgomes on 01/07/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected boolean isResumed = false;

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }
}

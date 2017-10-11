package info.test.reminder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import info.test.reminder.R;
import info.test.reminder.app.AppConfig;
import info.test.reminder.fragment.HomeFragment;
import info.test.reminder.helper.FragmentHelper;
import info.test.reminder.helper.SoundHelper;

/**
 * Created by k.benamor on 26-09-2017.
 */

public class AlertActivity extends AppCompatActivity {

    //region Variables
    Button dismiss;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        listeners();
        SoundHelper.getInstance(AlertActivity.this).Start(2);
    }

    void initViews(){
        setContentView(R.layout.activity_alert);
        int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //| WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        getWindow().addFlags(flags);
        dismiss = (Button) findViewById(R.id.dismiss);
    }

    void listeners(){
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

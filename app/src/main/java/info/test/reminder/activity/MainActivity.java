package info.test.reminder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import info.test.reminder.R;
import info.test.reminder.app.AppConfig;
import info.test.reminder.fragment.FragmentDrawer;
import info.test.reminder.fragment.ReminderFragment;
import info.test.reminder.fragment.HomeFragment;
import info.test.reminder.fragment.ValidateFragment;
import info.test.reminder.helper.FragmentHelper;
import info.test.reminder.util.ReminderInterface;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, ReminderInterface {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    public void setDrawerEnabled(boolean state){
        drawerFragment.setDrawerEnabled(state);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name), tag = AppConfig.FRAGMENT_HOME;
        boolean addToBackStack = false;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                tag = AppConfig.FRAGMENT_HOME;
                addToBackStack = false;
                break;
            case 1:
                fragment = new ReminderFragment();
                title = getString(R.string.title_friends);
                tag = AppConfig.FRAGMENT_REMINDER;
                addToBackStack = true;
                break;
            case 2:
                fragment = new ValidateFragment();
                title = getString(R.string.title_messages);
                tag = AppConfig.FRAGMENT_VALIDATE;
                addToBackStack = true;
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentHelper.replaceFragment(MainActivity.this, fragment, addToBackStack, tag);
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    //region onBackPressed
    @Override
    public void onBackPressed() {
        String  fragmentTag = getSupportFragmentManager().findFragmentById(R.id.container_body).getTag();
        switch (fragmentTag){
            case AppConfig.FRAGMENT_HOME:
                super.onBackPressed();
                break;
            case AppConfig.FRAGMENT_REMINDER:
                ReminderFragment.getInstance().onBackPressedAction();
                break;
            case AppConfig.FRAGMENT_VALIDATE:
                ValidateFragment.getInstance().onBackPressedAction();
                break;
            default:
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
        }
    }
    //endregion

}
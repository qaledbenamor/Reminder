package info.test.reminder.helper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import info.test.reminder.R;
import info.test.reminder.activity.MainActivity;

/**
 * Created by k.benamor on 24-09-2017.
 */

public class FragmentHelper {

    //region Variables
    private static FragmentHelper   _instance;
    private static Context _context;
    //endregion

    //region Constructor
    public FragmentHelper(Context context) {
        this._context  = context;
    }

    public static FragmentHelper getInstance(Context context) {
        return _instance == null ? new FragmentHelper(context) : _instance;
    }
    //endregion

    //region Replace Fragment
    public static void replaceFragment(Context context, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.container_body, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        } else {
            /*((MainActivity) context).getSupportFragmentManager().popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
            if (((MainActivity) context).getSupportFragmentManager().getBackStackEntryCount() > 1) {
                ((MainActivity) context).getSupportFragmentManager().popBackStackImmediate();
            }
        }

        transaction.commit();
        ((MainActivity) context).getSupportFragmentManager().executePendingTransactions();
    }
    //endregion

    //region Add Fragment
    public static void addFragment(Context context, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager()
                .beginTransaction();

        transaction.add(R.id.container_body, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        } else {
            ((MainActivity) context).getSupportFragmentManager().popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        transaction.commit();
        ((MainActivity) context).getSupportFragmentManager().executePendingTransactions();
    }
    //endregion

    //region Pop Fragment
    public boolean popFragment(Context context) {
        boolean isPop = false;

        if (((MainActivity) context).getSupportFragmentManager().getBackStackEntryCount() > 1) {
            isPop = true;
            ((MainActivity) context).getSupportFragmentManager().popBackStackImmediate();
        }

        return isPop;
    }
    //endregion

}

package info.test.reminder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import info.test.reminder.R;
import info.test.reminder.activity.MainActivity;
import info.test.reminder.app.AppConfig;
import info.test.reminder.helper.FragmentHelper;
import info.test.reminder.util.ReminderInterface;

/**
 * Created by Ravi on 29/07/15.
 */
public class ValidateFragment extends Fragment {

    //region Variables
    static ValidateFragment mCurrentFragment;
    ImageView imageView;
    static Snackbar snackbar;
    static CoordinatorLayout coordinatorLayout;
    //endregion

    public ValidateFragment() {
        // Required empty public constructor
    }

    //region Fragment Instance
    public static ValidateFragment getInstance() {
        return mCurrentFragment == null ? new ValidateFragment() : mCurrentFragment;
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validate, container, false);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ReminderInterface) getActivity()).setDrawerEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        mCurrentFragment = (ValidateFragment) getActivity().getSupportFragmentManager().findFragmentByTag(AppConfig.FRAGMENT_VALIDATE);
        coordinatorLayout    = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        imageView = (ImageView) getActivity().findViewById(R.id.closeReminder);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHelper.replaceFragment(getContext(), new HomeFragment() , false, AppConfig.FRAGMENT_HOME);
            }
        });

    }

    //region Show Snack Bar
    void showSnackBar(String text, int duration, int background){
        snackbar = Snackbar
                .make(coordinatorLayout, text, duration);
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorPrimary));
        snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), background));
        snackbar.show();
    }
    //endregion

    //region on Back Pressed Action
    public void onBackPressedAction(){
        FragmentHelper.replaceFragment(getContext(), new HomeFragment() , false, AppConfig.FRAGMENT_HOME);
    }
    //endregion
}

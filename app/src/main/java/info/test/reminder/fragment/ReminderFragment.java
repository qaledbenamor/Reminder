package info.test.reminder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import info.test.reminder.R;
import info.test.reminder.activity.MainActivity;
import info.test.reminder.alarm.AlarmsManager;
import info.test.reminder.app.AppConfig;
import info.test.reminder.helper.FragmentHelper;
import info.test.reminder.model.Reminder;
import info.test.reminder.util.ReminderInterface;

/**
 * Created by Ravi on 29/07/15.
 */
public class ReminderFragment extends Fragment {

    //region Variables
    static ReminderFragment mCurrentFragment;
    FloatingActionButton fab;
    static Snackbar snackbar;
    static CoordinatorLayout coordinatorLayout;
    TextView title, subject, message;
    TextView day1, day2, day3, day4, day5, day6, day7;
    Reminder reminder;
    //endregion

    public ReminderFragment() {
        // Required empty public constructor
    }

    //region Fragment Instance
    public static ReminderFragment getInstance() {
        return mCurrentFragment == null ? new ReminderFragment() : mCurrentFragment;
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reminder, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initValues();
        listeners();
    }

    //region Initialize
    public void initViews(){
        ((ReminderInterface) getActivity()).setDrawerEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.new_reminder));
        mCurrentFragment = (ReminderFragment) getActivity().getSupportFragmentManager().findFragmentByTag(AppConfig.FRAGMENT_REMINDER);
        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        title = (TextView) getActivity().findViewById(R.id.title);
        subject = (TextView) getActivity().findViewById(R.id.subject);
        message = (TextView) getActivity().findViewById(R.id.message);
        day1 = (TextView) getActivity().findViewById(R.id.day1);
        day2 = (TextView) getActivity().findViewById(R.id.day2);
        day3 = (TextView) getActivity().findViewById(R.id.day3);
        day4 = (TextView) getActivity().findViewById(R.id.day4);
        day5 = (TextView) getActivity().findViewById(R.id.day5);
        day6 = (TextView) getActivity().findViewById(R.id.day6);
        day7 = (TextView) getActivity().findViewById(R.id.day7);
    }

    public void initValues(){

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            reminder = bundle.getParcelable(AppConfig.REMINDER_ITEM);
            title.setText(reminder.getTitle());
            subject.setText(reminder.getSubject());
            message.setText(reminder.getMessage());
        } else {
            reminder = new Reminder();
        }
    }

    public void listeners(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmsManager.scheduleNextAlarm(getContext());
                FragmentHelper.replaceFragment(getContext(), new ValidateFragment(), false, AppConfig.FRAGMENT_VALIDATE);
            }
        });

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day1.getText().toString().compareTo("S") == 0){
                    day1.setText("s");
                    day1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day1.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day1.setText("S");
                    day1.setBackground(null);
                    day1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day2.getText().toString().compareTo("M") == 0){
                    day2.setText("m");
                    day2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day2.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day2.setText("M");
                    day2.setBackground(null);
                    day2.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });

        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day3.getText().toString().compareTo("T") == 0){
                    day3.setText("t");
                    day3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day3.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day3.setText("T");
                    day3.setBackground(null);
                    day3.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });

        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day4.getText().toString().compareTo("W") == 0){
                    day4.setText("w");
                    day4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day4.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day4.setText("W");
                    day4.setBackground(null);
                    day4.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });

        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day5.getText().toString().compareTo("T") == 0){
                    day5.setText("t");
                    day5.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day5.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day5.setText("T");
                    day5.setBackground(null);
                    day5.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });

        day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day6.getText().toString().compareTo("F") == 0){
                    day6.setText("f");
                    day6.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day6.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day6.setText("F");
                    day6.setBackground(null);
                    day6.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });

        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (day7.getText().toString().compareTo("S") == 0){
                    day7.setText("s");
                    day7.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_circle));
                    day7.setTextColor(ContextCompat.getColor(getContext(), R.color.textColorPrimary));
                } else {
                    day7.setText("S");
                    day7.setBackground(null);
                    day7.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                }

            }
        });
    }
    //endregion

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

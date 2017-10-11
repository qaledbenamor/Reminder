package info.test.reminder.fragment;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.test.reminder.R;
import info.test.reminder.activity.MainActivity;
import info.test.reminder.adapter.RemindersAdapter;
import info.test.reminder.app.AppConfig;
import info.test.reminder.helper.DividerItemDecoration;
import info.test.reminder.helper.FragmentHelper;
import info.test.reminder.model.Reminder;
import info.test.reminder.util.ReminderInterface;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RemindersAdapter.MessageAdapterListener {

    //region Variables
    static HomeFragment mCurrentFragment;
    List<Reminder> reminders = new ArrayList<>();
    RecyclerView recyclerView;
    RemindersAdapter mAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ActionModeCallback actionModeCallback;
    ActionMode actionMode;
    AppCompatActivity activity;
    FloatingActionButton fab;
    static Snackbar snackbar;
    static CoordinatorLayout coordinatorLayout;
    //endregion

    public HomeFragment() {
        // Required empty public constructor
    }

    //region Fragment Instance
    public static HomeFragment getInstance() {
        return mCurrentFragment == null ? new HomeFragment() : mCurrentFragment;
    }
    //endregion

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ReminderInterface) getActivity()).setDrawerEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.home));
        activity=(AppCompatActivity)getActivity();
        mCurrentFragment = (HomeFragment) getActivity().getSupportFragmentManager().findFragmentByTag(AppConfig.FRAGMENT_HOME);
        coordinatorLayout    = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new RemindersAdapter(getContext(), reminders, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        actionModeCallback = new ActionModeCallback();

        // show loader and fetch reminders
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getReminderList();
                    }
                }
        );

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actionMode != null) {
                    actionMode.finish();
                }
                clearSelection();
                FragmentHelper.replaceFragment(getContext(), new ReminderFragment(), false, AppConfig.FRAGMENT_REMINDER);
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }

    private void getReminderList(){
        reminders.clear();

        swipeRefreshLayout.setRefreshing(true);

        for (int i = 1; i<11; i++){

            Reminder reminder = new Reminder();
            reminder.setId(i);
            reminder.setTitle("Model "+ i);
            reminder.setSubject("Get your money now !");
            reminder.setTimestamp("10.25 AM");
            reminder.setMessage("this just testing message from me");
            reminder.setColor(getRandomMaterialColor("400"));
            reminders.add(reminder);
        }

        mAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * chooses a random color from array.xml
     */
    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", getActivity().getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            /*getFragmentManager().beginTransaction().replace(android.R.id.content,
                    new SettingsFragment()).commit();*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        // swipe refresh is performed, fetch the reminders again
        //getInbox();
        getReminderList();
    }



    @Override
    public void onIconClicked(int position) {
        if (actionMode == null) {
            actionMode = activity.startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    @Override
    public void onMessageRowClicked(int position) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
        if (mAdapter.getSelectedItemCount() > 0) {
            enableActionMode(position);
        } else {
            ReminderFragment reminderFragment = new ReminderFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConfig.REMINDER_ITEM, reminders.get(position));
            reminderFragment.setArguments(bundle);
            FragmentHelper.replaceFragment(getContext(), reminderFragment, false, AppConfig.FRAGMENT_REMINDER);
        }
    }

    @Override
    public void onRowLongClicked(int position) {
        // long press is performed, enable action mode
        enableActionMode(position);
    }



    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = activity.startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }


    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable swipe refresh if action mode is enabled
            swipeRefreshLayout.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected reminders
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            clearSelection();
        }
    }

    public void clearSelection(){
        mAdapter.clearSelections();
        swipeRefreshLayout.setEnabled(true);
        actionMode = null;
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.resetAnimationIndex();
                // mAdapter.notifyDataSetChanged();
            }
        });
    }

    // deleting the reminders from recycler view
    private void deleteMessages() {
        mAdapter.resetAnimationIndex();
        List<Integer> selectedItemPositions =
                mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            mAdapter.removeData(selectedItemPositions.get(i));
        }
        mAdapter.notifyDataSetChanged();
        showSnackBar("item deleted", Snackbar.LENGTH_LONG, R.color.colorPrimary);
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

}

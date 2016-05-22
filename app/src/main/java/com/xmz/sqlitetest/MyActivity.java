package com.xmz.sqlitetest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.xmz.sqlitetest.data.source.TasksRepository;
import com.xmz.sqlitetest.data.source.local.TasksLocalDataSource;
import com.xmz.sqlitetest.edit.EditFragment;
import com.xmz.sqlitetest.edit.EditPresenter;
import com.xmz.sqlitetest.tasks.TasksFragment;
import com.xmz.sqlitetest.tasks.TasksPresenter;
import com.xmz.sqlitetest.util.ActivityUtils;

public class MyActivity extends AppCompatActivity implements TasksFragment.Callbacks, EditFragment.Callbacks {

    private DrawerLayout mDrawerLayout;

    private TasksPresenter mTasksPresenter;

    private EditPresenter mEditPresenter;

    public boolean addOrEditTask = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            tasksFragment = TasksFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        mTasksPresenter = new TasksPresenter(
                TasksRepository.getInstance(TasksLocalDataSource.getInstance(getApplicationContext())), tasksFragment);

    }


    @Override
    public void addTask() {
        EditFragment editFragment =
                (EditFragment) getSupportFragmentManager().findFragmentById(R.id.contentEditFrame);
        if (editFragment == null) {
            editFragment = EditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), editFragment, R.id.contentEditFrame);
        } else {
//            getFragmentManager().beginTransaction().replace(R.id.contentEditFrame, editFragment).commit();
        }

    }

    @Override
    public void editTask(int id) {

    }

    @Override
    public void showTasksList() {
        mTasksPresenter.loadTasks(false);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_navigation_menu_item:
                        Log.i("hi","list_navigation_menu_item");
                        break;
                    case R.id.statistics_navigation_menu_item:
                        Log.i("hi","content");
                        break;
                    default:
                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

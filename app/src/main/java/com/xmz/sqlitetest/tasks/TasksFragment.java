package com.xmz.sqlitetest.tasks;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xmz.sqlitetest.MyActivity;
import com.xmz.sqlitetest.R;
import com.xmz.sqlitetest.ScrollChildSwipeRefreshLayout;
import com.xmz.sqlitetest.data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xmz on 2016/5/19.
 */
public class TasksFragment extends Fragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;

    private TasksAdapter mListAdapter;

    private LinearLayout mTasksView;

    private TextView mTasksLabel;

    private Callbacks mCallbacks;

    public TasksFragment() {

    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    public interface Callbacks {

        void addTask();

        void editTask(int id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_frag, container, false);

        ListView listView = (ListView) root.findViewById(R.id.tasks_list);
        listView.setAdapter(mListAdapter);

        mTasksLabel = (TextView) root.findViewById(R.id.title_label);
        mTasksView = (LinearLayout) root.findViewById(R.id.tasksLL);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mPresenter.addNewTask();
            }
        });

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(listView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTasks(false);
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void showAddTask() {
        ((MyActivity)getActivity()).addOrEditTask = true;
        mCallbacks.addTask();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);
    }

    @Override
    public void showEditTask(String taskId) {

    }

    private static class TasksAdapter extends BaseAdapter {

        private List<Task> mTasks;
        private TaskItemListener mItemListener;

        public TasksAdapter(List<Task> tasks, TaskItemListener itemListener) {
            setList(tasks);
            mItemListener = itemListener;
        }

        public void replaceData(List<Task> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }

        public void setList(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Task getItem(int position) {
            return mTasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.task_item, parent, false);
            }

            final Task task = getItem(position);

            TextView taskId = (TextView) rowView.findViewById(R.id.task_id);
            taskId.setText(task.getId());

            TextView taskDescription = (TextView) rowView.findViewById(R.id.task_description);
            taskDescription.setText(task.getDescription());

            return rowView;
        }
    }

    public interface TaskItemListener {

        void onTaskClick(Task clickedTask);

    }

    TaskItemListener mItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            Log.i("log", "item click");
        }
    };

    @Override
    public void showDeleteTask() {

    }
}

package com.xmz.sqlitetest.tasks;

import com.xmz.sqlitetest.data.Task;
import com.xmz.sqlitetest.data.source.TasksDataSource;
import com.xmz.sqlitetest.data.source.TasksRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xmz on 2016/5/20.
 */
public class TasksPresenter implements TasksContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final TasksContract.View mTasksView;

    private boolean mFirstLoad = true;

    public TasksPresenter(TasksRepository tasksRepository, TasksContract.View tasksView) {
        mTasksRepository = tasksRepository;
        mTasksView = tasksView;
        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
        mFirstLoad = false;
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        if (forceUpdate) {
            mTasksRepository.refreshTasks();
        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> tasksToShow = new ArrayList<Task>();

                for (Task task : tasks) {
                    tasksToShow.add(task);
                }

                processTasks(tasksToShow);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void processTasks(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            mTasksView.showTasks(tasks);
        }
    }

    @Override
    public void addNewTask() {
        mTasksView.showAddTask();
    }

    @Override
    public void editTask(Task requestTask) {
        mTasksView.showEditTask(requestTask.getId());
    }

    @Override
    public void deleteTask(Task task) {
        mTasksView.showDeleteTask();
    }
}

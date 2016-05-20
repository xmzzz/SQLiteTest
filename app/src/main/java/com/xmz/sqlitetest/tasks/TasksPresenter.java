package com.xmz.sqlitetest.tasks;

import com.xmz.sqlitetest.data.source.TasksRepository;

/**
 * Created by xmz on 2016/5/20.
 */
public class TasksPresenter implements TasksContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final TasksContract.View mTasksView;

    public TasksPresenter(TasksRepository tasksRepository, TasksContract.View tasksView) {
        mTasksRepository = tasksRepository;
        mTasksView = tasksView;
        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
    }
}

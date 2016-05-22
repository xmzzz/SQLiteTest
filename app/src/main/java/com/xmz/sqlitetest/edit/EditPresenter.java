package com.xmz.sqlitetest.edit;

import com.xmz.sqlitetest.data.source.TasksRepository;
import com.xmz.sqlitetest.tasks.TasksContract;

/**
 * Created by xmz on 2016/5/20.
 */
public class EditPresenter extends EditContract.Presenter {

    private final TasksRepository mTasksRepository;

    private final EditContract.View mEditView;

    public EditPresenter(TasksRepository tasksRepository, EditContract.View editView) {
        mTasksRepository = tasksRepository;
        mEditView = editView;
        mEditView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void updateTask(String id, String description) {

    }

    @Override
    public void createTask(String id, String description) {

    }

    @Override
    public void populateTask() {

    }
}

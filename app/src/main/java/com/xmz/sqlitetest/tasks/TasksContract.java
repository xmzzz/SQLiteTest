package com.xmz.sqlitetest.tasks;

import com.xmz.sqlitetest.BasePresenter;
import com.xmz.sqlitetest.BaseView;
import com.xmz.sqlitetest.data.Task;

import java.util.List;

/**
 * Created by xmz on 2016/5/19.
 */
public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void showTasks(List<Task> tasks);

        void showEditTask();

    }

    interface Presenter extends BasePresenter {

//        void createTask(String id, String description);

        void updateTask(String id, String description);

        void populateTask();

        void editTask(Task task);
    }
}

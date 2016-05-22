package com.xmz.sqlitetest.data.source;

import com.xmz.sqlitetest.data.Task;

import java.util.List;

/**
 * Created by xmz on 2016/5/19.
 */
public interface TasksDataSource {

    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }

    void getTasks(LoadTasksCallback callback);

    void getTask(String taskId, GetTaskCallback callback);

    void saveTask(Task task);

    void refreshTasks();


}

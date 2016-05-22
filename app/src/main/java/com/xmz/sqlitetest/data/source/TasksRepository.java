package com.xmz.sqlitetest.data.source;

import com.xmz.sqlitetest.data.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xmz on 2016/5/19.
 */
public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksLocalDataSource;

    Map<String, Task> mCachedTasks;

    boolean mCacheIsDirty = false;

    private TasksRepository(TasksDataSource tasksLocalDataSource) {
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    public static TasksRepository getInstance(TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(final LoadTasksCallback callback) {
        if (mCachedTasks !=null && !mCacheIsDirty) {
            callback.onTasksLoaded(new ArrayList<Task>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty) {
            ;
        } else {
            mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(List<Task> tasks) {
                    refreshCache(tasks);
                    callback.onTasksLoaded(new ArrayList<Task>(mCachedTasks.values()));
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
    }

    @Override
    public void getTask(String taskId, final GetTaskCallback callback) {
        Task cachedTask = getTaskWithId(taskId);

        if (cachedTask != null) {
            callback.onTaskLoaded(cachedTask);
            return;
        }

        mTasksLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void saveTask(Task task) {
        mTasksLocalDataSource.saveTask(task);

        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    private void refreshCache(List<Task> tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (Task task: tasks) {
            mCachedTasks.put(task.getId(), task);
        }
        mCacheIsDirty = false;
    }

    private Task getTaskWithId(String id) {
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(id);
        }
    }

    @Override
    public void refreshTasks() {
        mCacheIsDirty = true;
    }
}

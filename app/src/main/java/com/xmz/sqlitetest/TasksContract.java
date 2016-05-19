package com.xmz.sqlitetest;

/**
 * Created by xmz on 2016/5/19.
 */
public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void showTasksList();

        void setId(String id);

        void setDescription(String description);
    }

    interface Presenter extends BasePresenter {

        void createTask(String id, String description);

        void updateTask(String id, String description);

        void populateTask();
    }
}

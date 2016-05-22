package com.xmz.sqlitetest.edit;

import com.xmz.sqlitetest.BasePresenter;
import com.xmz.sqlitetest.BaseView;

/**
 * Created by xmz on 2016/5/20.
 */
public interface EditContract {

    interface View extends BaseView<Presenter> {

        void showTasksList();

        void setId(String id);  //  编辑模式打开时设置UI显示

        void setDescription(String description);


    }

    interface Presenter extends BasePresenter {

        void updateTask(String id, String description);  // 更新某一个Task

        void createTask(String id, String description);  // 新建一个Task，并存储

        void populateTask();

    }
}

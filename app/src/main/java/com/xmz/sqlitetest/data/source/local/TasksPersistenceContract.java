package com.xmz.sqlitetest.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by xmz on 2016/5/19.
 */
public final class TasksPersistenceContract {

    public TasksPersistenceContract() {
    }

    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}

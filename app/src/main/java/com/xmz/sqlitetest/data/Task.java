package com.xmz.sqlitetest.data;

/**
 * Created by xmz on 2016/5/19.
 */
public final class Task {

    private final String mId;

    private final String mDescription;

    public Task(String id, String description) {
        mId = id;
        mDescription = description;
    }

    public String getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

}

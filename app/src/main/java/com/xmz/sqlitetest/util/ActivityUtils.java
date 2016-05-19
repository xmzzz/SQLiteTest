package com.xmz.sqlitetest.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by xmz on 2016/5/19.
 */
public class ActivityUtils {

    public static void addFragmentToActivity (FragmentManager fragmentManager,
                                              Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
        transaction.commit();
    }
}

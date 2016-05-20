package com.xmz.sqlitetest.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xmz.sqlitetest.R;

/**
 * Created by xmz on 2016/5/20.
 */
public class EditFragment extends Fragment implements EditContract.View {

    private EditContract.Presenter mPresenter;

    private LinearLayout mTaskView;

    private EditText mTaskId;

    private EditText mTaskDescription;

    private Button mTaskSave;

    private Button mTaskUpdate;

    public EditFragment() {}

    public static EditFragment newInstance() {
        return new EditFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.edit_frag, container, false);

        mTaskId = (EditText) root.findViewById(R.id.edit_id);
        mTaskDescription = (EditText) root.findViewById(R.id.edit_description);

        Button
    }
}

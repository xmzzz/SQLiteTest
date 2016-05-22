package com.xmz.sqlitetest.edit;

import android.app.Activity;
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

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";

    private EditContract.Presenter mPresenter;

    private LinearLayout mTaskView;

    private EditText mTaskId;

    private EditText mTaskDescription;

    private Button mTaskSave;

    private Button mTaskUpdate;

    private String mEditTaskId;

    private Callbacks mCallbacks;

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
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public interface Callbacks {

        void showTasksList();
    }
    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.edit_frag, container, false);

        mTaskId = (EditText) root.findViewById(R.id.edit_id);
        mTaskDescription = (EditText) root.findViewById(R.id.edit_description);

        mTaskSave = (Button) root.findViewById(R.id.edit_save);
        mTaskUpdate = (Button) root.findViewById(R.id.edit_update);

        setHasOptionsMenu(true);
        setRetainInstance(true);
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTaskIdIfAny();

        mTaskSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewTask()) {
                    mPresenter.createTask(
                            mTaskId.getText().toString(),
                            mTaskDescription.getText().toString());
                } else {
                    mPresenter.updateTask(
                            mTaskId.getText().toString(),
                            mTaskDescription.getText().toString());
                }
            }
        });

        showTasksList();

    }

    @Override
    public void showTasksList() {
        mCallbacks.showTasksList();
    }

    private void setTaskIdIfAny() {
        if (getArguments() != null && getArguments().containsKey(ARGUMENT_EDIT_TASK_ID)) {
            mEditTaskId = getArguments().getString(ARGUMENT_EDIT_TASK_ID);
        }
    }

    private boolean isNewTask() {
        return (mEditTaskId == null);
    }

    @Override
    public void setId(String id) {
        mTaskId.setText(id);
    }

    @Override
    public void setDescription(String description) {
        mTaskDescription.setText(description);
    }
}

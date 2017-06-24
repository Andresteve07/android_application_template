package com.techyourchance.template.screens.common.dialogs;

import android.support.annotation.UiThread;
import android.support.v4.app.DialogFragment;

import com.techyourchance.template.MyApplication;
import com.techyourchance.template.dependencyinjection.controller.ControllerComponent;
import com.techyourchance.template.dependencyinjection.controller.ControllerModule;
import com.techyourchance.template.dependencyinjection.controller.ViewMvcModule;

/**
 * Base class for all dialogs
 */
public abstract class BaseDialog extends DialogFragment {

    private ControllerComponent mControllerComponent;

    @UiThread
    protected ControllerComponent getControllerComponent() {
        if (mControllerComponent == null) {
            mControllerComponent = ((MyApplication)getActivity().getApplication())
                    .getApplicationComponent().newControllerComponent(
                            new ControllerModule(getActivity(), getFragmentManager()),
                            new ViewMvcModule()
                    );
        }
        return mControllerComponent;
    }

    /**
     * Get this dialog's ID that was supplied with a call to
     * {@link DialogsManager#showRetainedDialogWithId(DialogFragment, String)}
     * @return dialog's ID, or null if none was set
     */
    protected String getDialogId() {
        if (getArguments() == null) {
            return null;
        } else {
            return getArguments().getString(DialogsManager.ARGUMENT_DIALOG_ID);
        }
    }

}

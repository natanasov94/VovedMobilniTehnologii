package com.example.vmt.listeners.buttonlistener;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

/**
 * Listener that can be attached to a view and transition from one activity to another.
 *
 * Can also be extended to remove a separate implementation for all remaining listeners
 * */
public class TransitionButtonOnClickListener implements View.OnClickListener {

    private Activity currentActivity;
    private Class activityToTransitionTo;

    public TransitionButtonOnClickListener(Activity currentActivity, Class activityToTransitionTo) {
        this.currentActivity = currentActivity;
        this.activityToTransitionTo = activityToTransitionTo;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(currentActivity, activityToTransitionTo);
        // Transition to different activity
        currentActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(currentActivity).toBundle());
    }
}

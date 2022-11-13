package com.example.vmt.listeners;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

import com.example.vmt.MainActivity;

public class TransitionButtonOnClickListener implements View.OnClickListener {

    private Activity currentActivity;
    private Class activityToTransitionTo;

    public TransitionButtonOnClickListener(Activity currentActivity, Class activityToTransitionTo
    ) {
        this.currentActivity = currentActivity;
        this.activityToTransitionTo = activityToTransitionTo;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(currentActivity, activityToTransitionTo);
        currentActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(currentActivity).toBundle());
    }
}

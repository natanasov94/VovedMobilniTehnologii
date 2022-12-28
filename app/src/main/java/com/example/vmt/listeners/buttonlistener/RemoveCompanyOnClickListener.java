package com.example.vmt.listeners.buttonlistener;

import android.app.Activity;
import android.view.View;

import com.example.vmt.company.dto.CompanyCategory;

/**
 * Listener to remove a company from a category
 * */
public class RemoveCompanyOnClickListener extends TransitionButtonOnClickListener {

    private final CompanyCategory companyCategory;
    private final int index;

    public RemoveCompanyOnClickListener(Activity currentActivity, Class activityToTransitionTo, CompanyCategory companyCategory, int index) {
        super(currentActivity, activityToTransitionTo);
        this.companyCategory = companyCategory;
        this.index = index;
    }
    @Override
    public void onClick(View view) {
        // Remove company from category
        companyCategory.getCompanies().remove(index);
        // Transition to MainActivity
        super.onClick(view);
    }
}

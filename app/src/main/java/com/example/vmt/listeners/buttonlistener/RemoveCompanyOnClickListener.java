package com.example.vmt.listeners.buttonlistener;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.vmt.company.dto.CompanyCategory;

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
        Log.i("Companies hashcode (ou)", String.valueOf(companyCategory.getCompanies().hashCode()));
        Log.i("Before", companyCategory.getCompanies().toString());
        companyCategory.getCompanies().remove(index);
        Log.i("After", companyCategory.getCompanies().toString());
        super.onClick(view);
    }
}

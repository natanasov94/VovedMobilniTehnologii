package com.example.vmt.listeners.buttonlistener;

import static com.example.vmt.MainActivity.COMPANIES;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.vmt.company.dto.Company;
import com.example.vmt.company.dto.CompanyCategory;

public class AddCompanyOnClickListener extends TransitionButtonOnClickListener {

    TextView companyNameTextView;
    TextView companyPhoneTextView;
    TextView companyAddressTextView;
    TextView companyLogoPathTextView;
    TextView companySiteTextView;
    Spinner categorySpinner;

    public AddCompanyOnClickListener(
            Activity currentActivity,
            Class activityToTransitionTo,
            TextView companyNameTextView,
            TextView companyPhoneTextView,
            TextView companyAddressTextView,
            TextView companyLogoPathTextView,
            TextView companySiteTextView,
            Spinner categorySpinner
    ) {
        super(currentActivity, activityToTransitionTo);
        this.companyAddressTextView = companyAddressTextView;
        this.companyNameTextView = companyNameTextView;
        this.companyLogoPathTextView = companyLogoPathTextView;
        this.companyPhoneTextView = companyPhoneTextView;
        this.companySiteTextView = companySiteTextView;
        this.categorySpinner = categorySpinner;
    }

    @Override
    public void onClick(View view) {
        Company company = new Company(
                companyLogoPathTextView.getText().toString(),
                companyNameTextView.getText().toString(),
                companyAddressTextView.getText().toString(),
                companyPhoneTextView.getText().toString(),
                companySiteTextView.getText().toString()
        );
        String selectedCategoryName = categorySpinner.getSelectedItem().toString();
        CompanyCategory selectedCategory = COMPANIES.getCompanyCategory(selectedCategoryName);
        selectedCategory.getCompanies().add(company);
        super.onClick(view);
    }
}

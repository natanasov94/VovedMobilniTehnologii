package com.example.vmt.listeners.buttonlistener;

import static com.example.vmt.MainActivity.COMPANIES;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vmt.company.dto.Company;
import com.example.vmt.company.dto.CompanyCategory;

public class EditCompanyOnClickListener extends TransitionButtonOnClickListener {
    private TextView companyNameTextView;
    private TextView companyPhoneTextView;
    private TextView companyAddressTextView;
    private TextView companyLogoPathTextView;
    private TextView companySiteTextView;
    private Spinner categorySpinner;
    private CompanyCategory currentCategory;
    private int index;

    public EditCompanyOnClickListener(
            Activity currentActivity,
            Class activityToTransitionTo,
            TextView companyNameTextView,
            TextView companyPhoneTextView,
            TextView companyAddressTextView,
            TextView companyLogoPathTextView,
            TextView companySiteTextView,
            Spinner categorySpinner,
            CompanyCategory currentCategory,
            int index
    ) {
        super(currentActivity, activityToTransitionTo);
        this.companyAddressTextView = companyAddressTextView;
        this.companyNameTextView = companyNameTextView;
        this.companyLogoPathTextView = companyLogoPathTextView;
        this.companyPhoneTextView = companyPhoneTextView;
        this.companySiteTextView = companySiteTextView;
        this.categorySpinner = categorySpinner;
        this.currentCategory = currentCategory;
        this.index = index;
    }

    @Override
    public void onClick(View view) {
        // Get company and set new values
        Company company = currentCategory.getCompanies().get(index);
        company.setLogoPath(companyLogoPathTextView.getText().toString());
        company.setName(companyNameTextView.getText().toString());
        company.setAddress(companyAddressTextView.getText().toString());
        company.setPhone(companyPhoneTextView.getText().toString());
        company.setSite(companySiteTextView.getText().toString());
        // Check if selected category is
        String selectedCategoryName = categorySpinner.getSelectedItem().toString();
        CompanyCategory selectedCategory = COMPANIES.getCompanyCategory(selectedCategoryName);
        if (currentCategory != selectedCategory) {
            currentCategory.getCompanies().remove(index);
            selectedCategory.getCompanies().add(company);
        }
        super.onClick(view);
    }
}

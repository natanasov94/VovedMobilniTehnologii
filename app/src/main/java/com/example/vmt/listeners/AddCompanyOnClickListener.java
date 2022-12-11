package com.example.vmt.listeners;

import static com.example.vmt.MainActivity.COMPANIES;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.vmt.company.dto.Company;
import com.example.vmt.company.dto.CompanyCategory;

public class AddCompanyOnClickListener implements View.OnClickListener{

    TextView companyNameTextView;
    TextView companyPhoneTextView;
    TextView companyAddressTextView;
    TextView companyLogoPathTextView;
    TextView companySiteTextView;
    Spinner categorySpinner;

    public AddCompanyOnClickListener(
            TextView companyNameTextView,
            TextView companyPhoneTextView,
            TextView companyAddressTextView,
            TextView companyLogoPathTextView,
            TextView companySiteTextView,
            Spinner categorySpinner
    ) {
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
        String selectedCompany = categorySpinner.getSelectedItem().toString();
        CompanyCategory companyCategory = COMPANIES.getCompanyCategory(selectedCompany);
        companyCategory.getCompanies().add(company);
    }
}

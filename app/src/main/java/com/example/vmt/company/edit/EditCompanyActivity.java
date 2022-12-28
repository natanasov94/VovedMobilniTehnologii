package com.example.vmt.company.edit;

import static com.example.vmt.MainActivity.COMPANIES;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.vmt.MainActivity;
import com.example.vmt.R;
import com.example.vmt.company.dto.Company;
import com.example.vmt.company.dto.CompanyCategory;
import com.example.vmt.listeners.buttonlistener.EditCompanyOnClickListener;
import com.example.vmt.listeners.buttonlistener.RemoveCompanyOnClickListener;
import com.example.vmt.listeners.buttonlistener.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Activity used to edit existing companies
 * */
public class EditCompanyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_company);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout(width, height);

        // Getting category and company info/object
        Intent intent = getIntent();
        String categoryName = intent.getExtras().getString("category");
        CompanyCategory companyCategory = COMPANIES.getCompanyCategory(categoryName);
        int index = intent.getExtras().getInt("index");
        Company company = companyCategory.getCompanies().get(index);

        // Getting views and setting categories dropdown
        TextView companyNameTextView = findViewById(R.id.editCompanyNameTextInput);
        TextView companyPhoneTextView = findViewById(R.id.editCompanyPhoneTextInput);
        TextView companyAddressTextView = findViewById(R.id.editCompanyAddressTextInput);
        TextView companyLogoPathTextView = findViewById(R.id.editCompanyLogoPath);
        TextView companySiteTextView = findViewById(R.id.editCompanySiteTextInput);

        Spinner categorySpinner = findViewById(R.id.editCategoryDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(COMPANIES.getCategoryIndex(categoryName));

        // Set values from company
        companyNameTextView.setText(company.getName());
        companyAddressTextView.setText(company.getAddress());
        companyLogoPathTextView.setText(company.getLogoPath());
        companySiteTextView.setText(company.getSite());
        companyPhoneTextView.setText(company.getPhone());

        // Set listeners for buttons
        FloatingActionButton deleteButton = findViewById(R.id.editDeleteCompany);
        FloatingActionButton backButton = findViewById(R.id.editCompanyGoBack);
        FloatingActionButton confirmButton = findViewById(R.id.editCompanyConfirm);

        deleteButton.setOnClickListener(new RemoveCompanyOnClickListener(
                this,
                MainActivity.class,
                companyCategory,
                index
        ));
        /*
        * The views are passed to the listener instead of the text
        * values since the values will not have been set when the
        * listener is created, so it needs to extract the latest
        * text value from them
        * */
        confirmButton.setOnClickListener(new EditCompanyOnClickListener(
                this,
                MainActivity.class,
                companyNameTextView,
                companyPhoneTextView,
                companyAddressTextView,
                companyLogoPathTextView,
                companySiteTextView,
                categorySpinner,
                companyCategory,
                index));
        backButton.setOnClickListener(new TransitionButtonOnClickListener(this, MainActivity.class));
    }
}

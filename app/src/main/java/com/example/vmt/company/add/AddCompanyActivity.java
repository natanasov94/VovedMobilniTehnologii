package com.example.vmt.company.add;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.vmt.MainActivity;
import com.example.vmt.R;
import com.example.vmt.listeners.buttonlistener.AddCompanyOnClickListener;
import com.example.vmt.listeners.buttonlistener.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
/**
 * Activity used to add new companies
 * */
public class AddCompanyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_company);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout(width, height);

        // Getting views and setting categories dropdown
        TextView companyNameTextView = findViewById(R.id.addCompanyNameTextInput);
        TextView companyPhoneTextView = findViewById(R.id.addCompanyPhoneTextInput);
        TextView companyAddressTextView = findViewById(R.id.addCompanyAddressTextInput);
        TextView companyLogoPathTextView = findViewById(R.id.addCompanyLogoPath);
        TextView companySiteTextView = findViewById(R.id.addCompanySiteTextInput);

        Spinner categorySpinner = findViewById(R.id.addCategoryDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(adapter);

        // Set listeners for buttons
        FloatingActionButton addButton = findViewById(R.id.addCompanyConfirm);
        /*
         * The views are passed to the listener instead of the text
         * values since the values will not have been set when the
         * listener is created, so it needs to extract the latest
         * text value from them
         * */
        addButton.setOnClickListener(
                new AddCompanyOnClickListener(
                    this,
                    MainActivity.class,
                    companyNameTextView,
                    companyPhoneTextView,
                    companyAddressTextView,
                    companyLogoPathTextView,
                    companySiteTextView,
                    categorySpinner
                )
        );

        FloatingActionButton backButton = findViewById(R.id.addCompanyGoBack);
        backButton.setOnClickListener(new TransitionButtonOnClickListener(this, MainActivity.class));
    }
}

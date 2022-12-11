package com.example.vmt.company.add;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.vmt.MainActivity;
import com.example.vmt.R;
import com.example.vmt.listeners.AddCompanyOnClickListener;
import com.example.vmt.listeners.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCompanyActivity extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_company);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout(width, height);

        TextView companyNameTextView = findViewById(R.id.companyNameTextInput);
        TextView companyPhoneTextView = findViewById(R.id.companyPhoneTextInput);
        TextView companyAddressTextView = findViewById(R.id.companyAddressTextInput);
        TextView companyLogoPathTextView = findViewById(R.id.companyLogoPath);
        TextView companySiteTextView = findViewById(R.id.companySiteTextInput);

        Spinner categorySpinner = findViewById(R.id.categoryDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(adapter);
        Log.i("Selected",categorySpinner.getSelectedItem().toString());

        FloatingActionButton addButton = findViewById(R.id.addCompanyConfirm);
        addButton.setOnClickListener(
                new AddCompanyOnClickListener(
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

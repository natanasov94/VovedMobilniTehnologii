package com.example.vmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.vmt.company.dto.Companies;
import com.example.vmt.company.add.AddCompanyActivity;
import com.example.vmt.company.dto.CompanyCategory;
import com.example.vmt.listeners.CategoryOnClickListener;
import com.example.vmt.listeners.buttonlistener.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String COMPANIES_PREFS = "companiesSharedPrefs";

    public static final Companies COMPANIES = new Companies();

    private SharedPreferences sharedPreferences;

    private RecyclerView companyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(COMPANIES_PREFS, MODE_PRIVATE);
        try {
            initializeCompanies();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        companyView = findViewById(R.id.companyView);
        setCategoriesTabs();
        COMPANIES.getCompanyCategories().get(0).displayCompanyCategory(this, companyView);
        FloatingActionButton addCompanyButton = findViewById(R.id.addCompany);
        addCompanyButton.setOnClickListener(new TransitionButtonOnClickListener(this, AddCompanyActivity.class));
    }

    private void initializeCompanies() throws JSONException {
        if (COMPANIES.getCompanyCategories().isEmpty()) {
            // If company categories is empty, load from memory
            COMPANIES.loadFromMemory(sharedPreferences);
            // If empty, then create the categories, since there is no previous memory
            if (COMPANIES.getCompanyCategories().isEmpty()) {
                List<CompanyCategory> companyCategories = new ArrayList<>();
                for (String category : getResources().getStringArray(R.array.categories)) {
                    companyCategories.add(new CompanyCategory(category));
                }
                COMPANIES.setCompanyCategories(companyCategories);
            }
        } else {
            COMPANIES.save(sharedPreferences);
        }
    }

    private void setCategoriesTabs() {
        TabLayout categories = findViewById(R.id.categories);
        for (String category :  getResources().getStringArray(R.array.categories)) {
            categories.addTab(categories.newTab().setText(category));
        }
        categories.selectTab(categories.getTabAt(0));
        categories.addOnTabSelectedListener(new CategoryOnClickListener(this, companyView));
    }

}
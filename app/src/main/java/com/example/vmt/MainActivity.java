package com.example.vmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.vmt.company.dto.Companies;
import com.example.vmt.company.add.AddCompanyActivity;
import com.example.vmt.company.fragment.CategoryFragmentAdapter;
import com.example.vmt.listeners.buttonlistener.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    public static final String COMPANIES_PREFS = "companiesSharedPrefs";
    public static final String COMPANIES_KEY = "companies";

    public static Companies COMPANIES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        initializeCompanies();
        setSwipingAndCategories();
        // Add listener to switch to AddCompanyActivity
        FloatingActionButton addCompanyButton = findViewById(R.id.addCompany);
        addCompanyButton.setOnClickListener(new TransitionButtonOnClickListener(this, AddCompanyActivity.class));
    }

    /**
     * Called every time the main activity is loaded to save or load the companies
     * that should be displayed
     * */
    private void initializeCompanies() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(COMPANIES_PREFS, MODE_PRIVATE);
        if (COMPANIES == null) {
            // Companies have not yet been initialized (this is first start of app)
            String companiesJsonString = sharedPreferences.getString(COMPANIES_KEY, null);
            if (companiesJsonString == null) {
                // There are no in memory companies, load the default ones
                COMPANIES = readCompaniesFromDefault();
            } else {
                // Load the in memory companies
                COMPANIES = gson.fromJson(companiesJsonString, Companies.class);
            }
        } else {
            // Save current state of companies to memory
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(COMPANIES_KEY, gson.toJson(COMPANIES));
            editor.apply();
        }
    }

    /**
     * Load the default companies from a json file
     * */
    private Companies readCompaniesFromDefault() {
        try (Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.default_companies))) {
            return new Gson().fromJson(reader, Companies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    /**
     * Setup tabLayout with categories and view pager for swiping
     * */
    private void setSwipingAndCategories() {
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        CategoryFragmentAdapter categoryFragmentAdapter = new CategoryFragmentAdapter(
                getSupportFragmentManager(),
                getLifecycle()
        );
        viewPager.setAdapter(categoryFragmentAdapter);
        TabLayout categories = findViewById(R.id.categories);
        new TabLayoutMediator(categories, viewPager,
                (tab, position) -> tab.setText(
                        COMPANIES.getCompanyCategories().get(position).getCategoryName()
                )
        ).attach();
    }

}
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

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    public static final String COMPANIES_PREFS = "companiesSharedPrefs";

    public static Companies COMPANIES;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(COMPANIES_PREFS, MODE_PRIVATE);
        try {
            initializeCompanies();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FloatingActionButton addCompanyButton = findViewById(R.id.addCompany);
        setSwipingAndCategories();
        addCompanyButton.setOnClickListener(new TransitionButtonOnClickListener(this, AddCompanyActivity.class));
    }

    private void initializeCompanies() throws JSONException {
        if (COMPANIES == null) {
            String companiesJsonString = sharedPreferences.getString("companies", null);
            if (companiesJsonString == null) {
                COMPANIES = readCompaniesFromDefault();
            } else {
                COMPANIES = new Gson().fromJson(companiesJsonString, Companies.class);
            }
        } else {
            COMPANIES.save(sharedPreferences);
        }
    }

    private Companies readCompaniesFromDefault() {
        try (Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.default_companies))) {
            return new Gson().fromJson(reader, Companies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

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
package com.example.vmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.vmt.company.dto.Companies;
import com.example.vmt.company.dto.Company;
import com.example.vmt.company.add.AddCompany;
import com.example.vmt.company.dto.CompanyCategory;
import com.example.vmt.company.view.CompanyViewAdapter;
import com.example.vmt.listeners.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView companyView;
    Map<String, Company> companyMap;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COMPANIES = "companies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateCompanyView();
        FloatingActionButton addCompanyButton = findViewById(R.id.addCompany);
        addCompanyButton.setOnClickListener(new TransitionButtonOnClickListener(this, AddCompany.class));
        try {
            testCreateAndToJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void populateCompanyView() {
        this.companyView = findViewById(R.id.companyView);
        CompanyViewAdapter viewAdapter = new CompanyViewAdapter(this, null);
        this.companyView.setAdapter(viewAdapter);
        this.companyView.setLayoutManager(new LinearLayoutManager(this));
        this.companyView.addItemDecoration(
                new DividerItemDecoration(
                        getBaseContext(),
                        LinearLayout.VERTICAL
                )
        );
        this.companyView.getAdapter().notifyDataSetChanged();
    }

    private void testCreateAndToJson() throws JSONException {
        Company company1 = new Company("1", "2", "3", "4", "5");
        Company company2 = new Company("6", "7", "8", "9", "10");
        CompanyCategory companyCategory1 = new CompanyCategory("Category1", Collections.singletonList(company1));
        CompanyCategory companyCategory2 = new CompanyCategory("Category2", Collections.singletonList(company2));
        Companies companies = new Companies(Arrays.asList(companyCategory1, companyCategory2));
        JSONObject companiesJsonObject = companies.toJsonObject();
        Log.i("Companies: " , companiesJsonObject.toString());
        testLoadFromJson(companiesJsonObject);
    }

    private void testLoadFromJson(JSONObject companiesJSONObject) throws JSONException {
        Companies companies = new Companies(companiesJSONObject);
        for (CompanyCategory companyCategory : companies.getCompanyCategories()) {
            Log.i("Companies", "Company category :" + companyCategory.getCategoryName());
            for (Company company: companyCategory.getCompanies()) {
                Log.i("Companies", "   Company :" + company.toJsonObject());
            }
        }
    }

}
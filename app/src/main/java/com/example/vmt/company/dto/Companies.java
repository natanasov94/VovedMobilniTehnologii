package com.example.vmt.company.dto;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Companies {

    public String LOGOS_FOLDER = "companyLogos";

    private List<CompanyCategory> companyCategories = new ArrayList<>();

    public JSONObject toJsonObject() throws JSONException {
        JSONObject companiesJsonObject = new JSONObject();
        JSONArray companyCategoriesJSONArray = new JSONArray();
        for (CompanyCategory companyCategory : companyCategories) {
            companyCategoriesJSONArray.put(companyCategory.toJsonObject());
        }
        companiesJsonObject.put("companyCategories", companyCategoriesJSONArray);
        return companiesJsonObject;
    }

    public List<CompanyCategory> getCompanyCategories() {
        return companyCategories;
    }

    public void setCompanyCategories(List<CompanyCategory> companyCategories) {
        this.companyCategories = companyCategories;
    }

    public void save(SharedPreferences sharedPreferences) throws JSONException {
        String companiesJsonString = toJsonObject().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("companies", companiesJsonString);
        editor.apply();
    }

    public void loadFromMemory(SharedPreferences sharedPreferences) throws JSONException {
        String companiesJsonString = sharedPreferences.getString("companies", null);
        if (companiesJsonString == null) {
            return;
        }
        loadCompanies(new JSONObject(companiesJsonString));
        Log.i("Companies", companiesJsonString);
    }

    private void loadCompanies(JSONObject companiesJsonObject) throws JSONException {
        // Used for loading companies
        this.companyCategories = new ArrayList<>();
        JSONArray companyCategoriesJsonArray = companiesJsonObject.getJSONArray("companyCategories");
        for (int i = 0; i < companyCategoriesJsonArray.length(); i++) {
            JSONObject companyCategoryJsonObject = companyCategoriesJsonArray.getJSONObject(i);
            CompanyCategory companyCategory = new CompanyCategory(companyCategoryJsonObject);
            this.companyCategories.add(companyCategory);
        }
    }

    public CompanyCategory getCompanyCategory(String categoryName) {
        for (CompanyCategory companyCategory : companyCategories) {
            if (companyCategory.getCategoryName().equals(categoryName)) {
                return companyCategory;
            }
        }
        throw new IllegalStateException("Cannot find category");

    }
}

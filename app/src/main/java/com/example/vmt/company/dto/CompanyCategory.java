package com.example.vmt.company.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompanyCategory {

    private String categoryName;
    private List<Company> companies;

    public CompanyCategory(String categoryName, List<Company> companies) {
        // Used for adding new categories
        this.categoryName = categoryName;
        this.companies = companies;
    }

    public CompanyCategory(JSONObject categoryJsonObject) throws JSONException {
        // Used for loading existing categories
        this.categoryName = categoryJsonObject.getString("categoryName");
        this.companies = new ArrayList<>();
        JSONArray companiesJsonArray = categoryJsonObject.getJSONArray("companies");
        for (int i = 0; i < companiesJsonArray.length(); i++) {
            JSONObject companyJsonObject = companiesJsonArray.getJSONObject(i);
            Company company = new Company(companyJsonObject);
            this.companies.add(company);
        }
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject categoryJsonObject = new JSONObject();
        categoryJsonObject.put("categoryName", categoryName);
        JSONArray companiesJsonArray = new JSONArray();
        for (Company company : companies) {
            companiesJsonArray.put(company.toJsonObject());
        }
        categoryJsonObject.put("companies", companiesJsonArray);
        return categoryJsonObject;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}

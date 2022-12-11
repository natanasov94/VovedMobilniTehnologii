package com.example.vmt.company.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Companies {

    private List<CompanyCategory> companyCategories;

    public Companies(List<CompanyCategory> companyCategories) {
        // Used for adding new companies
        this.companyCategories = companyCategories;
    }

    public Companies(JSONObject companiesJsonObject) throws JSONException {
        // Used for loading companies
        this.companyCategories = new ArrayList<>();
        JSONArray companyCategoriesJsonArray = companiesJsonObject.getJSONArray("companyCategories");
        for (int i = 0; i < companyCategoriesJsonArray.length(); i++) {
            JSONObject companyCategoryJsonObject = companyCategoriesJsonArray.getJSONObject(i);
            CompanyCategory companyCategory = new CompanyCategory(companyCategoryJsonObject);
            this.companyCategories.add(companyCategory);
        }
    }

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
}

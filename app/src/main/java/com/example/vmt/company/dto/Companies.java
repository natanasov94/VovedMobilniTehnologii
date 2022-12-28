package com.example.vmt.company.dto;

import java.util.ArrayList;
import java.util.List;

public class Companies {

    public final String LOGOS_FOLDER = "companyLogos";

    private final List<CompanyCategory> companyCategories = new ArrayList<>();

    public List<CompanyCategory> getCompanyCategories() {
        return companyCategories;
    }

    public CompanyCategory getCompanyCategory(String categoryName) {
        for (CompanyCategory companyCategory : companyCategories) {
            if (companyCategory.getCategoryName().equals(categoryName)) {
                return companyCategory;
            }
        }
        throw new IllegalStateException("Cannot find category");

    }

    public int getCategoryIndex(String categoryName) {
        for (int i = 0; i < companyCategories.size(); i++) {
            CompanyCategory companyCategory = companyCategories.get(i);
            if (companyCategory.getCategoryName().equals(categoryName)) {
                return i;
            }
        }
        throw new IllegalStateException("Cannot find category");
    }
}


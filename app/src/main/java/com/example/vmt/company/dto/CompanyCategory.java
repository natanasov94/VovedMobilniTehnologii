package com.example.vmt.company.dto;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vmt.company.view.CompanyViewAdapter;
import java.util.List;

public class CompanyCategory {

    private String categoryName;
    private List<Company> companies;

    public void displayCompanyCategory(Context context, RecyclerView companyView) {
        CompanyViewAdapter viewAdapter = new CompanyViewAdapter(context, this);
        companyView.setAdapter(viewAdapter);
        companyView.setLayoutManager(new LinearLayoutManager(context));
        companyView.addItemDecoration(
                new DividerItemDecoration(
                        context,
                        LinearLayout.VERTICAL
                )
        );
        companyView.getAdapter().notifyDataSetChanged();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}

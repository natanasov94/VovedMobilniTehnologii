package com.example.vmt.listeners;

import static com.example.vmt.MainActivity.COMPANIES;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vmt.R;
import com.example.vmt.company.dto.CompanyCategory;
import com.example.vmt.company.view.CompanyViewAdapter;
import com.google.android.material.tabs.TabLayout;

public class CategoryOnClickListener implements TabLayout.OnTabSelectedListener {

    Context context;
    RecyclerView companyView;

    public CategoryOnClickListener(Context context, RecyclerView companyView) {
        this.context = context;
        this.companyView = companyView;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        CompanyCategory category = COMPANIES.getCompanyCategories().get(tab.getPosition());
        category.displayCompanyCategory(context, companyView);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

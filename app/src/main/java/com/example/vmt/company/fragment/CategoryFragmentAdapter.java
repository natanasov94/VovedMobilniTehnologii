package com.example.vmt.company.fragment;

import static com.example.vmt.MainActivity.COMPANIES;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vmt.company.dto.CompanyCategory;

public class CategoryFragmentAdapter extends FragmentStateAdapter {

    public CategoryFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        CompanyCategory companyCategory = COMPANIES.getCompanyCategories().get(position);
        return new CompanyFragment(companyCategory);
    }

    @Override
    public int getItemCount() {
        return COMPANIES.getCompanyCategories().size();
    }
}

package com.example.vmt.company.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vmt.R;
import com.example.vmt.company.dto.CompanyCategory;

public class CompanyFragment extends Fragment {

    CompanyCategory companyCategory;

    public CompanyFragment(CompanyCategory companyCategory) {
        this.companyCategory = companyCategory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView companyView = view.findViewById(R.id.companyView);
        companyCategory.displayCompanyCategory(getContext(), companyView);
    }
}

package com.example.vmt.company.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vmt.R;
import com.example.vmt.company.dto.CompanyCategory;
import com.example.vmt.listeners.CompanyOnClickListener;

/**
 * Represents a single company card (element)
 * */
public class CompanyViewHolder extends RecyclerView.ViewHolder {

    private final ImageView companyLogo;
    private final TextView companyName;
    private final TextView companyAddress;
    private final TextView companyPhone;
    private final TextView companySite;

    private final CompanyCategory companyCategory;

    public CompanyViewHolder(@NonNull View itemView, CompanyCategory companyCategory) {
        super(itemView);
        this.companyCategory = companyCategory;
        this.companyLogo = itemView.findViewById(R.id.companyLogo);
        this.companyName = itemView.findViewById(R.id.companyName);
        this.companyAddress = itemView.findViewById(R.id.companyAddress);
        this.companyPhone = itemView.findViewById(R.id.companyPhone);
        this.companySite = itemView.findViewById(R.id.companySite);
        // Add listener to be able to edit the company
        itemView.findViewById(R.id.companyViewHolder).setOnClickListener(
                new CompanyOnClickListener(this)
        );
    }

    public ImageView getCompanyLogo() {
        return companyLogo;
    }

    public TextView getCompanyName() {
        return companyName;
    }

    public TextView getCompanyAddress() {
        return companyAddress;
    }

    public TextView getCompanyPhone() {
        return companyPhone;
    }

    public TextView getCompanySite() {
        return companySite;
    }

    public CompanyCategory getCompanyCategory() {
        return companyCategory;
    }
}

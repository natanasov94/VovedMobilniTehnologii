package com.example.vmt.company.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vmt.R;
import com.example.vmt.company.dto.CompanyCategory;
import com.example.vmt.listeners.CompanyOnClickListener;

public class CompanyViewHolder extends RecyclerView.ViewHolder {

    private ImageView companyLogo;
    private TextView companyName;
    private TextView companyAddress;
    private TextView companyPhone;
    private TextView companySite;

    private CompanyCategory companyCategory;

    public CompanyViewHolder(@NonNull View itemView, CompanyCategory companyCategory) {
        super(itemView);
        this.companyCategory = companyCategory;
        this.companyLogo = itemView.findViewById(R.id.companyLogo);
        this.companyName = itemView.findViewById(R.id.companyName);
        this.companyAddress = itemView.findViewById(R.id.companyAddress);
        this.companyPhone = itemView.findViewById(R.id.companyPhone);
        this.companySite = itemView.findViewById(R.id.companySite);
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

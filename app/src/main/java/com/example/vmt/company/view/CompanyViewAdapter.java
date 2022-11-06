package com.example.vmt.company.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vmt.R;
import com.example.vmt.company.Company;

import java.util.List;

public class CompanyViewAdapter extends RecyclerView.Adapter<CompanyViewHolder> {

    Context context;
    private List<Company> companyList;

    public CompanyViewAdapter(Context context, List<Company> companyList){
        this.context = context;
        this.companyList = companyList;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.company,parent,false);
        return new CompanyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        holder.getCompanyLogo().setImageResource(R.drawable.java_logo);
        holder.getCompanyName().setText("Test company name");
        holder.getCompanyAddress().setText("Test company address");
        holder.getCompanyPhone().setText(Integer.toString(position));
        holder.getCompanySite().setText("localhost:8080");
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

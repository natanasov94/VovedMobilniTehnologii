package com.example.vmt.listeners;

import android.util.Log;
import android.view.View;

import com.example.vmt.company.view.CompanyViewHolder;

public class CompanyOnClickListener implements View.OnClickListener {

    CompanyViewHolder holder;

    public CompanyOnClickListener(CompanyViewHolder holder) {
        this.holder = holder;
    }

    @Override
    public void onClick(View view) {
        Log.i("Click", holder.getCompanyPhone().getText() + " has been clicked");
    }
}

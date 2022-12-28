package com.example.vmt.listeners;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

import com.example.vmt.company.edit.EditCompanyActivity;
import com.example.vmt.company.view.CompanyViewHolder;

/**
 * Listener to open {@link EditCompanyActivity} with the information about
 * which company was clicked, so it can be edited
 * */
public class CompanyOnClickListener implements View.OnClickListener {

    private final CompanyViewHolder holder;

    public CompanyOnClickListener(CompanyViewHolder holder) {
        this.holder = holder;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), EditCompanyActivity.class);
        /*
         * Pass the category and selected company's index to be able to locate the company
         * object and more easily move/remove it
         */
        intent.putExtra("category", holder.getCompanyCategory().getCategoryName());
        intent.putExtra("index", holder.getAdapterPosition());
        // Transition to new EditCompanyActivity
        view.getContext().startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle()
        );
    }
}

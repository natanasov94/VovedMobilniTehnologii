package com.example.vmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.vmt.company.add.AddCompany;
import com.example.vmt.company.view.CompanyViewAdapter;
import com.example.vmt.listeners.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView companyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateCompanyView();
        FloatingActionButton addCompanyButton = findViewById(R.id.addCompany);
        addCompanyButton.setOnClickListener(new TransitionButtonOnClickListener(this, AddCompany.class));
    }

    private void populateCompanyView() {
        this.companyView = findViewById(R.id.companyView);
        CompanyViewAdapter viewAdapter = new CompanyViewAdapter(this, null);
        this.companyView.setAdapter(viewAdapter);
        this.companyView.setLayoutManager(new LinearLayoutManager(this));
        this.companyView.addItemDecoration(
                new DividerItemDecoration(
                        getBaseContext(),
                        LinearLayout.VERTICAL
                )
        );
        this.companyView.getAdapter().notifyDataSetChanged();
    }
}
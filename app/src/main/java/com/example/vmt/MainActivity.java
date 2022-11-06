package com.example.vmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.vmt.company.view.CompanyViewAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView companyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
package com.example.vmt.company.add;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.example.vmt.MainActivity;
import com.example.vmt.R;
import com.example.vmt.listeners.TransitionButtonOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCompany extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_company);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout(width, height);

        FloatingActionButton backButton = findViewById(R.id.addCompanyGoBack);
        backButton.setOnClickListener(new TransitionButtonOnClickListener(this, MainActivity.class));
    }
}

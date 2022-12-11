package com.example.vmt.company.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vmt.R;
import com.example.vmt.company.dto.Company;

import java.io.InputStream;
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
        Company company = companyList.get(position);
        Log.i("Logo url",company.getLogoPath());
        new DownloadImageTask(holder.getCompanyLogo()).execute(company.getLogoPath());
        holder.getCompanyName().setText(company.getName());
        holder.getCompanyAddress().setText(company.getAddress());
        holder.getCompanyPhone().setText(company.getPhone());
        holder.getCompanySite().setText(company.getSite());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    /**
     * Implementation based on https://stackoverflow.com/a/10868126
     * */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

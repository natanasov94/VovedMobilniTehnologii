package com.example.vmt.company.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vmt.MainActivity;
import com.example.vmt.R;
import com.example.vmt.company.dto.Company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CompanyViewAdapter extends RecyclerView.Adapter<CompanyViewHolder> {

    Context context;
    private List<Company> companyList;

    public CompanyViewAdapter(Context context, List<Company> companyList) {
        this.context = context;
        this.companyList = companyList;
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.company, parent, false);
        return new CompanyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        Company company = companyList.get(position);
        loadLogo(company, holder.getCompanyLogo());
        holder.getCompanyName().setText(company.getName());
        holder.getCompanyAddress().setText(company.getAddress());
        holder.getCompanyPhone().setText(company.getPhone());
        holder.getCompanySite().setText(company.getSite());
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    private void loadLogo(Company company, ImageView companyImage) {
        String logoPath = company.getLogoPath();
        // Check if the logo exists locally.
        if (!new File(logoPath).exists()) {
            /*
             * If the logo doesn't exist locally, download it, set it to the
             * image view and reference it in the company
             */
            new DownloadImageTask(companyImage, company, context).execute();
        } else {
            // Image exists locally, load it and set to the image
            Bitmap myBitmap = BitmapFactory.decodeFile(logoPath);
            companyImage.setImageBitmap(myBitmap);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        Company company;
        Context context;

        public DownloadImageTask(ImageView imageView, Company company, Context context) {
            this.imageView = imageView;
            this.company = company;
            this.context = context;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                // Download the image as bitmap
                bitmap = Glide.with(context)
                        .asBitmap()
                        .load(company.getLogoPath())
                        .submit()
                        .get();
                // Get the internal path where the image should be downloaded
                String internalLogoPath = UUID.randomUUID().toString() + ".jpg";
                ContextWrapper contextWrapper = new ContextWrapper(context.getApplicationContext());
                File companyLogosDirectory = contextWrapper.getDir(MainActivity.COMPANIES.LOGOS_FOLDER, Context.MODE_PRIVATE);
                File newLogoPath = new File(companyLogosDirectory, internalLogoPath);
                // Set to the company for further use
                company.setLogoPath(newLogoPath.getAbsolutePath());
                // Save the image to the internal storage
                FileOutputStream outputStream = new FileOutputStream(newLogoPath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            // Display the image in the ImageView
            imageView.setImageBitmap(result);
        }
    }
}

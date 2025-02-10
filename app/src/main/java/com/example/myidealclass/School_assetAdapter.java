package com.example.myidealclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class School_assetAdapter extends RecyclerView.Adapter<School_assetAdapter.School_assetViewHolder> {
    private Context context;
    private List<School_asset> school_assetList; // Используем List<Event>

    public School_assetAdapter(Context context, List<School_asset> school_asset) {
        this.context = context;
        this.school_assetList = school_assetList; // Правильное присваивание
    }

    @NonNull
    @Override
    public School_assetAdapter.School_assetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_asset_item, parent, false);
        return new School_assetAdapter.School_assetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull School_assetAdapter.School_assetViewHolder holder, int position) {
        School_asset schoolAsset = school_assetList.get(position);

        holder.title.setText(schoolAsset.getTitle());
        holder.place.setText(schoolAsset.getPlace());
        holder.describe.setText(schoolAsset.getDescribe());
        holder.firstName.setText(schoolAsset.getfirstName());
        holder.secondname.setText(schoolAsset.getsecondname());
        holder.thirsdname.setText(schoolAsset.getthirsdname());
        String base64Image = schoolAsset.getImageData();
        if (base64Image != null && !base64Image.isEmpty()) {
            try {
                byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.photo.setImageBitmap(bitmap);
            } catch (IllegalArgumentException e) {
                Log.e("EventsAdapter", "Ошибка декодирования Base64", e);
            }
        }
    }

    @Override
    public int getItemCount() {
        return school_assetList.size();
    }

    public static class School_assetViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title, place, describe, firstName, secondname, thirsdname;

        public School_assetViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imageApplication_form);
            title = itemView.findViewById(R.id.titleApplication_form);
            place = itemView.findViewById(R.id.TypeEvent);
            describe = itemView.findViewById(R.id.DescriptionApplication_form);
            firstName = itemView.findViewById(R.id.firstNameApplication_form);
            secondname = itemView.findViewById(R.id.secondnameApplication_form);
            thirsdname = itemView.findViewById(R.id.thirsdnameApplication_form);
        }
    }
}

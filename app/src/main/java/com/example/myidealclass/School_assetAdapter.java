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

    public School_assetAdapter(Context context, List<School_asset> school_assetList) {
        this.context = context;
        this.school_assetList = school_assetList; // Теперь всё ок
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
                holder.imageData.setImageBitmap(bitmap);
            } catch (IllegalArgumentException e) {
                Log.e("SchoolAssetAdapter", "Ошибка декодирования Base64", e);
                // Здесь можно поставить изображение по умолчанию
                holder.imageData.setImageResource(R.drawable.about_app_1); // Поставьте свое изображение по умолчанию
            }
        } else {
            holder.imageData.setImageResource(R.drawable.about_app_1); // Если нет картинки, поставить изображение по умолчанию
        }
    }


    @Override
    public int getItemCount() {
        return school_assetList.size();
    }

    public static class School_assetViewHolder extends RecyclerView.ViewHolder {
        ImageView imageData;
        TextView title, place, describe, firstName, secondname, thirsdname;

        public School_assetViewHolder(@NonNull View itemView) {
            super(itemView);
            imageData = itemView.findViewById(R.id.imageApplication_form);
            title = itemView.findViewById(R.id.titleApplication_form);
            place = itemView.findViewById(R.id.Place_asset);
            describe = itemView.findViewById(R.id.DescriptionApplication_form);
            firstName = itemView.findViewById(R.id.firstNameApplication_form);
            secondname = itemView.findViewById(R.id.secondnameApplication_form);
            thirsdname = itemView.findViewById(R.id.thirsdnameApplication_form);
        }
    }
}

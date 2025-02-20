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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Important_informationAdapter extends RecyclerView.Adapter<Important_informationAdapter.Important_informationViewHolder> {
    private Context context;
    private List<Important_information> importent_informationList; // Используем List<Important_information>

    public Important_informationAdapter(Context context, List<Important_information> eventList) {
        this.context = context;
        this.importent_informationList = eventList; // Правильное присваивание
    }

    @NonNull
    @Override
    public Important_informationAdapter.Important_informationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.important_information_item, parent, false);
        return new Important_informationAdapter.Important_informationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Important_informationAdapter.Important_informationViewHolder holder, int position) {
        Important_information important_information = importent_informationList.get(position);

        holder.title.setText(important_information.getTitle());
        holder.term.setText(important_information.getTerm());
        holder.describe.setText(important_information.getDescribe());

        // Форматирование даты
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        holder.date.setText(sdf.format(important_information.getDate()));

        String base64Image = important_information.getImageData();
        if (base64Image != null && !base64Image.isEmpty()) {
            try {
                byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.photo.setImageBitmap(bitmap);
            } catch (IllegalArgumentException e) {
                Log.e("Important_informationAdapter", "Ошибка декодирования Base64", e);
            }
        }
    }

    @Override
    public int getItemCount() {
        return importent_informationList != null ? importent_informationList.size() : 0;  // Добавлена проверка на null
    }

    public static class Important_informationViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title, term, describe, date;

        public Important_informationViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imageImportant_information);
            title = itemView.findViewById(R.id.titleImportant_information);
            term = itemView.findViewById(R.id.TermImportant_information);
            describe = itemView.findViewById(R.id.DescriptionImportant_information);
            date = itemView.findViewById(R.id.DateImportant_information);
        }
    }
}

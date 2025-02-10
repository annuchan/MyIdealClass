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

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private Context context;
    private List<Event> eventList; // Используем List<Event>

    public EventsAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList; // Правильное присваивание
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.title.setText(event.getTitle());
        holder.type.setText(event.getType());
        holder.describe.setText(event.getDescribe());

        // Форматирование даты
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        holder.date.setText(sdf.format(event.getDate()));

        String base64Image = event.getImageData();
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
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title, type, describe, date;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imageevent);
            title = itemView.findViewById(R.id.titleEvent);
            type = itemView.findViewById(R.id.TypeEvent);
            describe = itemView.findViewById(R.id.DescriptionEvent);
            date = itemView.findViewById(R.id.DateTimeEvent);
        }
    }
}

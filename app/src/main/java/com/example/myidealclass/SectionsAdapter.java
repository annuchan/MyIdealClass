package com.example.myidealclass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {

    private List<Club> clubs;

    public SectionsAdapter(List<Club> clubs) {
        this.clubs = clubs;
        Log.d("SectionsAdapter", "Adapter initialized with " + clubs.size() + " clubs.");
    }
    public void updateData(List<Club> newClubs) {
        this.clubs.clear();
        this.clubs.addAll(newClubs);
        notifyDataSetChanged(); // Обновляем RecyclerView
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_item_user, parent, false);
        Log.d("SectionsAdapter", "ViewHolder created");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Club club = clubs.get(position);

        Log.d("SectionsAdapter", "Binding data for club at position: " + position);
        Log.d("SectionsAdapter", "Title: " + club.getTitle());
        Log.d("SectionsAdapter", "Description: " + club.getDescribe());
        Log.d("SectionsAdapter", "Place: " + club.getPlace());

        holder.titleTextView.setText(club.getTitle());
        holder.describeTextView.setText(club.getDescribe());
        holder.placeTextView.setText(club.getPlace());

        // Обработка изображения
        String base64Image = club.getImageData();
        if (base64Image != null && !base64Image.isEmpty()) {
            try {
                Log.d("SectionsAdapter", "Decoding Base64 image data.");
                byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.imageView.setImageBitmap(bitmap);
            } catch (IllegalArgumentException e) {
                Log.e("SectionsAdapter", "Failed to decode Base64 image: " + e.getMessage());
                holder.imageView.setImageResource(R.drawable.school2);
            }
        } else {
            Log.d("SectionsAdapter", "No image data available, using default image.");
            holder.imageView.setImageResource(R.drawable.school2);
        }

        // Исправляем вызов метода
        fillSchedule(holder.scheduleLayout, club.getSchedules());
    }

    @Override
    public int getItemCount() {
        Log.d("SectionsAdapter", "Item count: " + clubs.size());
        return clubs.size();
    }

    private void fillSchedule(LinearLayout scheduleLayout, List<Club.Schedule> schedule) {
        scheduleLayout.removeAllViews();
        Log.d("SectionsAdapter", "Filling schedule, items count: " + (schedule != null ? schedule.size() : 0));

        if (schedule == null || schedule.isEmpty()) {
            Log.d("SectionsAdapter", "No schedule available.");
            TextView noScheduleTextView = new TextView(scheduleLayout.getContext());
            noScheduleTextView.setText("Нет расписания");
            scheduleLayout.addView(noScheduleTextView);
        } else {
            for (Club.Schedule item : schedule) {
                Log.d("SectionsAdapter", "Adding schedule item: " + item.getDayOfWeek() + " " + item.getStartTime() + " - " + item.getEndTime());
                LinearLayout scheduleRow = new LinearLayout(scheduleLayout.getContext());
                scheduleRow.setOrientation(LinearLayout.HORIZONTAL);
                scheduleRow.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                TextView dayTextView = new TextView(scheduleLayout.getContext());
                dayTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                dayTextView.setText(item.getDayOfWeek());

                TextView timeTextView = new TextView(scheduleLayout.getContext());
                timeTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                timeTextView.setText(item.getStartTime() + " - " + item.getEndTime());  // Исправлено!

                scheduleRow.addView(dayTextView);
                scheduleRow.addView(timeTextView);

                scheduleLayout.addView(scheduleRow);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, describeTextView, placeTextView;
        ImageView imageView;
        LinearLayout scheduleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleApplication_form);
            describeTextView = itemView.findViewById(R.id.DescriptionApplication_form);
            placeTextView = itemView.findViewById(R.id.Place_asset);
            imageView = itemView.findViewById(R.id.imageApplication_form);
            scheduleLayout = itemView.findViewById(R.id.scheduleLayout);
            Log.d("SectionsAdapter", "ViewHolder initialized.");
        }
    }
}

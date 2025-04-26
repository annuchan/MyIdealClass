package com.example.myidealclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ViewHolder> {
    private Context context;
    private List<Club> clubList;

    public ClubAdapter(Context context, List<Club> clubList) {
        this.context = context;
        this.clubList = clubList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.asset_item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Club club = clubList.get(position);
        holder.title.setText(club.getTitle());
        holder.description.setText(club.getDescribe());
        holder.place.setText(club.getPlace());
        holder.teacherFIO.setText(club.getTeacherFIO());

        // Загружаем изображение
        if (club.getImageData() != null) {
            Picasso.get().load(club.getImageData()).into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.school2);
        }

        // Отображаем расписание
        StringBuilder scheduleText = new StringBuilder();
        for (Club.Schedule schedule : club.getSchedules()) {
            scheduleText.append(schedule.getDayOfWeek()).append(": ").append(schedule.getStartTime()).append("\n");
        }
        holder.schedule.setText(scheduleText.toString());
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, place, teacherFIO, schedule;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleApplication_form);
            description = itemView.findViewById(R.id.DescriptionApplication_form);
            place = itemView.findViewById(R.id.Place_asset);
            teacherFIO = itemView.findViewById(R.id.firstNameApplication_form);
            schedule = itemView.findViewById(R.id.scheduleText);
            image = itemView.findViewById(R.id.imageApplication_form);
        }
    }
}


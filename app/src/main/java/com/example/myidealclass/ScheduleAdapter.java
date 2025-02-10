package com.example.myidealclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private final List<HomeworkItem> scheduleList;

    public ScheduleAdapter(List<HomeworkItem> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeworkItem item = scheduleList.get(position);
        holder.subject.setText(item.getSubject());
        holder.homework.setText(item.getHomework());
        holder.grade.setText(item.getGrade());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject, homework, grade;

        ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.tvSubject);
            homework = itemView.findViewById(R.id.tvHomework);
            grade = itemView.findViewById(R.id.tvGrade);
        }
    }
}

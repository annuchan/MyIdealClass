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

    // Конструктор адаптера
    public ScheduleAdapter(List<HomeworkItem> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating XML для каждого элемента в списке
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Получаем объект HomeworkItem по позиции
        HomeworkItem item = scheduleList.get(position);

        // Привязываем данные к элементам интерфейса
        holder.subject.setText(item.getSubjectTitle()); // Название предмета
        holder.homework.setText(item.getTask()); // Задание/домашка
    }

    @Override
    public int getItemCount() {
        return scheduleList.size(); // Возвращаем количество элементов в списке
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject, homework;

        ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.tvSubject); // Ссылка на TextView для предмета
            homework = itemView.findViewById(R.id.tvHomework); // Ссылка на TextView для задания
        }
    }
}

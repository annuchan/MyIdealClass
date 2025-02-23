package com.example.myidealclass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;


public class Dropdown_menu_Teacher {
    public static void showCustomPopupMenu(View view, Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dropdown_menu_teacher, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true); // true = окно закрывается при клике вне него

        TextView item1 = popupView.findViewById(R.id.menu_item1);
        TextView item2 = popupView.findViewById(R.id.menu_item2);
        TextView item3 = popupView.findViewById(R.id.menu_item3);
        TextView item4 = popupView.findViewById(R.id.menu_item4);
        TextView item5 = popupView.findViewById(R.id.menu_item5);

        // Обработчики кликов
        item1.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу домашнее задание", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Homework_admin.class));
            popupWindow.dismiss();
        });

        item2.setOnClickListener(v -> {
           Toast.makeText(context, "Вы перешли на страницу Оценки", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Evalution_admin.class));
            popupWindow.dismiss();
       });

        item3.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу Итоговые оценки", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Final_grades_admin.class));
            popupWindow.dismiss();
       });
        item4.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу кружков", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Asset_admin.class));
            popupWindow.dismiss();
        });
//        item5.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу питания", Toast.LENGTH_SHORT).show();
//            context.startActivity(new Intent(context, Event_User.class));
//            popupWindow.dismiss();
//        });
        // Показываем PopupWindow
        popupWindow.showAsDropDown(view);

    }
}


package com.example.myidealclass;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.example.myidealclass.R;

public class Dropdown_Menu {
    public static void showCustomPopupMenu(View view, Context context) {
        // Раздуваем кастомное меню из XML
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.custom_menu_item, null);

        // Создаем PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true); // true = окно закрывается при клике вне него

        // Находим элементы меню
        TextView item1 = popupView.findViewById(R.id.menu_item1);
//        TextView item2 = popupView.findViewById(R.id.menu_item2);
//        TextView item3 = popupView.findViewById(R.id.menu_item3);
        TextView item4 = popupView.findViewById(R.id.menu_item4);
        TextView item5 = popupView.findViewById(R.id.menu_item5);
        TextView item6 = popupView.findViewById(R.id.menu_item6);
//        TextView item7 = popupView.findViewById(R.id.menu_item7);
        TextView item8 = popupView.findViewById(R.id.menu_item8);
        TextView item9 = popupView.findViewById(R.id.menu_item9);

        // Обработчики кликов
        item1.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу школы", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, School_info.class));
            popupWindow.dismiss();
        });

//        item2.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу электронного дневника", Toast.LENGTH_SHORT).show();
//            popupWindow.dismiss();
//        });

//        item3.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу домашнего задания", Toast.LENGTH_SHORT).show();
//            popupWindow.dismiss();
//        });
        item4.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу уведомлений об учебе", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Important_information_user.class));
            popupWindow.dismiss();
        });
        item5.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу мероприятий", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Event_User.class));
            popupWindow.dismiss();
        });
        item6.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу актива школы", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, School_asset_user.class));
            popupWindow.dismiss();
        });
//        item7.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу питания", Toast.LENGTH_SHORT).show();
//            popupWindow.dismiss();
//        });
        item8.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу учителей", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Teacher_Select_User.class));
            popupWindow.dismiss();
        });
        item9.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на ваших секций", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, School_asset_my_user.class));
            popupWindow.dismiss();
        });

        // Показываем PopupWindow
        popupWindow.showAsDropDown(view);
    }
}

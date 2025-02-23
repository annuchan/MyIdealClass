package com.example.myidealclass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Dropdown_menu_admin {
    public static void showCustomPopupMenu(View view, Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dropdown_admin, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        TextView item1 = popupView.findViewById(R.id.menu_item1);
        TextView item2 = popupView.findViewById(R.id.menu_item2);
        TextView item3 = popupView.findViewById(R.id.menu_item3);
        TextView item4 = popupView.findViewById(R.id.menu_item4);
        TextView item5 = popupView.findViewById(R.id.menu_item5);
        TextView item6 = popupView.findViewById(R.id.menu_item6);
        TextView item7 = popupView.findViewById(R.id.menu_item7);
        TextView item8 = popupView.findViewById(R.id.menu_item8);


        // Обработчики кликов
        item1.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу школы", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, School_info.class));
            popupWindow.dismiss();
        });

       item2.setOnClickListener(v -> {
           Toast.makeText(context, "Вы перешли на страницу электронного дневника", Toast.LENGTH_SHORT).show();
          popupWindow.dismiss();
      });

      item3.setOnClickListener(v -> {
          Toast.makeText(context, "Вы перешли на страницу домашнего задания", Toast.LENGTH_SHORT).show();
          popupWindow.dismiss();
      });
//        item4.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу уведомлений об учебе", Toast.LENGTH_SHORT).show();
//            context.startActivity(new Intent(context, Important_information_user.class));
//            popupWindow.dismiss();
//        });
        item5.setOnClickListener(v -> {
            Toast.makeText(context, "Вы перешли на страницу мероприятий", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Event_User.class));
            popupWindow.dismiss();
        });
//        item6.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу актива школы", Toast.LENGTH_SHORT).show();
//            context.startActivity(new Intent(context, School_asset_user.class));
//            popupWindow.dismiss();
//        });
//      item7.setOnClickListener(v -> {
//           Toast.makeText(context, "Вы перешли на страницу питания", Toast.LENGTH_SHORT).show();
//           popupWindow.dismiss();
//      });
//        item8.setOnClickListener(v -> {
//            Toast.makeText(context, "Вы перешли на страницу учителей", Toast.LENGTH_SHORT).show();
//            context.startActivity(new Intent(context, Teacher_Select_User.class));
//            popupWindow.dismiss();
//        });

        popupWindow.showAsDropDown(view);
    }
}


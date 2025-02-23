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

import com.example.myidealclass.Teacher;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private Context context;
    private List<Teacher> teacherList;

    public TeacherAdapter(Context context, List<Teacher> teacherList) {
        this.context = context;
        this.teacherList = teacherList;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.teacher_item, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teacherList.get(position);

        holder.firstName.setText(teacher.getFirstName());
        holder.lastName.setText(teacher.getLastName());
        holder.middleName.setText(teacher.getMiddleName());
        holder.subject.setText(teacher.getSpecialty());
        holder.education.setText(teacher.getEducation());
        holder.experience.setText(String.valueOf(teacher.getExperience()));
        holder.title.setText(teacher.getTitle());
        holder.academicDegree.setText(teacher.getAcademicDegree());
        holder.certificate.setText(teacher.getCertificateTeacher());
        holder.qualification.setText(teacher.getQualification());
        holder.professionalDevelopment.setText(teacher.getProfessionalDevelopment());
        String base64Image = teacher.getImageData();  // Убедитесь, что это строка Base64

        if (base64Image != null && !base64Image.isEmpty()) {
            try {
                byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.photo.setImageBitmap(bitmap);
            } catch (IllegalArgumentException e) {
                Log.e("TeacherAdapter", "Ошибка декодирования Base64", e);
            }
        }
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView firstName, lastName, middleName, subject, education, experience, title, academicDegree, certificate, qualification, professionalDevelopment;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.foto_teacher);
            firstName = itemView.findViewById(R.id.teacherfirstname);
            lastName = itemView.findViewById(R.id.teachersecondname);
            middleName = itemView.findViewById(R.id.teacherthirsdname);
            subject = itemView.findViewById(R.id.subject_teacher);
            education = itemView.findViewById(R.id.Education_teacher);
            experience = itemView.findViewById(R.id.Expirience);
            title = itemView.findViewById(R.id.Title_teacher);
            academicDegree = itemView.findViewById(R.id.Academic_Degree);
            certificate = itemView.findViewById(R.id.Certificate_teacher);
            qualification = itemView.findViewById(R.id.Qualification_teacher);
            professionalDevelopment = itemView.findViewById(R.id.Professional_development);
        }
    }

    // Используйте правильную переменную для обновления списка
    public void updateTeachers(List<Teacher> teacherList) {
        this.teacherList = teacherList;
        notifyDataSetChanged();  // Обновляем адаптер
    }
}


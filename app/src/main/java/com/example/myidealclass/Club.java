package com.example.myidealclass;

import java.util.List;

public class Club {
    private int id;
    private String title;
    private String describe;
    private String place;
    private String teacherFIO;
    private String imageName;
    private String imageData;
    private List<Schedule> schedules;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescribe() { return describe; }
    public String getPlace() { return place; }
    public String getTeacherFIO() { return teacherFIO; }
    public String getImageName() { return imageName; }
    public String getImageData() { return imageData; }
    public List<Schedule> getSchedules() { return schedules; }

    public static class Schedule {
        private String dayOfWeek;
        private String startTime;
        private String endTime;

        public String getDayOfWeek() { return dayOfWeek; }
        public String getStartTime() { return startTime; }
        public String getEndTime() { return endTime; }
    }
}


package com.example.myidealclass;

public class HomeworkItem {
    private final int id;
    private final String task;
    private final String evaluation;
    private final String dateHomework;
    private final int classNumber; // Номер класса
    private final String classIdentifier; // Буква класса
    private final int teacherId; // ID учителя
    private final String subjectTitle; // Название предмета

    public HomeworkItem(int id, String task, String evaluation, String dateHomework, int classNumber, String classIdentifier, int teacherId, String subjectTitle) {
        this.id = id;
        this.task = task;
        this.evaluation = evaluation;
        this.dateHomework = dateHomework;
        this.classNumber = classNumber;
        this.classIdentifier = classIdentifier;
        this.teacherId = teacherId;
        this.subjectTitle = subjectTitle;
    }

    public int getId() { return id; }
    public String getTask() { return task; }
    public String getEvaluation() { return evaluation; }
    public String getDateHomework() { return dateHomework; }
    public int getClassNumber() { return classNumber; }
    public String getClassIdentifier() { return classIdentifier; }
    public int getTeacherId() { return teacherId; }
    public String getSubjectTitle() { return subjectTitle; }
}

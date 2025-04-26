package com.example.myidealclass;

public class HomeworkItem {
    private int id;
    private String task;
    private String evaluation;
    private String dateHomework;
    private int classNumber; // Номер класса
    private String classIdentifier; // Буква класса
    private int teacherId; // ID учителя
    private String subjectTitle; // Название предмета

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

    public void setId(int id) { this.id = id; }
    public void setTask(String task) { this.task = task; }
    public void setEvaluation(String evaluation) { this.evaluation = evaluation; }
    public void setDateHomework(String dateHomework) { this.dateHomework = dateHomework; }
    public void setClassNumber(int classNumber) { this.classNumber = classNumber; }
    public void setClassIdentifier(String classIdentifier) { this.classIdentifier = classIdentifier; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }
    public void setSubjectTitle(String subjectTitle) { this.subjectTitle = subjectTitle; }

    @Override
    public String toString() {
        return "Homework ID: " + id + ", Task: " + task + ", Evaluation: " + evaluation + ", Date: " + dateHomework;
    }
}

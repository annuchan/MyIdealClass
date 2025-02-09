package com.example.myidealclass;

import java.util.Date;

public class Teacher { private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String specialty;
    private String education;
    private int experience;
    private String academicDegree;
    private String title;
    private String certificateTeacher;
    private String qualification;
    private String professionalDevelopment;
    private String phoneNumber;
    private String addressTeacher;
    private Date dateOfBirth;
    private int idSubject;
    private int idPassport;
    private String imageData;

    // Конструктор
    public Teacher(int id, String lastName, String firstName, String middleName, String specialty,
                   String education, int experience, String academicDegree, String title,
                   String certificateTeacher, String qualification, String professionalDevelopment,
                   String phoneNumber, String addressTeacher, Date dateOfBirth, int idSubject,
                   int idPassport, String imageData) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.specialty = specialty;
        this.education = education;
        this.experience = experience;
        this.academicDegree = academicDegree;
        this.title = title;
        this.certificateTeacher = certificateTeacher;
        this.qualification = qualification;
        this.professionalDevelopment = professionalDevelopment;
        this.phoneNumber = phoneNumber;
        this.addressTeacher = addressTeacher;
        this.dateOfBirth = dateOfBirth;
        this.idSubject = idSubject;
        this.idPassport = idPassport;
        this.imageData = imageData;
    }

    // Геттеры
    public int getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getSpecialty() { return specialty; }
    public String getEducation() { return education; }
    public int getExperience() { return experience; }
    public String getAcademicDegree() { return academicDegree; }
    public String getTitle() { return title; }
    public String getCertificateTeacher() { return certificateTeacher; }
    public String getQualification() { return qualification; }
    public String getProfessionalDevelopment() { return professionalDevelopment; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddressTeacher() { return addressTeacher; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public int getIdSubject() { return idSubject; }
    public int getIdPassport() { return idPassport; }
    public String getImageData() {

        return imageData;
    }

    // Сеттеры
    public void setId(int id) { this.id = id; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setEducation(String education) { this.education = education; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setAcademicDegree(String academicDegree) { this.academicDegree = academicDegree; }
    public void setTitle(String title) { this.title = title; }
    public void setCertificateTeacher(String certificateTeacher) { this.certificateTeacher = certificateTeacher; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public void setProfessionalDevelopment(String professionalDevelopment) { this.professionalDevelopment = professionalDevelopment; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddressTeacher(String addressTeacher) { this.addressTeacher = addressTeacher; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setIdSubject(int idSubject) { this.idSubject = idSubject; }
    public void setIdPassport(int idPassport) { this.idPassport = idPassport; }
}
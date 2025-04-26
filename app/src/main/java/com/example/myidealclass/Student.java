package com.example.myidealclass;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private String addressStudent;
    private String dateOfBirth;
    private int idClass;
    private int idPassport;
    private int idNutrition;
    private int idAsset;

    // Геттеры
    public int getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddressStudent() { return addressStudent; }
    public String getDateOfBirth() { return dateOfBirth; }
    public int getIdClass() { return idClass; }
    public int getIdPassport() { return idPassport; }
    public int getIdNutrition() { return idNutrition; }
    public int getIdAsset() { return idAsset; }

    // Удобный метод для отображения ФИО
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
}

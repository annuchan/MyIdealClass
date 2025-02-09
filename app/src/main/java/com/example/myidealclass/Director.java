package com.example.myidealclass;

public class Director {
    private String LastName;
    private String FirstName;
    private String MiddleName;
    private String ImageData;

    // Геттеры
    public String getImageData() {
        return ImageData;
    }

    public String getLastName() {
        return LastName; // Фамилия
    }

    public String getFirstName() {
        return FirstName; // Имя
    }

    public String getMiddleName() {
        return MiddleName; // Отчество
    }
}

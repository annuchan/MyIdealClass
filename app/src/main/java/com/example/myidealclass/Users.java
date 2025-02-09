package com.example.myidealclass;

public class Users {
    private int Id;
    private String Login_user;
    private String Password_user;
    private int Id_Roles;
    private Integer Id_Parent; // Можно быть null
    private Integer Id_Teacher; // Можно быть null
    private Integer Id_Administration; // Можно быть null

    // Конструктор
    public Users(int Id, String Login_user, String Password_user, int Id_Roles, Integer Id_Parent, Integer Id_Teacher, Integer Id_Administration) {
        this.Id = Id;
        this.Login_user = Login_user;
        this.Password_user = Password_user;
        this.Id_Roles = Id_Roles;
        this.Id_Parent = Id_Parent;
        this.Id_Teacher = Id_Teacher;
        this.Id_Administration = Id_Administration;
    }

    // Геттеры
    public int getId() { return Id; }
    public String getLogin_user() { return Login_user.trim(); } // nchar содержит пробелы, удаляем их
    public String getPassword_user() { return Password_user.trim(); }
    public int getId_Roles() { return Id_Roles; }
    public Integer getId_Parent() { return Id_Parent; }
    public Integer getId_Teacher() { return Id_Teacher; }
    public Integer getId_Administration() { return Id_Administration; }
}


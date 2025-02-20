package com.example.myidealclass;

public class LoginResponse {
    private String message;
    private String token;
    private int userId;
    private int idParent;
    private int idTeacher;
    private int idAdministration;
    private int roleId;

    // Геттеры и сеттеры

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdAdministration() {
        return idAdministration;
    }

    public void setIdAdministration(int idAdministration) {
        this.idAdministration = idAdministration;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

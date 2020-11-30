package ru.uennar.models;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


public class Animal {
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 40, message = "Название должно содержать от двух до 40 букв" )
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше чем 0")
    private int age;

    @NotEmpty(message = "Почта не должна быть пустой")
    @Email(message = "Почта неправильная")
    private String emailNurseryMan;

    public Animal(){

    }

    public Animal(int id, String name, int age, String emailNurseryMan) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.emailNurseryMan = emailNurseryMan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailNurseryMan() {
        return emailNurseryMan;
    }

    public void setEmailNurseryMan(String emailNurseryMan) {
        this.emailNurseryMan = emailNurseryMan;
    }
}

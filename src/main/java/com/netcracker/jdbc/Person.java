package com.netcracker.jdbc;

import java.util.Random;

/**
 * Created by ivan on 27.11.16.
 */
public class Person {

    private final static Random random = new Random();

    private final long id;
    private final String name;
    private final String surname;
    private final int age;

    /**
     * Процесс создания ID пока сделан на коленке. Потом покажу как делать капитально
     */
    public Person(String name, String surname, int age) {
        this(random.nextLong(), name, surname, age);
    }

    public Person(long id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}

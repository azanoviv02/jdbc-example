package com.netcracker.jdbc;

import java.util.List;

public interface PersonRepository {
    void insert(Person person);

    List<Person> findAll();
}

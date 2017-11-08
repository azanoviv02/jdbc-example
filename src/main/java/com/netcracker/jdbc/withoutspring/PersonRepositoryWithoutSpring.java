package com.netcracker.jdbc.withoutspring;

import com.netcracker.jdbc.Person;
import com.netcracker.jdbc.PersonRepository;
import com.netcracker.jdbc.withspring.PersonRepositoryWithSpring;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Пример репозитория, НЕ использующего поддержду Spring JDBC
 */
public class PersonRepositoryWithoutSpring implements PersonRepository {

    private final DataSource dataSource;

    public PersonRepositoryWithoutSpring(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Person person) {
        String sql = "INSERT INTO PERSONS (ID, NAME, SURNAME, AGE) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());
            preparedStatement.setInt(4, person.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException();
        }
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT * FROM PERSONS";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            List<Person> personList = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String surname = rs.getString("SURNAME");
                int age = rs.getInt("AGE");

                Person person = new Person(id, name, surname, age);
                personList.add(person);
            }

            return personList;
        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException();
        }
    }
}

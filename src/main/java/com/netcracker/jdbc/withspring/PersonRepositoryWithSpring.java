package com.netcracker.jdbc.withspring;

import com.netcracker.jdbc.Person;
import com.netcracker.jdbc.PersonRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Пример репозитория, использующего поддержду Spring JDBC
 * Все обращения к базе даннхы проходят через промежуточный бин namedParameterJdbcTemplate.
 * Он сам ловит исключения и упрощает ввод запросов.
 */
public class PersonRepositoryWithSpring implements PersonRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonRepositoryWithSpring(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insert(Person person) {
        String sql = "INSERT INTO PERSONS (ID, NAME, SURNAME, AGE) VALUES (:id, :name, :surname, :age)";

        Map namedParameters = new HashMap();
        namedParameters.put("id", person.getId());
        namedParameters.put("name", person.getName());
        namedParameters.put("surname", person.getSurname());
        namedParameters.put("age", person.getAge());

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public List<Person> findAll() {

        Map<String, Object> namedParameters = new HashMap<String, Object>();

        String sql = "SELECT * FROM PERSONS";

        List<Person> result = namedParameterJdbcTemplate.query(
                sql,
                namedParameters,
                new PersonMapper());

        return result;
    }

    /**
     * Этот класс как бы "объясняет" как превратить строку из загруженной таблицы с результатов в желаемый нами объект
     */
    public static final class PersonMapper implements RowMapper<Person> {

        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

            long id = rs.getLong("ID");
            String name = rs.getString("NAME");
            String surname = rs.getString("SURNAME");
            int age = rs.getInt("AGE");

            Person person = new Person(id, name, surname, age);
            return person;
        }
    }
}

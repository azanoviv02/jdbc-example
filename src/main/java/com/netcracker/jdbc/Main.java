package com.netcracker.jdbc;

import com.netcracker.jdbc.withoutspring.PersonRepositoryWithoutSpring;
import com.netcracker.jdbc.withspring.PersonRepositoryWithSpring;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ivan on 27.11.16.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // Тут можешь выбрать какой именно репозиторий ты хочешь использовать - со спрингом или без
        PersonRepository repository = context.getBean(PersonRepositoryWithSpring.class);
//        PersonRepository repository = context.getBean(PersonRepositoryWithoutSpring.class);

        repository.insert(new Person("Danil", "Nemchenko", 20));
        repository.insert(new Person("Yaroslav", "Okunevich", 20));

        System.out.println();
        List<Person> people = repository.findAll();
        for(Person person : people){
            System.out.println(person);
        }
    }
}

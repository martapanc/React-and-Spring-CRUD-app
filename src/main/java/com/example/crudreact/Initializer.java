package com.example.crudreact;

import com.example.crudreact.model.Event;
import com.example.crudreact.model.Group;
import com.example.crudreact.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        Stream.of("Sherlock Holmes", "John Watson").forEach(name -> repository.save(new Group(name)));
        Group group = repository.findByName("Sherlock Holmes");

        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();

        group.setEvents(Collections.singleton(e));
        repository.save(group);

        repository.findAll().forEach(System.out::println);
    }
}

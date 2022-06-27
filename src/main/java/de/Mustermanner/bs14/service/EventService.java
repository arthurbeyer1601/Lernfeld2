package de.Mustermanner.bs14.service;

import de.Mustermanner.bs14.Event;
import de.Mustermanner.bs14.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getEvents() {
        return repository.findAll();
    }


    public Optional<Event> getEventById(String id) {
        return repository.findById(id);
    }

    public Event createEvent(Event Event) {
        Event newEvent = new Event();
        repository.save(newEvent);
        return newEvent;
    }


    public void deleteEvent(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Delete failed! - No Event with id " + id + " has been found!");
        }
    }
}


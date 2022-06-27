package de.Mustermanner.bs14.controller;

import de.Mustermanner.bs14.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("event")
public class EventController {

    private final EventService repository;

    @Autowired
    public EventController(EventService repository) {
        this.repository = repository;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getEvents() {
        return repository.getEvents();
    }

    @GetMapping("/getBy/{filter}")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getEvents(@PathVariable String filter) {
        return repository.getEventsByFilter(filter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Event> getEvent(@PathVariable String id) {
        try {
            return repository.getEventById(id);
        } catch (Exception e){
            System.out.println("Invalid ID");
            return null;
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event Event) {
        return repository.createEvent(Event);
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createTestEvent() {
        return repository.createTestEvent();
    }

    @PatchMapping("/changeStatus")
    @ResponseStatus(HttpStatus.OK)
    public Event changeStatusOfEvent(@RequestBody ChangeStatusRequest request) {
        return repository.changeStatusOfEvent(request.getId(),request.getNewStatus());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@PathVariable String id) {
        try {
            repository.deleteEvent(id);
        } catch (Exception e){
            System.out.println("Invalid ID");
        }
    }
}

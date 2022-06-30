package de.Mustermanner.bs14.controller;

import de.Mustermanner.bs14.Event;
import de.Mustermanner.bs14.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("event")
public class EventController {

    private final EventService repository;

    @Autowired
    public EventController(EventService repository) {
        this.repository = repository;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String getEvents(Model model) {
        model.addAttribute("events",repository.getEvents());
        model.addAttribute("newEvent", Event.builder().build());
        return "new-event";
    }

    @PostMapping("/deleteEvent")
    public String deleteConfig(
            @ModelAttribute Event event, BindingResult errors, Model model) {
        repository.deleteEvent(event.getId());
        return "redirect:/event";
    }

    @PostMapping("/createEvent")
    public String createConfig(
            @ModelAttribute Event event, BindingResult errors, Model model) {
        repository.createEvent(event);
        return "redirect:/event";
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

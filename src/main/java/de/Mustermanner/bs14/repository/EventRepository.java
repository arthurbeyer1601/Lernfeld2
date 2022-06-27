package de.Mustermanner.bs14.repository;

import de.Mustermanner.bs14.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@org.springframework.stereotype.Repository
public interface EventRepository extends MongoRepository<Event, String> {
}
package de.Mustermanner.bs14;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "event")
public class Event {

    @Id
    private String id;
    private float distance;
    private float costPerKilometer = 5;
    private float totalCost;

    public Event(float distance, float costPerKilometer) {
        this.id = UUID.randomUUID().toString();
        this.distance = distance;
        this.costPerKilometer = costPerKilometer;
        this.totalCost = distance*costPerKilometer;
    }

}

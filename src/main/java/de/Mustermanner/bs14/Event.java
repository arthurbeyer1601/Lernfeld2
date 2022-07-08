package de.Mustermanner.bs14;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event")
public class Event {

    @Id
    private String id;
    private float distance = 0;
    private float costPerKilometer = 5;
    private double totalCost;

    public Event(float distance, float costPerKilometer) {
        this.id = UUID.randomUUID().toString();
        this.distance = distance;
        this.costPerKilometer = costPerKilometer;
        this.totalCost = Math.round(distance*costPerKilometer * 100.0) / 100.0;
    }

}

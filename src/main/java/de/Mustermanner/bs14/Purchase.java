package de.Mustermanner.bs14;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Getter
@Setter
@Document(collection = "purchase")
public class Purchase {

    @Id
    String transactionId;
    String buyerId;
    String shareName;
    double amount;
    double purchasePrice;



    public Purchase(){
    }


    public Purchase(String transactionId, String buyerId, String shareName, double amount, double purchasePrice) {
        this.transactionId = transactionId;
        this.buyerId = buyerId;
        this.shareName = shareName;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
    }




}

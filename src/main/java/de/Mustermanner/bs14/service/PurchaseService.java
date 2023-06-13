package de.Mustermanner.bs14.service;

import de.Mustermanner.bs14.Purchase;
import de.Mustermanner.bs14.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

  @Autowired private PurchaseRepository repository;

  public List<Purchase> getPurchases() {
    return repository.findAll();
  }

  public Optional<Purchase> getPurchaseById(String id) {
    return repository.findById(id);
  }

  public Purchase createTestPurchase() {
    Purchase newPurchase =
        new Purchase(
            UUID.randomUUID().toString(), UUID.randomUUID().toString(), "Test GmbH", 1, 12);
    repository.save(newPurchase);
    return newPurchase;
  }

  public Purchase createPurchase(Purchase purchase) {
    Purchase newPurchase =
        new Purchase(UUID.randomUUID().toString(),UUID.randomUUID().toString(),purchase.getShareName(), purchase.getPurchasePrice(), purchase.getAmount());
    repository.save(newPurchase);
    return newPurchase;
  }

  public void deletePurchase(String id) {
    try {
      repository.deleteById(id);
      System.out.println("Deleted " + id);
    } catch (Exception e) {
      System.out.println("Delete failed! - No Purchase with id " + id + " has been found!");
    }
  }
}

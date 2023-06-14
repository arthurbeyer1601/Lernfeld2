package de.Mustermanner.bs14.controller;

import de.Mustermanner.bs14.Purchase;
import de.Mustermanner.bs14.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("purchase")
public class PurchaseController {

  private final PurchaseService repository;

  @Autowired
  public PurchaseController(PurchaseService repository) {
    this.repository = repository;
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public String getPurchase(Model model) {
    model.addAttribute("purchases", repository.getPurchases());
    model.addAttribute("newPurchase", new Purchase());
    return "new-purchase";
  }

  @PostMapping("/deletePurchase")
  public String deletePurchase(@RequestParam String id) {
    repository.deletePurchase(id);
    return "redirect:/purchase";
  }

  @PostMapping("/createPurchase")
  public String createPurchase(
      @ModelAttribute Purchase purchase, BindingResult errors, Model model) {
    purchase.setShareName(purchase.getShareName().toUpperCase().replace(".","").replace(" ","_"));
    repository.createPurchase(purchase);
    return "redirect:/purchase";
  }

  @GetMapping("/buyer")
  public String getPurchasesFromBuyer(@RequestParam("id") String id, Model model) {
    List<Purchase> purchases = repository.getPurchases();
    List<Purchase> purchasesWithBuyerId = new ArrayList<>();
    System.out.println(id);
    for (Purchase p : purchases) {
      if (p.getBuyerId().equals(id)) {
        purchasesWithBuyerId.add(p);
      }
    }
    model.addAttribute("purchases", purchasesWithBuyerId);
    return "specific-purchase";
  }

  @GetMapping("/sharename")
  public String getPurchasesFromShareName(@RequestParam("name") String id, Model model) {
    List<Purchase> purchases = repository.getPurchases();
    List<Purchase> purchasesWithShareName = new ArrayList<>();
    System.out.println(id);
    for (Purchase p : purchases) {
      if (p.getShareName().equalsIgnoreCase(id)) {
        purchasesWithShareName.add(p);
      }
    }
    model.addAttribute("purchases", purchasesWithShareName);
    return "specific-purchase";
  }

  @GetMapping("/transaction")
  public String getPurchasesFromTransactionsId(@RequestParam("id") String id, Model model) {
    List<Purchase> purchases = repository.getPurchases();
    List<Purchase> purchasesWithTransactionsId = new ArrayList<>();
    System.out.println(id);
    for (Purchase p : purchases) {
      if (p.getTransactionId().equals(id)) {
        purchasesWithTransactionsId.add(p);
      }
    }
    model.addAttribute("purchases", purchasesWithTransactionsId);
    return "specific-purchase";
  }

}

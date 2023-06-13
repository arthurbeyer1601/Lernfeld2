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
        model.addAttribute("purchases",repository.getPurchases());
        model.addAttribute("newPurchase", new Purchase());
        return "new-purchase";
    }

    @PostMapping("/deletePurchase")
    public String deletePurchase(
            @RequestParam String id) {
        repository.deletePurchase(id);
        return "redirect:/purchase";
    }

    @PostMapping("/createPurchase")
    public String createPurchase(
            @ModelAttribute Purchase Purchase, BindingResult errors, Model model) {
        repository.createPurchase(Purchase);
        return "redirect:/purchase";
    }

    @GetMapping("/getPurchase/buyer/{buyerId}")
    public String getPurchasesFromBuyer(
            @PathVariable String buyerId, BindingResult errors, Model model) {
        List<Purchase> purchases = repository.getPurchases();
        List<Purchase> purchasesWithBuyerId = new ArrayList<>();
        for(Purchase p : purchases){
            if(p.getBuyerId().equals(buyerId)){
                purchasesWithBuyerId.add(p);
            }
        }
        return "redirect:/purchase";
    }

}

package com.kr.ecomarket.controllers;

import com.kr.ecomarket.models.Basket;
import com.kr.ecomarket.models.User;
import com.kr.ecomarket.service.BasketService;
import com.kr.ecomarket.service.ItemService;
import com.kr.ecomarket.service.CategoryService;
import com.kr.ecomarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class BasketController {

    @Autowired
    private ItemService ItemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;


    private int getBasketPrice(List<Basket> purchases) {
        int total = 0;
        for (Basket basket: purchases){
            total += ItemService.getItemById(basket.getItemId()).getPrice() * basket.getItemCount();
        }
        return total;
    }

    @GetMapping("/basket")
    public String basket(Model model,
                         @AuthenticationPrincipal User user){

        int userId = user.getId();
        model.addAttribute("basketPrice", getBasketPrice(basketService.getPurchasesByUserId(userId)));
        model.addAttribute("category", categoryService.getAllCategory());

        List<Basket> purchases = basketService.getPurchasesByUserId(userId);

        model.addAttribute("basket", purchases);
        model.addAttribute("ItemService", ItemService);

        return "basket";
    }

    @PostMapping("/basketDeletePurchase")
    public String deletePurchase(@RequestParam(value = "delButton") int purchaseId){
        basketService.deletePurchaseById(purchaseId);
        return "redirect:/basket";
    }

    @PostMapping("/basketIncrPurchase")
    public String increasePurchase(@RequestParam(value = "incrButton") int purchaseId){
        Basket purchase = basketService.getPurchaseById(purchaseId);
        purchase.setItemCount(purchase.getItemCount() + 1);
        basketService.savePurchase(purchase);
        return "redirect:/basket";
    }

    @PostMapping("/basketDecrPurchase")
    public String decreasePurchase(@RequestParam(value = "decrButton") int purchaseId){
        Basket purchase = basketService.getPurchaseById(purchaseId);
        purchase.setItemCount(purchase.getItemCount() - 1);
        basketService.savePurchase(purchase);
        if (purchase.getItemCount() <= 0){
            basketService.deletePurchase(purchase);
        }
        return "redirect:/basket";
    }

}
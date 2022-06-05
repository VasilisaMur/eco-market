package com.kr.ecomarket.controllers;

import com.kr.ecomarket.models.Item;
import com.kr.ecomarket.models.Role;
import com.kr.ecomarket.models.User;
import com.kr.ecomarket.models.Basket;
import com.kr.ecomarket.repo.ItemRepo;
import com.kr.ecomarket.service.BasketService;
import com.kr.ecomarket.service.ItemService;
import com.kr.ecomarket.service.CategoryService;
import com.kr.ecomarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private ItemRepo ItemRepo;

    @Autowired
    private ItemService ItemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;


    @GetMapping("/")
    public String mainPage(
            @AuthenticationPrincipal User user,
            Authentication authentication,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            Model model) {

        String auth = user.getAuthorities().toString();


        model.addAttribute("authority", auth);
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("title", "Main page");
        Iterable<Item> Item = ItemRepo.findAll();
        model.addAttribute("Item", Item);


        if (categoryId == null) {
//            model.addAttribute("products", products);
            model.addAttribute("Item", Item);
        }
        else{
            model.addAttribute("Item", ItemService.getAllItemsByCategoryId(categoryId));
        }

        return "index";
    }

    @Controller
    public class ItemsController {

        @GetMapping("/page/{id}")
        public String ItemPage(
                @AuthenticationPrincipal User user,
                @PathVariable(value="id") int id,
                Model model) {
            model.addAttribute("itemDesc", "itemDesc");

            Item item = ItemService.getItemById(id);

            model.addAttribute("item", item);
            model.addAttribute("itemId", id);
            model.addAttribute("item_category", categoryService.getCategoryById(item.getСategoryId()));
            model.addAttribute("category", categoryService.getAllCategory());

            Optional<Item> Item = Optional.ofNullable(ItemRepo.findById(id));
            ArrayList<Item> res = new ArrayList<>();
            Item.ifPresent(res::add);
            model.addAttribute("Item", res);

            return "item-desc";
        }

        @GetMapping("/add")
        public String itemAddForm(Model model){
            return "itemAdd";
        }

        @PostMapping("/add")
        public String itemSave(@RequestParam String item_name, @RequestParam String item_desc,
                               @RequestParam String cover_link, @RequestParam int price,
                               @RequestParam int category_id, Model model){
            Item item = new Item(
                    item_name,
                    item_desc,
                    cover_link,
                    price,
                    category_id);

            ItemRepo.save(item);

            return "redirect:/";
        }
    }


    private int getUserId(Authentication authentication) {
        if (authentication == null)
            return 0;
        else
            return ((User)userService.loadUserByUsername(authentication.getName())).getId();
    }


    @PostMapping("/page/{id}")
    public String addItemToBasket(
            Authentication authentication,
            @PathVariable(value = "id") int itemId,
            Model model
    ) {
        int userId = getUserId(authentication);
        Basket basket = basketService.getPurchaseByUserIdAndItemId(userId, itemId);
        if (basket == null){
            Basket newBasket = new Basket();
            newBasket.setUserId(userId);
            newBasket.setItemId(itemId);
            newBasket.setItemCount(1);
            basketService.savePurchase(newBasket);
            return "redirect:/";
        }
        else{
            basket.setItemCount(basket.getItemCount() + 1);
            basketService.savePurchase(basket);
            return "redirect:/";
        }
    }
}
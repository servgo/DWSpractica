package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

    @Autowired
    GameService gameService;
    ShoppingCartService shoppingCartService;

    //Once a product is added to cart, we'll be redirected to a shown message telling us if it's been successfully done
    @RequestMapping("/AddToCart")
    public String addToCart(Model model, @RequestParam int id) {
        shoppingCartService.add(this.service.games.get(id));
        return "AddedToCart.html";
    }

    //It'll show us our shopping cart or a message telling us it's empty otherwise
    @RequestMapping("/ShoppingCart")
    public String showShoppingCart(Model model) {
        model.addAttribute("cart", service.shoppingCart);
        if (service.shoppingCart.isEmpty()) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("empty", false);
        }
        int sum = 0;
        for (Game game : service.shoppingCart) {
            sum += game.getPrice();
        }
        model.addAttribute("sum", sum);
        return "ShoppingCart";
    }

    //We can remove a game from our shopping cart if we regret our decision of buying it
    @RequestMapping("/RemoveGame")
    public String removeGameShoppingCart(Model model, @RequestParam int id) {
        service.shoppingCart.removeIf(game -> id == game.getId());
        return "RemovedGame.html";
    }

    //We can delete all items from our shopping cart
    @RequestMapping("/RemoveShoppingCart")
    public String removeShoppingCart(Model model) {
        service.shoppingCart.clear();
        return "RemovedShoppingCart.html";
    }


}

package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {
    @Autowired
    GameService gameService;
    ShoppingCart shoppingCart;

    @GetMapping("/addedToCart")
    public String newGameToCart(Model model, @RequestParam int id) {
        shoppingCart.addGame(this.gameService.getGames(id));
        return "AddedToCart";
    }

    @RequestMapping("/ShoppingCart")
    public String showShoppingCart(Model model) {

        model.addAttribute("cart", shoppingCart);

        if (shoppingCart.esVacia()) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("empty", false);
        }

        float sum = shoppingCart.getPrecio();

        model.addAttribute("sum", sum);
        return "ShoppingCart";
    }

    @RequestMapping("/RemoveGame")
    public String removeGameShoppingCart(Model model,@RequestParam Game game) {
        shoppingCart.deleteGame(game);
        return "RemovedGame.html";
    }

    @RequestMapping("/RemoveShoppingCart")
    public String removeShoppingCart(Model model) {
        shoppingCart.clearCart();
        return "RemovedShoppingCart.html";
    }
}



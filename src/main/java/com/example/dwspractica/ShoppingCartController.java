package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ShoppingCartController {

    @Autowired
    GameService gameService;
    @Autowired
    ShoppingCart shoppingCart;
    @Autowired
    UserService userService;

    @GetMapping("/addedToCart/{idGame}")
    public String newGameToCart(@PathVariable int idGame) {
        shoppingCart.addGame(gameService.getGames(idGame));
        return "AddedToCart";
    }

    @GetMapping("/shoppingCart")
    public String showShoppingCart(Model model) {

        model.addAttribute("cart", shoppingCart.getCart());
        float sum = shoppingCart.getPrecio();

        model.addAttribute("sum", sum);
        return "ShoppingCart";
    }

    @GetMapping("/RemoveGame/{idGame}")
    public String removeGameShoppingCart(@PathVariable int idGame) {
        shoppingCart.deleteGame(idGame);
        return "RemovedGame";
    }

    @GetMapping("/RemoveShoppingCart")
    public String removeShoppingCart(Model model) {
        shoppingCart.clearCart();
        return "RemovedShoppingCart";
    }

    @GetMapping("/MadeOrder")
    public String madeOrder(){
        shoppingCart.makeOrder(1);
        shoppingCart.clearCart();
        return "CreatedOrder";
    }
    @GetMapping("/showOrders")
    public String showOrders(Model model){
        model.addAttribute("orders", shoppingCart.showOrders(1));
        model.addAttribute("sum", userService.getUsers(1).getPriceOrders());
        return "MyOrders";
    }
    @GetMapping("/deleteOrder/{idGame}")
    public String deleteOrder(@PathVariable int idGame){
        userService.deleteOrder(idGame);
        return "DeletedOrder";
    }
}



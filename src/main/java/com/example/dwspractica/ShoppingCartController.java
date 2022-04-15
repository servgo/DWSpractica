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
        if (gameService.containsGame(idGame)){
            shoppingCart.addGame(gameService.getGames(idGame));
            return "AddedToCart";
        }else{
            return "error/404";
        }
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
        if (gameService.containsGame(idGame)){
            if (shoppingCart.containsGame(idGame)){
                shoppingCart.deleteGame(idGame);
                return "RemovedGame";
            }else{
                return "error/404";
            }
        }else{
            return "error/404";
        }
    }

    @GetMapping("/RemoveShoppingCart")
    public String removeShoppingCart(Model model) {
        shoppingCart.clearCart();
        return "RemovedShoppingCart";
    }

    @GetMapping("/MadeOrder")
    public String madeOrder(){
        userService.makeOrder(2);
        shoppingCart.clearCart();
        return "CreatedOrder";
    }
    @GetMapping("/showOrders")
    public String showOrders(Model model){
        model.addAttribute("orders", userService.showOrders(2));
        model.addAttribute("sum", userService.getUsers(2).getPriceOrders());
        return "MyOrders";
    }
    @GetMapping("/deleteOrder/{idGame}")
    public String deleteOrder(@PathVariable int idGame){
        userService.deleteOrder(2, idGame);
        return "DeletedOrder";
    }
}



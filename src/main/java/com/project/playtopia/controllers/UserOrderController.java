package com.project.playtopia.controllers;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.Game;
import com.project.playtopia.models.UserOrder;
import com.project.playtopia.service.AppUserService;
import com.project.playtopia.service.GameService;
import com.project.playtopia.service.UserOrderService;
import javassist.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserOrderController {
    UserOrderService userOrderService;
    AppUserService appUserService;
    GameService gameService;

    public UserOrderController(UserOrderService userOrderService, AppUserService appUserService, GameService gameService) {
        this.userOrderService = userOrderService;
        this.appUserService = appUserService;
        this.gameService = gameService;
    }
    @PostMapping("/add-game-to-cart")

    public String addGameToCart(@RequestParam("game_id") Long gameId, @RequestParam(value = "quantity", defaultValue = "1") int quantity, Model model) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userOrderService.addGameToCart(gameId, username, quantity);

        GameDto basketGames = gameService.findGameById(gameId);
        model.addAttribute("basketGames", basketGames );
        return "redirect:/games";
    }


    @GetMapping("/basket")
    public String goToBasket(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<AppUser> userOpt = appUserService.findUserByUsername(username);
        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            List<UserOrder> userOrders = user.getUserOrders();
            if (!userOrders.isEmpty()) {
                UserOrder order = userOrders.get(0);
                Double orderPrice = order.getPrice();
                List<Game> basketContent = order.getBasketContent();
                if (!basketContent.isEmpty()) {
                    model.addAttribute("basketContent", basketContent);
                    model.addAttribute("orderPrice", orderPrice);
                    model.addAttribute("order", order);
                }
            }
        }
        return "user-basket";
    }

    @PostMapping("/delete-game-from-basket")
    public String deleteGameFromBasket(@RequestParam("game_id") Long game_id,@RequestParam("order_id") Long order_id){
        userOrderService.deleteGameFromCart(game_id, order_id);
        return "redirect:/basket";
    }

}

package com.project.playtopia.service;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.Game;
import com.project.playtopia.models.UserOrder;
import com.project.playtopia.repository.AppUserRepository;
import com.project.playtopia.repository.GameRepository;
import com.project.playtopia.repository.UserOrderRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrderService {
    UserOrderRepository userOrderRepository;
    GameRepository gameRepository;
    AppUserRepository appUserRepository;

    public UserOrderService(UserOrderRepository userOrderRepository, GameRepository gameRepository, AppUserRepository appUserRepository) {
        this.userOrderRepository = userOrderRepository;
        this.gameRepository = gameRepository;
        this.appUserRepository = appUserRepository;
    }

    public void addGameToCart(Long gameId, String username, int quantity) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        Optional<AppUser> optionalUser = appUserRepository.findByUsername(username);

        if (optionalGame.isPresent() && optionalUser.isPresent()) {
            Game game = optionalGame.get();
            AppUser user = optionalUser.get();

            Optional<UserOrder> optionalOrder = userOrderRepository.findByOrderOwner(user);
            UserOrder order;

            if (optionalOrder.isPresent()) {
                order = optionalOrder.get();
                for (int i = 0; i < quantity; i++) {
                    order.getBasketContent().add(game);
                }
                double totalPrice = order.getPrice() + (quantity * game.getPrice());
                order.setPrice(totalPrice);
            } else {
                order = new UserOrder();
                order.setOrderOwner(user);
                order.setPrice(quantity * game.getPrice());
                order.setBasketContent(new ArrayList<>());
                for (int i = 0; i < quantity; i++) {
                    order.getBasketContent().add(game);
                }
            }

            userOrderRepository.save(order);
        }
    }



    public void deleteGameFromCart(Long gameId, Long orderId) {
        UserOrder order = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        order.getBasketContent().remove(game);

        double newPrice = order.getBasketContent().stream().mapToDouble(Game::getPrice).sum();
        order.setPrice(newPrice);

        if (order.getBasketContent().isEmpty()) {
            userOrderRepository.delete(order);
        } else {
            userOrderRepository.save(order);
        }
    }

}


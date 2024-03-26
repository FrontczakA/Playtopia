package com.project.playtopia.service;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.ConfirmedUserOrder;
import com.project.playtopia.models.Game;
import com.project.playtopia.models.UserOrder;
import com.project.playtopia.repository.AppUserRepository;
import com.project.playtopia.repository.ConfirmedUserOrderRepository;
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
    ConfirmedUserOrderRepository confirmedUserOrderRepository;

    public UserOrderService(UserOrderRepository userOrderRepository, GameRepository gameRepository, AppUserRepository appUserRepository, ConfirmedUserOrderRepository confirmedUserOrderRepository) {
        this.userOrderRepository = userOrderRepository;
        this.gameRepository = gameRepository;
        this.appUserRepository = appUserRepository;
        this.confirmedUserOrderRepository = confirmedUserOrderRepository;
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

    public void confirmOrder(Long orderId, String deliveryMethod) {
        Optional<UserOrder> optionalUserOrder = userOrderRepository.findById(orderId);
        if (optionalUserOrder.isPresent()) {
            UserOrder userOrder = optionalUserOrder.get();

            if (deliveryMethod == null || deliveryMethod.isEmpty()) {
                throw new IllegalArgumentException("Please select a delivery method.");
            }

            ConfirmedUserOrder confirmedOrder = new ConfirmedUserOrder();
            confirmedOrder.setPrice(userOrder.getPrice());
            confirmedOrder.setOrderOwner(userOrder.getOrderOwner());
            confirmedOrder.setDeliveryMethod(deliveryMethod);

            List<Game> orderedGamesClone = new ArrayList<>(userOrder.getBasketContent());
            confirmedOrder.setOrderedGames(orderedGamesClone);

            confirmedOrder = confirmedUserOrderRepository.save(confirmedOrder);

            userOrder.getBasketContent().clear();
            userOrder.setPrice(0.0);
            userOrderRepository.save(userOrder);
        }
    }




    public List<ConfirmedUserOrder> getOrderHistory(String username) {
        Optional<AppUser> userOpt = appUserRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            ConfirmedUserOrder order = confirmedUserOrderRepository.findByOrderOwner(user).orElse(null);
            if (order != null) {
                return Collections.singletonList(order);
            }
        }
        return Collections.emptyList();
    }

}



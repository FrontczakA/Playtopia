package com.project.playtopia.test;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.Game;
import com.project.playtopia.models.UserOrder;
import com.project.playtopia.repository.AppUserRepository;
import com.project.playtopia.repository.ConfirmedUserOrderRepository;
import com.project.playtopia.repository.GameRepository;
import com.project.playtopia.repository.UserOrderRepository;
import com.project.playtopia.service.UserOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserOrderServiceTest {

    @Mock
    private UserOrderRepository userOrderRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private ConfirmedUserOrderRepository confirmedUserOrderRepository;

    @InjectMocks
    private UserOrderService userOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddGameToCart() {
        Long gameId = 1L;
        String username = "testUser";
        int quantity = 2;

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(new Game()));
        when(appUserRepository.findByUsername(username)).thenReturn(Optional.of(new AppUser()));
        when(userOrderRepository.findByOrderOwner(any())).thenReturn(Optional.of(new UserOrder()));

        userOrderService.addGameToCart(gameId, username, quantity);

        verify(userOrderRepository).save(any());
    }

    @Test
    void testDeleteGameFromCart() {
        Long gameId = 1L;
        Long orderId = 1L;

        when(userOrderRepository.findById(orderId)).thenReturn(Optional.of(new UserOrder()));
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(new Game()));

        userOrderService.deleteGameFromCart(gameId, orderId);

        verify(userOrderRepository).findById(orderId);
        verify(gameRepository).findById(gameId);
        verify(userOrderRepository).save(any());
    }
}

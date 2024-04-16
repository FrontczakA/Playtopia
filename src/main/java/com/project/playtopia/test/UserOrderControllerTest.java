package com.project.playtopia.test;

import com.project.playtopia.controllers.UserOrderController;
import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.UserOrder;
import com.project.playtopia.service.AppUserService;
import com.project.playtopia.service.GameService;
import com.project.playtopia.service.UserOrderService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserOrderControllerTest {

    @Mock
    private UserOrderService userOrderService;

    @Mock
    private AppUserService appUserService;

    @Mock
    private GameService gameService;

    @Mock
    private Model model;

    @InjectMocks
    private UserOrderController userOrderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddGameToCart() throws NotFoundException {
        Long gameId = 1L;
        int quantity = 2;

        userOrderController.addGameToCart(gameId, quantity, model);

        verify(userOrderService).addGameToCart(gameId, anyString(), quantity);
        verify(model).addAttribute(eq("basketGames"), any());
    }

    @Test
    void testGoToBasket() {
        AppUser user = new AppUser();
        user.setUsername("testUser");
        UserOrder order = new UserOrder();
        List<UserOrder> userOrders = new ArrayList<>();
        userOrders.add(order);
        user.setUserOrders(userOrders);
        when(appUserService.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        userOrderController.goToBasket(model);

        verify(model).addAttribute(eq("basketContent"), any());
        verify(model).addAttribute(eq("orderPrice"), any());
        verify(model).addAttribute(eq("order"), any());
        verify(model).addAttribute(eq("user"), any());
    }

}

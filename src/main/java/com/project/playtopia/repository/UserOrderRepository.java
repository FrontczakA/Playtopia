package com.project.playtopia.repository;

import com.project.playtopia.models.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.playtopia.models.UserOrder;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    Optional<UserOrder> findByOrderOwner(AppUser orderOwner);

}
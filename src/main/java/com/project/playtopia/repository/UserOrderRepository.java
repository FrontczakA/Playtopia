package com.project.playtopia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.playtopia.models.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}
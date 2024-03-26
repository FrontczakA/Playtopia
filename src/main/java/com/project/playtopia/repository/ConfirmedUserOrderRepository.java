package com.project.playtopia.repository;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.ConfirmedUserOrder;
import com.project.playtopia.models.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfirmedUserOrderRepository extends JpaRepository<ConfirmedUserOrder, Long> {
    Optional<ConfirmedUserOrder> findByOrderOwner(AppUser orderOwner);
    List<ConfirmedUserOrder> findAllByOrderOwner(AppUser orderOwner);

}
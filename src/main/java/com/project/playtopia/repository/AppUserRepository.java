package com.project.playtopia.repository;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}

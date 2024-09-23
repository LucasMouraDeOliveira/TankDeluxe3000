package com.luma.tankdeluxe.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luma.tankdeluxe.entity.PlayerLevel;

public interface PlayerLevelRepository extends JpaRepository<PlayerLevel, Long> {

    @Query("SELECT playerLevel from PlayerLevel playerLevel, User user "
            + "WHERE user.playerLevel = playerLevel "
            + "AND user.login = :username")
    public Optional<PlayerLevel> findByUsername(String username);

}

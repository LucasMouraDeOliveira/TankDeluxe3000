package com.luma.tankdeluxe.service.user;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.entity.PlayerLevel;
import com.luma.tankdeluxe.repository.PlayerLevelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerLevelService {

    private static final double LEVEL_X_FACTOR = 0.15;

    private static final double LEVEL_Y_FACTOR = 2;

    private final PlayerLevelRepository playerLevelRepository;

    public PlayerLevel create() {
        return PlayerLevel.builder()
                .currentXP(0)
                .nextLevelXP(this.calculateNextXp(1))
                .level(1)
                .build();
    }

    public void addXp(String username, long xpAmount) {
        Optional<PlayerLevel> optLevel = this.playerLevelRepository.findByUsername(username);
        if (optLevel.isEmpty()) {
            return;
        }

        PlayerLevel player = optLevel.get();

        long nextXp = player.getCurrentXP() + xpAmount;
        player.setCurrentXP(nextXp);

        if (nextXp > player.getNextLevelXP()) {
            this.upgradePlayerLevel(player, nextXp);
        }

        this.playerLevelRepository.save(player);
    }

    private void upgradePlayerLevel(PlayerLevel player, long nextXp) {
        player.setCurrentXP(nextXp - player.getNextLevelXP());
        player.setLevel(player.getLevel() + 1);
        player.setNextLevelXP(this.calculateNextXp(player.getLevel()));
    }

    private int calculateNextXp(int level) {
        return (int) (Math.floor(Math.pow(level / LEVEL_X_FACTOR, LEVEL_Y_FACTOR)));
    }

}

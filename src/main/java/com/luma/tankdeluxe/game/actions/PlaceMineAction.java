package com.luma.tankdeluxe.game.actions;

import java.util.List;
import java.util.stream.Collectors;

import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.Mine;
import com.luma.tankdeluxe.game.player.Miner;

public class PlaceMineAction extends GameUpdate {

    public PlaceMineAction(GameServer gameServer) {
        super(gameServer);
    }

    @Override
    public void act(int delta) {
        getActingMiners().forEach(miner -> {
            gameServer.addMine(new Mine(miner));
            miner.setMineCount(0);
        });
    }

    private List<Miner> getActingMiners() {
        return gameServer.getPlayers().stream()
                .filter(Miner.class::isInstance).map(Miner.class::cast)
                .filter(this::isPlacingMine)
                .collect(Collectors.toList());
    }
    
    private boolean isPlacingMine(Miner miner) {
        return miner.isAlive() && !miner.isInvincible() && miner.isPlacingMine() && miner.getMineCount() > 0;
    }

}

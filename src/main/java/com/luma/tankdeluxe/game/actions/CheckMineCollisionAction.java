package com.luma.tankdeluxe.game.actions;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.dyn4j.geometry.Vector2;

import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.Mine;
import com.luma.tankdeluxe.game.player.Player;

public class CheckMineCollisionAction extends GameUpdate {

    public CheckMineCollisionAction(GameServer gameServer) {
        super(gameServer);
    }

    @Override
    public void act(int delta) {
        
        gameServer.getMines().removeIf(Mine::isBlown);
        
        for(Player player : killablePlayers()) {
            for(Mine mine : gameServer.getMines()) {
                if(mine.getMiner().equals(player))
                    continue;
                if(player.contains(new Vector2(mine.getX(), mine.getY())))
                    blow(mine, player);
            }
        }
    }

    private List<Player> killablePlayers() {
       return gameServer.getPlayers().stream()
               .filter(Player::isAlive)
               .filter(Predicate.not(Player::isInvincible))
               .collect(Collectors.toList());
    }
    
    private void blow(Mine mine, Player player) {
        mine.getMiner().setMineCount(1);
        mine.setBlown(true);
        
        // If player has shield, do nothing except decrease shield count
        if(player.getNbShield() > 0) {
            player.removeOneShield();
        } else if(!player.isInvincible()){
            this.gameServer.killPlayer(player);
            this.gameServer.getGameScore().increaseScore(mine.getMiner());
        }
    }

}

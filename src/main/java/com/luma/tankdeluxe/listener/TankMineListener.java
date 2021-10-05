package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.Mine;
import com.luma.tankdeluxe.game.player.Player;

public class TankMineListener extends ContactAdapter {
    
    private GameServer gameServer;
    
    public TankMineListener(GameServer gs) {
        this.gameServer = gs;
    } 

    @Override
    public boolean begin(ContactPoint point) {
        
        Player player;
        Mine mine;
        
        if(point.getBody1() instanceof Mine && point.getBody2() instanceof Player) {
            mine = (Mine)point.getBody1();
            player = (Player)point.getBody2();
        } else if(point.getBody2() instanceof Mine && point.getBody1() instanceof Player) {
            player = (Player)point.getBody1();
            mine = (Mine)point.getBody2();
        } else {
            return super.begin(point);
        }
            
        if(canKill(mine, player)) {
            blow(mine, player);
        }
        
        return super.begin(point);
    }
    
    private void blow(Mine mine, Player player) {
        mine.getMiner().setMineCount(1);
        this.gameServer.removeMine(mine);
        
        // If player has shield, do nothing except decrease shield count
        if(player.getNbShield() > 0) {
            player.removeOneShield();
        } else if(!player.isInvincible()){
            this.gameServer.killPlayer(player);
            this.gameServer.getGameScore().increaseScore(mine.getMiner());
        }
    }
    
    private boolean canKill(Mine mine, Player player) {
        /*if(mine.getMiner().equals(player))
            return false;*/
        return player.isAlive() && !player.isInvincible();
    }
    
}

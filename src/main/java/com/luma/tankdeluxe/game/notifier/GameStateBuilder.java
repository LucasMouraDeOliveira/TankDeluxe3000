package com.luma.tankdeluxe.game.notifier;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.MapEvent;
import com.luma.tankdeluxe.game.Mine;
import com.luma.tankdeluxe.game.level.Cell;
import com.luma.tankdeluxe.game.level.Layout;
import com.luma.tankdeluxe.game.player.Player;

@Component
public class GameStateBuilder {

    private GameStateBuilder() {
        // Private constructor
    }

    public static String getMap(GameServer server) {
        JSONArray jsonCells = new JSONArray();
        for (Layout l : server.getLevel().getLayouts()) {
            for (Cell cell : l.getCells()) {
                JSONObject jsonCell = new JSONObject();
                jsonCell.put("x", cell.getX());
                jsonCell.put("y", cell.getY());
                jsonCell.put("code", cell.getCode());
                jsonCells.put(jsonCell);
            }
        }

        JSONObject gameData = new JSONObject();
        gameData.put("walls", jsonCells);
        gameData.put("width", server.getLevel().getWidth());
        gameData.put("height", server.getLevel().getHeight());

        return gameData.toString();
    }

    public static JSONArray getPlayers(GameServer server) {
        JSONArray jsonPlayers = new JSONArray();
        for (Player player : server.getPlayers()) {
            JSONObject jsonPlayer = new JSONObject();
            jsonPlayer.put("id", player.getId().toString());
            jsonPlayer.put("x", player.getX() * SettingsManager.SIZE_RATIO);
            jsonPlayer.put("y", player.getY() * SettingsManager.SIZE_RATIO);
            jsonPlayer.put("angle", player.getAngle());
            jsonPlayer.put("turretAngle", player.getTurretAngle());
            jsonPlayer.put("nbShield", player.getNbShield());
            jsonPlayer.put("color", player.getColor());
            jsonPlayer.put("alive", player.isAlive());
            jsonPlayer.put("invincible", player.isInvincible());
            jsonPlayer.put("name", player.getName());
            jsonPlayer.put("shooting", player.isShooting());
            jsonPlayer.put("charge", player.getCharge());
            jsonPlayer.put("health", player.getHealth());
            jsonPlayers.put(jsonPlayer);
        }
        return jsonPlayers;
    }

    public static JSONArray getBullets(GameServer server) {
        JSONArray jsonBullets = new JSONArray();
        for (Bullet bullet : server.getBullets()) {
            JSONObject jsonBullet = new JSONObject();
            jsonBullet.put("x", bullet.getX() * SettingsManager.SIZE_RATIO);
            jsonBullet.put("y", bullet.getY() * SettingsManager.SIZE_RATIO);
            jsonBullet.put("direction", bullet.getLinearVelocity().getDirection());
            jsonBullets.put(jsonBullet);
        }
        return jsonBullets;
    }

    public static JSONArray getMines(GameServer server) {
        JSONArray jsonMines = new JSONArray();
        for (Mine mine : server.getMines()) {
            JSONObject jsonMine = new JSONObject();
            jsonMine.put("x", mine.getX() * SettingsManager.SIZE_RATIO);
            jsonMine.put("y", mine.getY() * SettingsManager.SIZE_RATIO);
            jsonMines.put(jsonMine);
        }
        return jsonMines;
    }

    public static JSONObject getScore(GameServer server) {
        JSONObject jsonScore = new JSONObject();

        JSONArray jsonPlayerScores = new JSONArray();
        for (Map.Entry<Player, Integer> entry : server.getGameScore().getScores().entrySet()) {
            JSONObject jsonPlayerScore = new JSONObject();
            jsonPlayerScore.put("name", entry.getKey().getName());
            jsonPlayerScore.put("score", entry.getValue());
            jsonPlayerScores.put(jsonPlayerScore);
        }
        jsonScore.put("players", jsonPlayerScores);

        Player bestPlayer = server.getGameScore().getBestPlayer();
        if (bestPlayer != null) {
            jsonScore.put("bestPlayer", bestPlayer.getName());
            jsonScore.put("bestScore", server.getGameScore().getAllTimeHighScore());
        }
        return jsonScore;
    }

    public static JSONArray getMapEvents(GameServer server) {
        JSONArray mapEvents = new JSONArray();
        for (MapEvent event : server.getMapEvents()) {
            JSONObject mapEvent = new JSONObject();
            mapEvent.put("x", event.x());
            mapEvent.put("y", event.y());
            mapEvent.put("code", event.code());
            mapEvents.put(mapEvent);
        }
        return mapEvents;
    }

}

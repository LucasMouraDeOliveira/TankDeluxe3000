package com.luma.tankdeluxe.game.notifier;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.luma.tankdeluxe.game.GameServer;

@Component
public class GameStateNotifier {

    private GameStateNotifier() {
        // Private constructor
    }

    public static void notifyPlayers(GameServer server) {

        JSONArray jsonPlayers = GameStateBuilder.getPlayers(server);

        JSONObject gameData = new JSONObject();
        gameData.put("players", jsonPlayers);
        gameData.put("bullets", GameStateBuilder.getBullets(server));
        gameData.put("mines", GameStateBuilder.getMines(server));
        gameData.put("scores", GameStateBuilder.getScore(server));
        gameData.put("mapEvents", GameStateBuilder.getMapEvents(server));

        // TODO find a better place for this
        server.clearMapEvents();

        server.getPlayers().parallelStream().filter(player -> player.getSession() != null).forEach(player -> {
            for (int i = 0; i < jsonPlayers.length(); i++) {
                JSONObject playerJson = jsonPlayers.getJSONObject(i);
                playerJson.put("self", playerJson.getString("name").equals(player.getName()));
            }

            sendWsMessage(gameData.toString(), player.getSession());
        });
    }

    private static void sendWsMessage(String gameData, WebSocketSession session) {
        new Thread(() -> {
            try {
                session.sendMessage(new TextMessage(gameData));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

package com.luma.tankdeluxe.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.dyn4j.dynamics.Body;
import org.dyn4j.world.PhysicsWorld;
import org.dyn4j.world.World;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.dto.AimDTO;
import com.luma.tankdeluxe.dto.PlayerActionDTO;
import com.luma.tankdeluxe.game.level.Cell;
import com.luma.tankdeluxe.game.level.Coordinate;
import com.luma.tankdeluxe.game.level.Level;
import com.luma.tankdeluxe.game.player.Player;
import com.luma.tankdeluxe.listener.BulletBulletListener;
import com.luma.tankdeluxe.listener.BulletDestructibleListener;
import com.luma.tankdeluxe.listener.BulletWallListener;
import com.luma.tankdeluxe.listener.TankBulletListener;
import com.luma.tankdeluxe.service.LeaderboardService;
import com.luma.tankdeluxe.service.PlayerService;

public class GameServer {

	private UUID id;
	private String name;
	private World<Body> world;
	private Random random;
	private Level level;

	private Map<UUID, Player> players;
	private Map<Player, PlayerActionDTO> playerActions;

	private List<Bullet> bullets;
	private List<Mine> mines;

	private List<MapEvent> mapEvents;

	private List<Color> availableColor;

	private PlayerService playerService;

	private GameScore gameScore;

	public GameServer(String name, Level level, PlayerService playerService, LeaderboardService leaderboardService) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.level = level;
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.bullets = new ArrayList<>();
		this.mines = new ArrayList<>();
		this.mapEvents = new ArrayList<>();
		this.availableColor = new LinkedList<>(Arrays.asList(Color.values()));
		this.random = new Random();
		this.gameScore = new GameScore(leaderboardService);

		this.playerService = playerService;

		this.world = new World<>();

		this.world.addCollisionListener(new TankBulletListener(this));
		this.world.addCollisionListener(new BulletBulletListener(this));
		this.world.addCollisionListener(new BulletWallListener());
		this.world.addCollisionListener(new BulletDestructibleListener(this));

		this.world.setGravity(PhysicsWorld.ZERO_GRAVITY);

		this.loadLevel();
	}

	private void loadLevel() {

		// TODO manage many layout
		List<Cell> cells = this.level.getLayouts().get(1).getCells();
		cells.stream().forEach(cell -> {
			if (cell.getBody() != null) {
				this.world.addBody(cell.getBody());
			}
		});
	}

	public void createPlayer(Player newPlayer) {
		this.players.put(newPlayer.getUserId(), newPlayer);

		this.addPlayerToWorld(newPlayer);
	}

	public void respawnPlayer(UUID userId) {
		Player player = this.getPlayer(userId);

		this.playerService.initializeStats(player);
		this.world.removeBody(player);
		this.gameScore.removeScore(player);

		this.addPlayerToWorld(player);
	}

	public void addPlayerToWorld(Player player) {
		List<Coordinate> spone = this.level.getSpawn();
		Coordinate playerSpone = spone.get(random.nextInt(spone.size()));

		playerService.initializePlayerPosition(player, playerSpone);
		playerService.initializeStats(player);

		if (this.world != null) {
			this.world.addBody(player);
		}
		this.gameScore.initScore(player);
	}

	public void removePlayer(UUID userId) {
		this.availableColor.add(this.players.get(userId).getColor());

		Player p = this.players.remove(userId);

		this.world.removeBody(p);
		this.gameScore.removeScore(p);
	}

	public Player getPlayer(UUID userId) {
		return players.get(userId);
	}

	public List<Player> getPlayers() {
		return this.players.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
	}

	public Map<Player, PlayerActionDTO> getPlayerActions() {
		return this.playerActions;
	}

	public void updatePlayerAction(Player player, PlayerActionDTO action) {
		playerActions.put(player, action);
	}

	public void updatePlayerAim(Player player, AimDTO aim) {
		PlayerActionDTO action = this.playerActions.get(player);

		// TODO avoid this check by ensuring that action will never be null
		if (action != null) {
			action.setAim(aim);
		}
	}

	public void start() {
		new GameLoop(this, 1000 / SettingsManager.FPS).start();
	}

	public void updateWorld(int elapsedTime) {
		// TODO Exception in thread "Thread-9" java.util.ConcurrentModificationException
		this.world.update(elapsedTime);
	}

	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
		this.world.addBody(bullet);
	}

	public void addMine(Mine mine) {
		this.mines.add(mine);
	}

	public void removeBullet(Bullet bullet) {
		bullet.getShooter().getBullets().remove(bullet);
		this.bullets.remove(bullet);
		this.world.removeBody(bullet);
	}

	public void removeMine(Mine mine) {
		this.mines.remove(mine);
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public List<Mine> getMines() {
		return mines;
	}

	public List<MapEvent> getMapEvents() {
		return mapEvents;
	}

	public void clearMapEvents() {
		this.mapEvents.clear();
	}

	public void killPlayer(Player player) {
		this.gameScore.initScore(player);
		player.setAlive(false);
	}

	public GameScore getGameScore() {
		return gameScore;
	}

	public Level getLevel() {
		return this.level;
	}

	public String getName() {
		return this.name;
	}

	public UUID getId() {
		return this.id;
	}

	public List<Color> getAvailableColor() {
		return this.availableColor;
	}

}
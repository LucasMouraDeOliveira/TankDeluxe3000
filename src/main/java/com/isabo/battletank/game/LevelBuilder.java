package com.isabo.battletank.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.entity.dto.LevelDTO;
import com.isabo.battletank.game.level.Layout;
import com.isabo.battletank.game.physical.Obstacle;
import com.isabo.battletank.service.BodyFactory;

@Service
public class LevelBuilder {
	
	@Autowired
	private BodyFactory bodyFactory;
	
	@Value("${app.map.location}")
	private String mapLocation;
	
	
	public Level loadLevel(String levelName) throws IOException {
		
		String levelString = Files.readString(Paths.get(mapLocation, levelName));
		levelString = new String(Base64.getDecoder().decode(levelString));
		LevelDTO levelDTO = new ObjectMapper().readValue(levelString, LevelDTO.class);
		
		
		return this.loadLevel(levelDTO);
	}
	
	public Level loadLevel(LevelDTO levelDTO) {
		Level level = new Level();
		
		level.setHeight(levelDTO.getHeight());
		level.setWidth(levelDTO.getWidth());
		
		Layout ground = new Layout(0);
		Layout obstacle = new Layout(1);
		
		List<List<String>> groundData = levelDTO.getGround();
		List<List<String>> obstacleData = levelDTO.getObstacle();

		// TODO optimize
		for (int x = 0; x < levelDTO.getWidth(); x++) {
			for (int y = 0; y < levelDTO.getHeight(); y++) {
				String groundSpriteCode = groundData.get(x).get(y);
				String obstacleSpriteCode = obstacleData.get(x).get(y);
				
				// Just assets
				if(groundSpriteCode != null) {
					Cell cell = new Cell(x, y);
					
					cell.setCode(groundSpriteCode);
					
					ground.addCell(cell);
				}
				
				// Assets and body
				if(obstacleSpriteCode != null) {
					Cell cell = new Cell(x, y);
					Obstacle body = this.bodyFactory.buildObstacle(obstacleSpriteCode, x * SettingsManager.OBSTACLE_WIDTH, y * SettingsManager.OBSTACLE_HEIGHT);

					cell.setCode(obstacleSpriteCode);
					cell.setBody(body);
					
					obstacle.addCell(cell);
				}
			}
		}
		
		level.addLayout(ground);
		level.addLayout(obstacle);
		
		// Set spawn
		level.addSpawn(new Coordinate(12, 15));
		level.addSpawn(new Coordinate(12, 68));
		level.addSpawn(new Coordinate(58, 8));
		level.addSpawn(new Coordinate(58, 65));
		level.addSpawn(new Coordinate(108, 15));
		level.addSpawn(new Coordinate(108, 68));

		return level;
	}
	
}

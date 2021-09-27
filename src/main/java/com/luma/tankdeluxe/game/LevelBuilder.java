package com.luma.tankdeluxe.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.entity.dto.LevelDTO;
import com.luma.tankdeluxe.game.level.Layout;
import com.luma.tankdeluxe.game.physical.Obstacle;
import com.luma.tankdeluxe.service.BodyFactory;

@Service
public class LevelBuilder {
	
	@Autowired
	private BodyFactory bodyFactory;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${app.map.location}")
	private String mapLocation;
	
	private List<String> mapList;
	
	
	public Level loadLevel(int i) throws IOException {
		return this.loadLevel(this.getMapList().get(i));
	}
	
	public Level loadLevel(String levelName) throws IOException {
		
		String levelString = Files.readString(Paths.get(mapLocation, levelName));
		levelString = new String(Base64.getDecoder().decode(levelString));
		
		LevelDTO levelDTO = mapper.readValue(levelString, LevelDTO.class);
		levelDTO.setName(levelName);
		
		return this.loadLevel(levelDTO);
	}
	
	public Level loadLevel(LevelDTO levelDTO) {
		Level level = new Level(levelDTO.getName());
		
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
	
	public List<String> getMapList() {
		if(this.mapList == null) {
			this.loadMapList();
		}
		
		return this.mapList;
	}
	
	private void loadMapList() {
		// Load existing map names
		this.mapList = Stream.of(new File(this.mapLocation).listFiles(File::isFile))
							.sorted((f1, f2) -> (int) (f1.lastModified() - f2.lastModified()))
							.map(File::getName)
							.collect(Collectors.toList());
	}
}

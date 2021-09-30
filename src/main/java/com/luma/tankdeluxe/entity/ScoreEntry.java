package com.luma.tankdeluxe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ScoreEntry implements Comparable<ScoreEntry> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private int score;
	
	
	@Override
	public int compareTo(ScoreEntry entry) {
		return entry.getScore() - this.score;
	}
}

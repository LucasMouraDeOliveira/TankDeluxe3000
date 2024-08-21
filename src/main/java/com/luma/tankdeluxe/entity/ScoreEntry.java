package com.luma.tankdeluxe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ScoreEntry implements Comparable<ScoreEntry> {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private Long id;

	private String username;
	private int score;

	@Override
	public int compareTo(ScoreEntry entry) {
		return entry.getScore() - this.score;
	}
}

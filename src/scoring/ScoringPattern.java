package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.*;

public abstract class ScoringPattern {
	private String name;
	private int fan;
	private List<String> relatedScoringPatterns;

	public ScoringPattern(String name, int fan, List<String> relatedScoringPatterns) {
		this.name = name;
		this.fan = fan;
		this.relatedScoringPatterns = relatedScoringPatterns;
	}

	public String getName() { return this.name; }
	public int getFan() { return this.fan; }
	public List<String> getRelatedScoringPatterns() { return Collections.unmodifiableList(this.relatedScoringPatterns); }
	public void addRelatedScoringPattern(String relatedScoringPattern) { this.relatedScoringPatterns.add(relatedScoringPattern); }
	public abstract boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind);
}
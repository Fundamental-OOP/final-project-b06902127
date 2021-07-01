package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TaiwanScoringRules implements ScoringHandler {
	public int scoring(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind, int sequentialWinCount) {
		List<ScoringPattern> scoringPatterns = Arrays.asList(
			new BigWinds(), // relatedScoringPatterns: SeatWind, PrevailingWind
			new SmallWinds(), // relatedScoringPatterns: SeatWind, PrevailingWind
			new SeatWind(),
			new PrevailingWind(),

			new BigDragons(), // relatedScoringPatterns: Dragons
			new SmallDragons(), // relatedScoringPatterns: Dragons
			new Dragons(),

			new AllHonorTiles(), // relatedScoringPatterns: MixedOneSuit, AllInTriplets
			new AllOneSuit(), // relatedScoringPatterns: MixedOneSuit
			new MixedOneSuit(),

			new AllInTriplets(),

			new FiveSelfTriplets(), // relatedScoringPatterns: FourSelfTriplets, ThreeSelfTriplets
			new FourSelfTriplets(), // relatedScoringPatterns: ThreeSelfTriplets
			new ThreeSelfTriplets(),

			new CommonHand(),

			new EightBonuses(), // relatedScoringPatterns: SeasonKong, FlowerKong, Seasons, Flowers
			new SevenBonuses(), // relatedScoringPatterns: SeasonKong, FlowerKong, Seasons, Flowers
			new SeasonKong(), // relatedScoringPatterns: Seasons
			new FlowerKong(), // relatedScoringPatterns: Flowers
			new Seasons(),
			new Flowers(),

			new AllFromOthers(isSelfPick), // relatedScoringPatterns: OneShot
			new OneShot(),
			new Middle(),
			new OneSide(),

			new AllFromSelf(),
			new WinFromWall(),
			new SelfPick(),

			new SequentialWin(sequentialWinCount)
		);

		int fan = 0;
		List<String> skipScoringPatterns = new ArrayList<String>();
		Iterator<ScoringPattern> iterator = scoringPatterns.iterator();
        while (iterator.hasNext()) {
            ScoringPattern scoringPattern = iterator.next();
            boolean skip = false;
            for (String skipScoringPattern: skipScoringPatterns) {
            	if (scoringPattern.getName().equals(skipScoringPattern)) {
            		skip = true;
            		break;
            	}
            }
            if (skip)
            	continue;

            if (scoringPattern.patternMatch(winningTiles, isSelfPick, prevailingWind, playerWind)) {
            	fan += scoringPattern.getFan();
            	skipScoringPatterns.addAll(scoringPattern.getRelatedScoringPatterns());
            }
        }
        return fan;
	}
}
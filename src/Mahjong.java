import player.Player;
import tiles.WindRank;
import tiles.Wall;
import tiles.Tile;
import tiles.Meld;
import action.WinningTiles;
import action.ActionChecker;
import action.KongChecker;
import action.ChowHeadChecker;
import action.WinChecker;
import action.Action;
import action.KongAction;
import action.WinAction;
import action.DefaultAction;
import scoring.ScoringHandler;
import scoring.TaiwanScoringRules;
import static utils.Inputs.in;
import input.GuiInterface;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Mahjong {
	private Player[] players;
	private int dealer;
	private WindRank prevailingWind;
	private Wall wall;
	private List<Tile> discardedTiles;
	private ActionChecker actionChecker; 
	private ActionChecker chowChecker; 
	private ActionChecker winChecker; 
	private ScoringHandler scoringHandler; //TODO: initialization
	private int sequentialWinCount;
	private int base;
	private int faanMoney;

	private static Mahjong game = new Mahjong();
	public static void main(String[] args) {
		game.startGame();
	}

	public Mahjong() {
		players = new Player[4];
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player();
			players[i].setID(i);
		}
		dealer = 0;
		prevailingWind = WindRank.East;
		wall = new Wall();
		discardedTiles = new ArrayList<>();
		sequentialWinCount = 0;
		base = 30;
		faanMoney = 10;
		// init all action checkers
		actionChecker = new KongChecker();
		chowChecker = new ChowHeadChecker();
		winChecker = new WinChecker();
		// init scoringChecker
		scoringHandler = new TaiwanScoringRules();

	}

	public void startGame() {
		choosePosition();
		chooseDealer();

		WindRank[] winds = WindRank.values();
		for(int i = 0; i < winds.length; i++) {
			prevailingWind = winds[i];
			startCycle();
		}
	}

	private void choosePosition() {
		// shuffle players
		Random rnd = new Random();
		for(int i = 0; i < players.length; i++) {
			int randomIdxToSwap = rnd.nextInt(players.length);
			Player temp = players[randomIdxToSwap];
			players[randomIdxToSwap] = players[i];
			players[i] = temp;
		}
	}

	private void chooseDealer() {
		Random rnd = new Random();
		int randomIdx = rnd.nextInt(15) + 3;
		dealer = randomIdx % players.length;
		System.out.println("dealer is : " + players[dealer].getID());
	}

	private void startCycle() {
		int changeDealerCount = 0;
		while(changeDealerCount < 4) {
			int winnerIdx = -1;

			// init
			wall = new Wall();
			for(int i = 0; i < players.length; i++)
				players[i].clearAllTiles();
			shuffle();
			deal();
			for(int i = 0; i < players.length; i++)
				players[i].sortTiles();
			discardedTiles = new ArrayList<>();

			// set all players' wind
			setPlayersWind();

			// dealer draw a tile from wall
			Tile drawnTile = wall.draw(1).get(0);

			/* testing */
			System.out.println("================================================");
			System.out.println("=                   deal                       =");
			System.out.println("================================================");
			for(int i = 0; i < 4; i++) {
				System.out.println("Player " + players[i].getID() + "'s tiles : ");
				List<Tile> tileList = players[i].getTiles();
				String tileListString = "";
				for (int j = 0; j < tileList.size(); j++) {
           			tileListString += String.format("[%d]%s ", j, tileList.get(j));
        		}
            	System.out.println(tileListString);
            	System.out.println();
			}

			// check whether dealer wins after draw a tile from wall		
			if(selfWinning(dealer, drawnTile)) 
				winnerIdx = dealer;

			else {
				players[dealer].draw(drawnTile);
				// grounding flower and check whether dealer wins after groundingFlower()
				if(groundingFlower()) 
					winnerIdx = dealer;
				
				else {
					/* testing */
					System.out.println("================================================");
					System.out.println("=             groundingFlower                  =");
					System.out.println("================================================");
					for(int i = 0; i < 4; i++) {
						System.out.println("Player " + players[i].getID() + "'s tiles : ");
						List<Tile> tileList = players[i].getTiles();
						String tileListString = "";
						for (int j = 0; j < tileList.size(); j++) {
		           			tileListString += String.format("[%d]%s ", j, tileList.get(j));
		        		}
		            	System.out.println(tileListString);
		            	System.out.println("revealBonusTiles are : ");
		            	tileList = players[i].getRevealedBonusTiles();
		            	tileListString = "";
						for (int j = 0; j < tileList.size(); j++) {
		           			tileListString += String.format("[%d]%s ", j, tileList.get(j));
		        		}
		            	System.out.println(tileListString);
		            	System.out.println();
					}

					// start round
					winnerIdx = startRound();	
				}
			}
			
			// update dealer and sequentialWinCount
			if(winnerIdx == -1) {
				;
			}
			else if(dealer != winnerIdx) {
				dealer = (dealer + 1) % players.length;
				changeDealerCount++;
				sequentialWinCount = 0;
			}
			else
				sequentialWinCount++;

			// ask player whether he wants to play next round
			/*System.out.println("Do you want to continue playing ? (yes/no)");
			String command = in.nextLine();
			if(!command.equals("yes"))
				System.exit(0);*/

			if(!GuiInterface.askWhetherContinue(players[0].getID(), players[1].getID(), players[2].getID(), players[3].getID(),
                                            players[0].getMoney(), players[1].getMoney(), players[2].getMoney(), players[3].getMoney()))
				System.exit(0);
		}
	}

	private void shuffle() {
		wall.shuffle();
	}

	private void deal() {
		for(int i = dealer, cnt = 0; cnt < 16; i = (i+1)%players.length, cnt++) {
			List<Tile> tiles = wall.draw(4);
			for(int j = 0; j < tiles.size(); j++)
				players[i].draw(tiles.get(j));
		}
	}

	private void setPlayersWind() {
		Random rnd = new Random();
		int randomIdx = rnd.nextInt(15) + 3;
		int eastPosition = (dealer + randomIdx) % players.length;
		WindRank[] winds = WindRank.values();
		for(int i = eastPosition, cnt = 0; cnt < players.length; i = (i+1)%players.length, cnt++) 
			players[i].setWind(winds[cnt]);
	}

	private boolean selfWinning(int playerIdx, Tile drawnTile) {
		List<Action> availableActions = winChecker.check(players[playerIdx], drawnTile);
		if(availableActions.size() == 1 && availableActions.get(0) instanceof DefaultAction)
			return false;
		Action playerAction = players[playerIdx].chooseAction(availableActions,
				players[(playerIdx+1)%4].getRevealedMelds(), players[(playerIdx+2)%4].getRevealedMelds(),
				players[(playerIdx+3)%4].getRevealedMelds(), drawnTile);
		if(playerAction != null && playerAction instanceof WinAction) {
			List<WinningTiles> allKindOfWinningTiles = null;
			allKindOfWinningTiles = playerAction.execute(drawnTile);
			Player winner = players[playerIdx];
			List<Player> losers = new ArrayList<>();
			for(int i = 0; i < players.length; i++)
				if(i != playerIdx)
					losers.add(players[i]);
			int maxScore = -1;
			for(int i = 0; i < allKindOfWinningTiles.size(); i++) {
				int currentScore = scoringHandler.scoring(allKindOfWinningTiles.get(i), true, prevailingWind, winner.getWind(), sequentialWinCount);
				if(currentScore > maxScore) {
					maxScore = currentScore;	
				}
			}
			updateMoney(maxScore, winner, losers);
			System.out.println("************************************************");
			System.out.println("*       player " + winner.getID() + " self winning  !!!!!           *");
			System.out.println("************************************************");	
			return true;
		}		
		return false;
	}

	private boolean groundingFlower() {
		boolean isFinished = false;
		boolean[] playerFinished = new boolean[players.length];
		for(int i = 0; i < playerFinished.length; i++)
			playerFinished[i] = false;

		while(!isFinished) {
			// grounding flower
			for(int i = dealer, cnt = 0; cnt < players.length; i = (i+1)%players.length, cnt++) {
				if(!playerFinished[i]) {
					int revealedBonusTilesNum = players[i].revealBonusTiles();
					if(revealedBonusTilesNum == 0) {
						playerFinished[i] = true;
						continue;
					}
					List<Tile> tiles = wall.draw(revealedBonusTilesNum);
					for(int j = 0; j < tiles.size() - 1; j++)
						players[i].draw(tiles.get(j));
					if(i == dealer && selfWinning(dealer, tiles.get(tiles.size() - 1))) 
						return true;
					else
						players[i].draw(tiles.get(tiles.size() - 1)); 
				}
			}

			// update isFinished
			isFinished = true;
			for(int i = 0; i < playerFinished.length; i++)
				isFinished = isFinished && playerFinished[i];
		}
		return false;
	}

	private int startRound() {
		boolean isEnd = false;
		int winnerIdx = -1;
		int currentIdx = dealer;
		while(!isEnd) {
			System.out.println("================================================");
			System.out.println("=              Everyone's tile                 =");
			System.out.println("================================================");
			for(int i = 0; i < 4; i++) {
				System.out.println("Player " + players[i].getID() + "'s tiles : ");
				List<Tile> tileList = players[i].getTiles();
				String tileListString = "";
				for (int j = 0; j < tileList.size(); j++) {
           			tileListString += String.format("[%d]%s ", j, tileList.get(j));
        		}
            	System.out.println(tileListString);

            	System.out.println("revealed melds : ");
            	String meldListString = "";
            	List<Meld> meldList = players[i].getRevealedMelds();
            	for (int j = 0; j < meldList.size(); j++) {
            		meldListString += String.format("[%d]%s ", j, meldList.get(j));
            	}
            	System.out.println(meldListString);
            	System.out.println();
			}
			System.out.println("================================================");
			System.out.println("=             player " + players[currentIdx].getID() + "'s turn :                =");
			System.out.println("================================================");			
			// player discards a tile
			Tile discardedTile = players[currentIdx].discard(players[(currentIdx+1)%4].getRevealedMelds(),
					players[(currentIdx+2)%4].getRevealedMelds(), players[(currentIdx+3)%4].getRevealedMelds());
			players[currentIdx].sortTiles();
			System.out.println("player " + players[currentIdx].getID() + " discards " + discardedTile);
			System.out.println();

			// check what actions can be performed by each player, and then let each player to choose an action
			List<Action> candidateActions = new ArrayList<>();
			for(int i = 1; i <= 3; i++) {
				List<Action> availableActions = new ArrayList<>();
				if(i == 1)
					availableActions.addAll(chowChecker.check(players[(currentIdx+i) % players.length], discardedTile));
				else
					availableActions.addAll(actionChecker.check(players[(currentIdx+i) % players.length], discardedTile));
				
				Action playerAction = null;
				if(availableActions.size() == 1 && availableActions.get(0) instanceof DefaultAction)
					playerAction = availableActions.get(0);
				else {
					System.out.println("player " + players[(currentIdx+i) % players.length].getID() + " can perform an action");
					playerAction = players[(currentIdx+i) % players.length].chooseAction(availableActions,
							players[(currentIdx+i+1)%4].getRevealedMelds(), players[(currentIdx+i+2)%4].getRevealedMelds(),
							players[(currentIdx+i+3)%4].getRevealedMelds(), discardedTile);
					System.out.println();
				}
				candidateActions.add(playerAction);
			}

			// compare all actions selected by every player, choose the action that has highest priority to perform
			Collections.sort(candidateActions);
			int winActionNum = 0;
			for(int i = 0; i < candidateActions.size(); i++)
				if(candidateActions.get(i) instanceof WinAction)
					winActionNum++;
			int choosedActionIdx = 0;
			if(winActionNum > 1) {
				int min = players.length;
				for(int i = 0; i < winActionNum; i++) {
					Player player = candidateActions.get(i).getPlayer();
					int playerIdx = 0;
					for(int j = 0; j < players.length; j++) 
						if(player.equals(players[j]))
							playerIdx = j;
					int distance = playerIdx - currentIdx;
					if(distance < 0)
						distance += players.length;
					if(distance < min) {
						min = distance;
						choosedActionIdx = i;
					}
				}
			}

			// perform action
			Action choosedAction = null;
			List<WinningTiles> allKindOfWinningTiles = null;
			if(candidateActions.size() != 0)
				choosedAction = candidateActions.get(choosedActionIdx);
			if(choosedAction != null)
				allKindOfWinningTiles = choosedAction.execute(discardedTile);

			System.out.println("player " + choosedAction.getPlayer().getID() + " choosedAction is : " + choosedAction);
			System.out.println();

			Tile drawnTile = null;
			// if no action is performed, change currentIdx to next player and draw a tile
			if(choosedAction == null || choosedAction instanceof DefaultAction) {
				// add discarded tile to discaredTiles
				discardedTiles.add(discardedTile);
				// change currentIdx to next player and draw a tile
				currentIdx = (currentIdx + 1) % players.length;
				drawnTile = wall.draw(1).get(0);

				// check whether player wins after draw a tile from wall		
				if(selfWinning(currentIdx, drawnTile)) {
					winnerIdx = currentIdx;
					isEnd = true;
					continue;
				}
				else
					players[currentIdx].draw(drawnTile); 

				// end game checking
				if(wall.getLength() <= 16) {
					isEnd = true;
					printDrawnGameMsg();
					continue;
				}
				
				// if drawn tile is bonus tile, ground flower
				int revealedBonusTilesNum = players[currentIdx].revealBonusTiles();
				while(revealedBonusTilesNum == 1) {
					drawnTile = wall.draw(1).get(0);
					// check whether player wins after draw a tile from wall		
					if(selfWinning(currentIdx, drawnTile)) {
						winnerIdx = currentIdx;
						isEnd = true;
						break;
					} 
					else
						players[currentIdx].draw(drawnTile);
					revealedBonusTilesNum = players[currentIdx].revealBonusTiles();

					// end game checking
					if(wall.getLength() <= 16) {
						isEnd = true;
						printDrawnGameMsg();
						break;
					}			
				}			
			}

			// if the choosed action is Win, then count the score and update everyone's money
			else if(choosedAction instanceof WinAction) {
				Player winner = choosedAction.getPlayer();
				List<Player> losers = new ArrayList<>();
				losers.add(players[currentIdx]);
				int maxScore = -1;
				for(int i = 0; i < allKindOfWinningTiles.size(); i++) {
					int currentScore = scoringHandler.scoring(allKindOfWinningTiles.get(i), false, prevailingWind, winner.getWind(), sequentialWinCount);
					if(currentScore > maxScore) {
						maxScore = currentScore;	
					}
				}
				updateMoney(maxScore, winner, losers);
				// get winner idx
				for(int i = 0; i < players.length; i++)
					if(players[i] == winner) {
						winnerIdx = i;
						break;
					}
				isEnd = true;
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("+        player " + winner.getID() + " winning  :))))               +");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			}

			// if the choosed action is Chow, Kong or Pong, change current Index to player who perform action
			else {
				Player nextPlayer = choosedAction.getPlayer();
				for(int i = 0; i < players.length; i++)
					if(players[i] == nextPlayer) {
						currentIdx = i;
					}

				// if the choosed action is Kong, draw a tile
				if(choosedAction instanceof KongAction) {
					drawnTile = wall.draw(1).get(0);

					// check whether player wins after draw a tile from wall		
					if(selfWinning(currentIdx, drawnTile)) {
						winnerIdx = currentIdx;
						isEnd = true;
						continue;
					}
					else 
						players[currentIdx].draw(drawnTile);

					// end game checking
					if(wall.getLength() <= 16) {
						isEnd = true;
						printDrawnGameMsg();
						continue;
					}
					
					// if drawn tile is bonus tile, ground flower
					int revealedBonusTilesNum = players[currentIdx].revealBonusTiles();
					while(revealedBonusTilesNum == 1) {
						drawnTile = wall.draw(1).get(0);
						// check whether player wins after draw a tile from wall		
						if(selfWinning(currentIdx, drawnTile)) {
							winnerIdx = currentIdx;
							isEnd = true;
							break;
						} 
						else
							players[currentIdx].draw(drawnTile);
						revealedBonusTilesNum = players[currentIdx].revealBonusTiles();

						// end game checking
						if(wall.getLength() <= 16) {
							isEnd = true;
							printDrawnGameMsg();
							break;
						}			
					}
				
				}
			}		
		}
		return winnerIdx;
	}

	private void updateMoney(int score, Player winner, List<Player> losers) {
		int money = base + score * faanMoney;
		for(int i = 0; i < losers.size(); i++) {
			winner.addMoney(money);
			losers.get(i).minusMoney(money);
		}
	}

	private void printDrawnGameMsg() {
		System.out.println("////////////////////////////////////////////////");
		System.out.println("/               drawn game :(                  /");
		System.out.println("////////////////////////////////////////////////");			
	}
} 
package nz.ac.auckland.softeng281.a2;

import java.util.ArrayList;
import java.util.List;

/**
 * you should change this class for TASK 1, 2, 3, 4.
 */
public class BlackJack {

	private List<Participant> players;
	private Participant dealer;

	public BlackJack() {
		players = new ArrayList<>();
		dealer = new BotDealer("Dealer", players); // FIXME Task 2
		players.add(new HumanPlayer("Player1"));
		// ADDHERE Task 1
		players.add(new BotPlayer("Bot1"));
		players.add(new BotPlayer("Bot2"));
	}

	// getter setter for testing purposes
	public List<Participant> getPlayers() {
		return players;
	}

	public Participant getDealer() {
		return dealer;
	}

	public void setPlayers(List<Participant> players) {
		this.players = players;
	}

	public void setDealer(Participant dealer) {
		this.dealer = dealer;
	}

	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.start();
	}

	protected void start() {
		Utils.printBlackJack();
		// create a new deck of cards
		Deck deck = new Deck();
		String result;
		do {
			for (Participant player : players) {
				player.play(deck);
			}
			// ADDHERE Task 2
			dealer.play(deck);
			
			checkWinner();
			System.out.println("Do you want to play again?");
			result = Utils.scanner.next();
			while (!result.equals("yes") && !result.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				result = Utils.scanner.next();
			}
		} while (result.equals("yes"));
		printPlayerHighestGain();
	}

	public void checkWinner() {
		// TODO Task 3
		for (Participant player : players) { // KEEPTHIS
			// ADDHERE
			int playerScore;
			Hand dealerHand = getDealer().getCurrentHand();

		    //Get the score of the current hand
			int dealerScore = dealerHand.getScore();
			
			Hand theHand = player.getCurrentHand();
			playerScore = theHand.getScore();
			
			// Prints out the player name that won against dealer
			if ((theHand.isBlackJack()) || (playerScore <= 21 && dealerScore > 21) || (playerScore <= 21 && dealerScore <= 21 && playerScore > dealerScore)) {
				System.out.println(player.getName() + " wins"); // UNCOMMENT AND KEEPTHIS
			}
		}
	}

	public void printPlayerHighestGain() {
		// TODO Task 4
		// Get dealer's hands and store the score in array
		// For loop to access all players
		// For loop inside player loop to access each hand and bet value
		// Determine the winner of each hand loop. Increment each player's gain value respectively
		int i = 0;
		int j = 0;
		String name = null;
		double gain = 0;
		double currentGain = 0;
		
		// Set highest gain to a default value
		double totalGain = -999999999;
		
		// Get the hand list of dealer
		var dealerHandList = dealer.getHands();
		
		// Use for loop to get the list of players
		for (i = 0; i < players.size(); i++) {
			
			currentGain = 0;
			
			Participant currentPlayer = players.get(i);
			var playerHandList = currentPlayer.getHands();
			
			// For each player, get the hand list
			for (j = 0; j < playerHandList.size(); j++){
				
				Hand currentDealerHand = dealerHandList.get(j);
				Hand currentPlayerHand = playerHandList.get(j);
				double playerBet = currentPlayerHand.getBet();
				
				// Get the current hand of current player score and dealer score
				int dealerScore = currentDealerHand.getScore();
				int playerScore = currentPlayerHand.getScore();
				
				// Check to see if hand wins on these conditions and increment gain value respectively
				if (currentPlayerHand.isBlackJack()) {
					gain = playerBet * 1.5;
				}
				else if ((playerScore <= 21 && dealerScore >= 21) || (playerScore <= 21 && dealerScore <= 21 && playerScore > dealerScore)) {
					gain = playerBet;
				}
				else {
					gain = -playerBet;
				}
				
				currentGain = currentGain + gain;

				
			}
			
			// After hand loop, checks if that player had the highest gain out of the group
			// Gets name of that player if condition is met
			if (currentGain >= totalGain) {
				totalGain = currentGain;
				name = currentPlayer.getName();
				
			}
			
			
		}

		
		// Print out statement
		System.out.println("The player with the highest gain is: " + name + " with "
		+ totalGain + " chips"); // UNCOMMENT AND KEEPTHIS
 
	}
}

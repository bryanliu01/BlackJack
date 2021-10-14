package nz.ac.auckland.softeng281.a2;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

/**
 * you should change this class for TASK 2
 */
public class BotDealer extends Participant {

	private List<Participant> players;

	private int count = 0;
	private int playerScore;

	public BotDealer(String name, List<Participant> players) {
		
		super(name);

		// ADDHERE
		this.players = players;
	
		
	}

	@Override
	public Action decideAction() {
		// TODO
		//Dealer wants to hit if its hand is losing to AT LEAST two players
		//Otherwise, the dealer will hold its hand
		
		Hand dealerHand = getCurrentHand();

	    //Get the score of the current hand
		int dealerScore = dealerHand.getScore();
		

		for (Participant player: this.players) {
			Hand theHand = player.getCurrentHand();
			playerScore = theHand.getScore();
			if (theHand.isBlackJack()) {
				count++;
			}
			else if (playerScore <= 21 && dealerScore > 21){
				count++;
			}
			else if (playerScore <= 21 && dealerScore <= 21 && playerScore > dealerScore){
				count++;
			}
		}
		
		if (this.count >= 2) {
			return Action.HIT;
		}
		else {
			return Action.HOLD;
		}
		//return new Random().nextBoolean() ? Action.HIT : Action.HOLD; // FIXME

	}

	@Override
	/**
	 * do not touch this method
	 */
	public int makeABet() {
		// the Dealer doesn't bet so is always zero
		return 0;
	}
}

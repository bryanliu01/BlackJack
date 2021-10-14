package nz.ac.auckland.softeng281.a2;

import java.util.Random;

/**
 * you should change this class for TASK 1
 */
public class BotPlayer extends Participant {

	public BotPlayer(String name) {
		super(name);
	}

	@Override
	public Action decideAction() {
		// TODO
    // Assign hand variable by geting the current Bot's hand
		Hand hand = getCurrentHand();

    //Get the score of the current hand
		int score = hand.getScore();

    // Implement basic AI, and return an action based on score
		if (score < 17) return Action.HIT; 
		else return Action.HOLD;	
		//return new Random().nextBoolean() ? Action.HIT : Action.HOLD; // FIXME
	}

	@Override
	public int makeABet() {
		// TODO
    // Assign random r as new instance
		Random r = new Random();
		int low = 1;
		int high = 101;

    // Finds random integer from 1 (inclusive) to 101 (exclusive)
		int result = r.nextInt(high-low)+low;
		return result; // FIXME
	}
}

//Sebrianne Ferguson
//December 28th, 2017

package blackjack;

import java.util.HashSet;
import java.util.Scanner;

public class Player {
	
	Scanner in = new Scanner(System.in); //for user input

	//player will need the following:
	//
	//a method that puts up a bet
	//a method that keeps track of the money the player has
	//a method that evaluates its hand and decides whether they want to draw another card ("hit me") or not ("stay")
	//
	
	HashSet<String> playerHand = new HashSet<String>(); //creates a hand for the player
	//Dealer dealer = new Dealer(); //have to create a dealer object in order to access its methods and properties
	double allowance = 0.00;
	double bet = 0.00;
	
	public HashSet<String> makePlayerHand(Dealer dealer) { 
		
		playerHand = dealer.makeMyHand(); //make a hand for the player
		return playerHand;
		
	}
	
	public HashSet<String> getHand() { //classic getter method
		return playerHand;
	}
	
	public void removeFromHand(Dealer dealer) {
		
		HashSet<String> removethese = new HashSet<String>(getHand());
		playerHand.removeAll(removethese);
		dealer.removeFromHand();
	}

	
	public void printHand() {
		for (String c: getHand()) {
			System.out.println(c);
		}
	}
	
	public void addToHand(Dealer dealer) { //if the player wants to add to their deck
		String c = dealer.deal();
		System.out.println("Hit me: " + c);
		playerHand.add(c);
	}
	
	
	public int calcTotal() {
		
		HashSet<String> myHand = new HashSet<String>();
		//deals 2 cards
		myHand.addAll(getHand());
		
		int total = 0;
		
		for (String card: myHand) {
			
			if ((card == "A_heart") || (card == "A_diamond") || (card == "A_spade") || (card == "A_club")) {
				//ace is different because it can count for 1 or 11 
				//so strategically if you have a small total or you have a 10 then you'd want to use it as an 11
				if (total < 10) {
					total = total + 11;
				}
				//if you have any total above 10 then you'd want to use it as a 1 so you do not bust.
				else {
					total = total + 1;
				}
			}
			
			if ((card == "2_heart") || (card == "2_diamond") || (card == "2_spade") || (card == "2_club")) {
				total = total + 2;
			}
			if ((card == "3_heart") || (card == "3_diamond") || (card == "3_spade") || (card == "3_club")) {
				total = total + 3;
			}
			if ((card == "4_heart") || (card == "4_diamond") || (card == "4_spade") || (card == "4_club")) {
				total = total + 4;
			}
			if ((card == "5_heart") || (card == "5_diamond") || (card == "5_spade") || (card == "5_club")) {
				total = total + 5;
			}
			if ((card == "6_heart") || (card == "6_diamond") || (card == "6_spade") || (card == "6_club")) {
				total = total + 6;
			}
			if ((card == "7_heart") || (card == "7_diamond") || (card == "7_spade") || (card == "7_club")) {
				total = total + 7;
			}
			if ((card == "8_heart") || (card == "8_diamond") || (card == "8_spade") || (card == "8_club")) {
				total = total + 8;
			}
			if ((card == "9_heart") || (card == "9_diamond") || (card == "9_spade") || (card == "9_club")) {
				total = total + 9;
			}
			if ((card == "10_heart") || (card == "10_diamond") || (card == "10_spade") || (card == "10_club")) {
				total = total + 10;
			}
			if ((card == "JACK_heart") || (card == "JACK_diamond") || (card == "JACK_spade") || (card == "JACK_club")) {
				total = total + 10;
			}
			if ((card == "QUEEN_heart") || (card == "QUEEN_diamond") || (card == "QUEEN_spade") || (card == "QUEEN_club")) {
				total = total + 10;
			}
			if ((card == "KING_heart") || (card == "KING_diamond") || (card == "KING_spade") || (card == "KING_club")) {
				total = total + 10;
			}
			
			
		}
		
		return total;
			
	}
	
public Evaluation playerEvaluate(Dealer dealer) {
		
		int total = calcTotal(); //first find out what the cards value to -- this is the calctotal method for the player
		dealer.dealerEvaluate();
		int dealerTotal = dealer.calcTotal(); //this finds the dealers total
		
		System.out.println("Your score is: " + total);
		
		if (total == 21) { //if you got blackjack, you win!
			System.out.println("BLACKJACK");
			this.adjustAllowanceForBlackjack();
			System.out.println("The dealer's score is: " + dealerTotal);
			dealer.printHand();
			return Evaluation.BLACKJACK;
		}
		
		if (total > 21) { //if you busted, return bust and you lose
			System.out.println("BUST");
			this.adjustAllowanceForLowerOrBust();
			System.out.println("The dealer's score is: " + dealerTotal);
			dealer.printHand();
			return Evaluation.BUST;
		}
		
		while (total < 21) { //if you havent busted and are able to take a card there is a chance you could beat the dealer.
			 
			if (total > 17) { //if you have a 16 or over, don't take the chance of adding another card
				break;
			} 
			 
			//however if you're still below 16 take a card 
			addToHand(dealer); //add a card
			total = calcTotal(); //find out if you're still under 21
			System.out.println("Your score is: " + total);
			
			if (total == 21) { //if you got blackjack, you win!
				System.out.println("BLACKJACK");
				this.adjustAllowanceForBlackjack();
				System.out.println("The dealer's score is: " + dealerTotal);
				dealer.printHand();
				return Evaluation.BLACKJACK;
			}
			
			if (total > 21) { //if you busted, return bust and you lose
				System.out.println("BUST");
				this.adjustAllowanceForLowerOrBust();
				System.out.println("The dealer's score is: " + dealerTotal);
				dealer.printHand();
				return Evaluation.BUST;
			}
			
		}
		
		//once you're finished taking any cards, compare your cards to the dealer
		
		System.out.println("The dealer's score is: " + dealerTotal);
		dealer.printHand();
		 
		 if (total < dealerTotal) { //you lose
			 System.out.println("LOWER");
			 this.adjustAllowanceForLowerOrBust();
			 return Evaluation.LOWER;
		 }
		 if (total > dealerTotal) { //you win
			 System.out.println("HIGHER");
			 this.adjustAllowanceForHigher();
			 return Evaluation.HIGHER;
		 }
		 if (total == dealerTotal) { //you tie
			 System.out.println("SPLIT");
			 return Evaluation.SPLIT;
		 }
		 
		 
		 
		 return Evaluation.DONE;
		
	}

public double putUpBet() {
	
	System.out.println("How much would you like to bet?");
	this.bet = in.nextDouble();
	
	return this.bet;
	
}

public double adjustAllowanceForLowerOrBust() {
	this.allowance = this.allowance - this.bet;
	return this.allowance;
}

public double adjustAllowanceForHigher() {
	this.allowance = this.allowance + this.bet;
	return this.allowance;
}

public double adjustAllowanceForBlackjack() {
	this.allowance = this.allowance + 2*(this.bet);
	return this.allowance;
}

public void printMoney() {
	System.out.println("You have $" + this.allowance);
	
}



public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	Player p = new Player();
	Dealer dealer = new Dealer();
	p.allowance = 100.00;
	p.printMoney();
	
	boolean play = true;
	
	while(play == true && (p.allowance > 0.00) ) {
		p.makePlayerHand(dealer);
		dealer.makeMyHand();
		p.putUpBet();
		System.out.println("");
		p.printHand();
		p.playerEvaluate(dealer);
		p.printMoney();
		System.out.println("");
		System.out.println("Would you like to play another round? (1 for YES, 2 for NO)");
		int choose = in.nextInt();
		if (choose == 2) {
			play = false;
		}
		dealer.removeFromHand();
		
	}
	
	if (p.allowance <= 0.00) {
		System.out.println("You ran out of money!");
	}
	
	System.out.println("***END OF GAME***");
	
	
}


	
}

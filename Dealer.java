//Sebrianne Ferguson
//December 28th, 2017

package blackjack;

import java.util.*;

public class Dealer {
	
	HashSet<String> dealerHand = new HashSet<String>();
	
	//dealer will need the following:
	//
	//a method that deals the cards to all of the players
	//a method that evaluates its hand and decides whether they want to draw another card ("hit me") or not ("stay")
	//a method that collects the bets from the players who get "lower" or "bust"
	//a method that pays the players what they put up if they scored "higher" or 2x what they put up if they "bust"
	//a method that "reshuffles" the deck when the cards run out
	
	public Dealer() {
		
		dealerHand = makeMyHand();
	}
	
	public HashSet<String> deck() {
		
		String[] frenchdeck = {"A_heart", "A_diamond", "A_spade", "A_club",
							   "2_heart", "2_diamond", "2_spade", "2_club", 
							   "3_heart", "3_diamond", "3_spade", "3_club",
							   "4_heart", "4_diamond", "4_spade", "4_club",
							   "5_heart", "5_diamond", "5_spade", "5_club",
							   "6_heart", "6_diamond", "6_spade", "6_club",
							   "7_heart", "7_diamond", "7_spade", "7_club",
							   "8_heart", "8_diamond", "8_spade", "8_club",
							   "9_heart", "9_diamond", "9_spade", "9_club",
							   "10_heart", "10_diamond", "10_spade", "10_club",
							   "JACK_heart", "JACK_diamond", "JACK_spade", "JACK_club",
							   "QUEEN_heart", "QUEEN_diamond", "QUEEN_spade", "QUEEN_club",
							   "KING_heart", "KING_diamond", "KING_spade", "KING_club"
							   };
		
		HashSet<String> mydeck = new HashSet<String>();

		for (int i = 0; i < frenchdeck.length; i++) {
			mydeck.add(frenchdeck[i]);
		}
		
		return mydeck;
		//probably a more efficient way to do this whole process
	}
	
	public String deal() {
		
		//the idea would be that every time the dealer deals a new card, there is a full deck
		//this should be fixed at one point because if the player needs a new card during a round it 
		//will assume that the deck is full
		ArrayList<String> deck = new ArrayList<String>(deck());
		//creates a new 52 card deck
		
		Random rand = new Random(); //pick a random card
		
		//HashSet<String> removethese = new HashSet<String>(); //create a hashset that will store the cards delt
		
		String card1 = deck.get(rand.nextInt(deck.size())); //deals a card
		//String card2 = deck.get(rand.nextInt(deck.size()));
		
		deck.remove(card1);
		
		return card1;
		
	}
	
	public HashSet<String> makeMyHand(){
		HashSet<String> myHand = new HashSet<String>();
		//deals 2 cards
		myHand.add(deal());
		myHand.add(deal());
		
		return myHand;
	}
	
	public HashSet<String> getHand() {
		return dealerHand;
	}
	
	public void printHand() {
		System.out.println("Dealer's Hand:");
		for (String c: getHand()) {
			System.out.println(c);
		}
	}
	
	public void addToHand() {
		String c = deal();
		//System.out.println("Hit Dealer: " + c);
		dealerHand.add(c);
	}
	
	public void removeFromHand() {
		HashSet<String> removethese = new HashSet<String>(getHand());
		dealerHand.removeAll(removethese);
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
	
	public Evaluation dealerEvaluate() {
		
		int total = calcTotal(); //first find out what the cards value to
		
		if (total == 21) { //if you busted, return bust
			return Evaluation.BLACKJACK;
		}
		
		if (total > 21) { //if you busted, return bust
			return Evaluation.BUST;
		}
		
		while (total < 21) { //if you havent busted and are able to take another card
			 
			if (total > 17) { //if you have a 16 or over, don't take the chance of adding another card
				break;
			} 
			 
			//however if you're still below 17 take a card 
			addToHand(); //add a card
			total = calcTotal(); //find out if you're still under 21
			
		}
		 
		 return Evaluation.DONE; //the dealer is done with their hand, now time to look at the 
		
	}
	
	
}

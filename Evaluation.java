//Sebrianne Ferguson
//December 28th, 2017

package blackjack;

public enum Evaluation {

	BLACKJACK, BUST, SPLIT, HIGHER, LOWER, DONE;
}

//this enum class will be used to evaluate the players hands based on 
//the hand of the dealer. It breaks down as follows:

//BLACKJACK = the player has a hand with 1 face card (10, jack, queen, or king) and and ace.
//BUST = the total value of the cards added up is greater than 21
//SPLIT = the total vlaue of the players card is equal to that of the dealer
//HIGHER = the total value of the players cards is higher than the dealer, but they did not bust
//LOWER = the total value of the players cards is lower than the dealer, but they did not bust
//DONE = I needed this for the dealer to know when to stop and let the players evaluate their cards.

//there will be a section of the code that is dedicated to determining this using these enum values.


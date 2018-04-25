/**
 * Dale Larie
 * Game of set PT 1
 *03/25/18
 * CS 110A
 */
// importing the used things
import java.util.*;

public class Deck {
    // initializing an arraylist of cards that the deck will represent
    ArrayList<Card> cards = new ArrayList<>();

    /**
     * deck constructor
     */
    public Deck() {
        // looping through each attribute of the card
        for (int i = 0; i < 3; i += 1) {
            for (int j = 0; j < 2; j += 1) {
                for (int k = 0; k < 3; k += 1) {
                    for (int h = 0; h < 3; h += 1) {
                        // creating one card of each attribute
                        Card card = new Card(i, j, k, h);
                        // adding the card to the arraylist of cards
                        cards.add(card);
                    }
                }
            }
        }
    }

    /**
     * shuffle function
     */
     public void shuffle(){
         // using the built in java library function shuffle for this arraylist
        Collections.shuffle(cards);
     }

    /**
     * checking if the deck is empty
     * @return a boolean of if its empty or not
     */
     public boolean isEmpty(){
         // using built in arraylist function
        return cards.isEmpty();
     }

    /**
     * getting the topcard and removing it
     * @return the card on top
     */
     public Card topCard(){
         // getting the top card in the deck and setting it equal to a local variable, top
        Card top = cards.get(0);
        // removing the card from the list
        cards.remove(0);
        // returning the card
        return top;
     }

     public int remainingCards(){
         return cards.size();
     }
     // deck does not have a to string method because I think it works better on the card object instead of the deck object

}

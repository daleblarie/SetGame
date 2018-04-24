// importing the needed stuff
import java.util.ArrayList;
// game class
public class Game {
    // greating a board for this game
    private Board b;
    // creating a deck for this game
    private Deck d;
    // creating an arraylist for the selected squares
    private ArrayList<BoardSquare> selected = new ArrayList<>();
    public Game(){
        // making a new deck and shuffling it
        d = new Deck();
//        d.shuffle();
        // making the board with the shuffled deck
        b = new Board(d);
    }
    // creating a method that adds three cards to the game
    public void add3(){
        b.add3(d);
    }
    // creating a method that puts the board in the game as a string
    /**
     *
     * @return a string to output to the console
     */
    public String toString(){
        String output = "";
        output += b.toString();
        return output;
    }

    // method to see if the game is out of cards to play and the game is over

    /**
     *
     * @return boolean for if the game is empty yet
     */
    public boolean outOfCards(){
        if (d.isEmpty() && b.isEmpty()){
            return true;
        }
        return false;
    }

    // method to add a card to the selected arraylist

    /**
     *
     */
    public void addToSelected(BoardSquare bsq){
        selected.add(bsq);
    }

    // method that gets the number of selected cards

    /**
     *
     * @return the number of tiles selected
     */
    public int numSelected(){
        return selected.size();
    }

    // method that tests the selected squares to see if its a set
    public boolean testSelected(){

        BoardSquare sq1 = selected.get(0);
        BoardSquare sq2 = selected.get(1);
        BoardSquare sq3 = selected.get(2);


        Card card1 = sq1.getThisCard();
        Card card2 = sq2.getThisCard();
        Card card3 = sq3.getThisCard();

        if (Card.isSet(card1, card2, card3)){
            System.out.println("It was a set!");
            return true;
        }
        else{
            System.out.println("this is not a set");
            
            return false;
        }
    }
    
    public void replaceCards(){
        for (BoardSquare sq: selected) {
            sq.setThisCard(d.topCard());
        }
    }
    // method that removes the selected card from the list, if it has been selected
    public void removeSelected(int row, int col){
        BoardSquare thisSquare = b.getSquare(row, col);
        if (selected.contains(thisSquare)){
            selected.remove(thisSquare);
        }
    }
    public void clearSelected(){
        for (BoardSquare sq: selected) {
            sq.setSelected(false);
        }
        selected.clear();

    }

    // getter for selected list

    /**
     *
     * @return the selected cards
     */
    public ArrayList<BoardSquare> getSelected() {
        return selected;
    }

    public Deck getD() {
        return d;
    }

    public Board getB() {
        return b;
    }
}

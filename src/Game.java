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
        d.shuffle();
        // making the board with the shuffled deck
        b = new Board(d);
    }

    // method to find a set on the board
    public ArrayList<BoardSquare> cheat() {
        // creating an arraylist for the matched set
        ArrayList<BoardSquare> matchSet = new ArrayList<>(0);
        // creating array for listing all the squares on the board
        ArrayList<BoardSquare> listOfAllSquares = new ArrayList<>();
        for (int i = 0; i < b.numRows(); i += 1) {
            for (int j = 0; j < b.numCols(); j += 1) {
                listOfAllSquares.add(b.getSquare(i, j));
            }
        }
        // starting at the first element
        for (int i = 0; i < listOfAllSquares.size(); i += 1) {
            // starting at the element after i
            for (int j = i + 1; j < listOfAllSquares.size(); j += 1) {
                // starting with the element after j
                for (int k = j + 1; k < listOfAllSquares.size(); k += 1) {

                    // getting the three squares to test
                    BoardSquare sq1 = listOfAllSquares.get(i);
                    BoardSquare sq2 = listOfAllSquares.get(j);
                    BoardSquare sq3 = listOfAllSquares.get(k);

                    // getting the cards from those squares
                    Card card1 = sq1.getThisCard();
                    Card card2 = sq2.getThisCard();
                    Card card3 = sq3.getThisCard();

                    // if they are a set, add them to match set and return it
                    if (Card.isSet(card1, card2, card3)) {
                        matchSet.add(sq1);
                        matchSet.add(sq2);
                        matchSet.add(sq3);
                        return matchSet;
                    }
                }
            }
        }
        // if there is never 3 cards that make a set, print no sets and retern the empty set
        System.out.println("There are no sets on the board");
        return matchSet;
    }

    public boolean gameOver(){
        if (d.isEmpty() && this.cheat().isEmpty()){
            System.out.println("Gameover!");
            return true;
        }
        return false;
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

        // getting the board squares
        BoardSquare sq1 = selected.get(0);
        BoardSquare sq2 = selected.get(1);
        BoardSquare sq3 = selected.get(2);


        // getting the cards from the squares
        Card card1 = sq1.getThisCard();
        Card card2 = sq2.getThisCard();
        Card card3 = sq3.getThisCard();

        // testing
        if (Card.isSet(card1, card2, card3)){
            System.out.println("It was a set!");
            return true;
        }
        else{
            System.out.println("this is not a set");
            
            return false;
        }
    }

    // method to replace the cards
    public void replaceCards(){
        // for each square in selected
        for (BoardSquare sq: selected) {
            if (!d.isEmpty()){
                // if deck is not empty, set it to the top card
                sq.setThisCard(d.topCard());
            } else{
                // if deck is empty, get the position of the card and set it to a null square
                for (int i = 0; i < b.numRows(); i += 1){
                        for (int j = 0; j < b.getRow(i).size(); j += 1){
                            if (b.getRow(i).get(j) == sq){
                                Card nullCard = new Card(3,3,3,3);
                                BoardSquare nullSquare = new BoardSquare(nullCard, i, j);
                                b.getRow(i).set(j, nullSquare);
                            }

                    }
                }
            }
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

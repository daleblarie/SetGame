/**
 * Dale Larie
 * Game of set PT 1
 *03/25/18
 * CS 110A
 */

// importing the libraries to use
import java.util.ArrayList;

public class Board {
    // creating a board arraylist of other arraylists
    ArrayList<ArrayList<BoardSquare>> board = new ArrayList<>();

    /**
     * constructor for the board
     * @param deck the deck to use
     */
    public Board(Deck deck) {
        // creating the first three rows
        for (int i = 0; i < 3; i += 1) {
            ArrayList<BoardSquare> currentRow = new ArrayList<>();
            // adding the rows, one at a time, to the board
            board.add(currentRow);
            for (int j = 0; j < 4; j += 1) {
                // adding the first 4 squares to each row
                BoardSquare currentSquare = new BoardSquare(deck.topCard(), i, j);
                currentRow.add(currentSquare);
            }
        }
    }

    /**
     * getter for the card in that board position
     * @param row the row of the square
     * @param column the col of the square
     * @return the card in that square
     */
    public Card getCard(int row, int column) {
        BoardSquare square = getSquare(row, column);
        return square.getThisCard();
    }

    public BoardSquare getSquare(int row, int column) {
        // getting the row first
        ArrayList<BoardSquare> RowOfSquares = board.get(row);
        // getting the square in the col of that row
        BoardSquare square = RowOfSquares.get(column);
        // returning the card in that square
        return square;
    }

    /**
     * getter for the num of cols
     * @return int for num of cols
     */
    public int numCols() {
        // getting the firs row. All rows should have same number of cols
        ArrayList<BoardSquare> currentRow = board.get(0);
        // returning the number of cols in that row
        return currentRow.size();
    }

    /**
     * getter for number of rows
     * @return int for num of rows
     */
    public int numRows() {
        // returning the number of row arraylists
        return board.size();
    }


    /**
     * function to add another column of 3 cards to the board
     * @param deck the deck to pull from
     */
    public void add3(Deck deck) {
        for (int i = 0; i < 3; i += 1) {
            // creating a new square to add
            BoardSquare newSquare = new BoardSquare(deck.topCard(), i, this.numCols() + 1);
            // getting the row to add the square to
            ArrayList<BoardSquare> currentRow = board.get(i);
            // adding the square to the appropriate row
            currentRow.add(i, newSquare);
        }
    }

    /**
     * to string function
     * @return returning the eng output string of all the cards on the board
     */
    public String toString(){
        // initializing the output
        String output = "";
        // looping through every position on the board
        for (int i = 0; i < this.numRows(); i += 1){
            for (int j = 0; j < this.numCols(); j += 1){
                // getting the card in this position
                Card currentCard = getCard(i, j);
                // adding the card string to the output
                output += currentCard.toString();
            }
            // creating a new line after the row is done
            output += "\n";
        }
        // returing the output string
        return output;
    }
    public boolean isEmpty(){
        return board.isEmpty();
    }
}

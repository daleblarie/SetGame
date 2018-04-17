/**
 * Dale Larie
 * Game of set PT 1
 *03/25/18
 * CS 110A
 */
public class BoardSquare {
    // setting the vars to be used in this square
    private Card thisCard;
    private int row;
    private int col;
    private boolean selected;

    /**
     * constructor function for board square
     * @param card the card for this suare
     * @param row the row for this square
     * @param col the col for this square
     */
    BoardSquare(Card card, int row, int col){
        // setting the card, row and col for this square
        this.thisCard = card;
        this.row = row;
        this.col = col;
        // all aquares are initally not selected
        selected = false;
    }

    /**
     * getter for the row
     * @return the int for this row
     */
    public int getRow(){
        return this.row;
    }

    /**
     * getter for the col
     * @return the inf for this col
     */
    public int getCol(){
        return this.col;
    }

    /**
     * getter for the card
     * @return the card from this square
     */
    public Card getThisCard() {
        return thisCard;
    }

    /**
     * getter for if its selected or not
     * @return boolean for if its selected
     */
    public boolean getSelected(){
        return selected;
    }

    /**
     * setter for the row of this square
     * @param row the row to set
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * setter for the col of this square
     * @param col the col to set
     */
    public void setCol(int col){
        this.col = col;
    }

    /**
     *  setter for the card for this square
     * @param card the card to set
     */
    public void setThisCard(Card card){
        this.thisCard = card;
    }

    /**
     * setter for the selector
     * @param bool boolean of if its selected
     */
    public void setSelected(boolean bool){
        this.selected = bool;
    }

    @Override
    public String toString() {
        String output = "";
        output += this.getThisCard();
        return output;
    }
}

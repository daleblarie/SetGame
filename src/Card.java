/**
 * Dale Larie
 * Game of set PT 1
 *03/25/18
 * CS 110A
 */
// importing nesseary stuff
import java.util.ArrayList;


public class Card {
    // creating differet aspects for the cards
    public static final int FILLED = 0;
    public static final int HATCHED = 1;
    public static final int BLANK = 2;

    public static final int RED = 0;
    public static final int BLUE = 1;
    public static final int PURPLE = 2;

    public static final int OVAL = 0;
    public static final int RECT = 1;
    public static final int SQUIGGLE = 2;

    public static final int ONE = 0;
    public static final int TWO = 1;
    public static final int THREE = 2;

    private int color, fill, shape, num;

    // constructor

    /**
     * @param color the color as an int
     * @param fill the fill as an int
     * @param shape the shape as an int
     * @param num the number of shapes as an int
     */
    public Card(int color, int fill, int shape, int num) {
        this.color = color;
        this.fill = fill;
        this.shape = shape;
        this.num = num;
    }

    // getters
    public int getColor() {return this.color;}

    public int getFill() {return this.fill;}

    public int getShape() {return this.shape;}

    public int getNum() {return this.num;}

    // Helpers to check if cards have all same or all different color, fill, etc.
    private static boolean isColorTheSame(Card card1, Card card2, Card card3){
        return card1.getColor() == card2.getColor() && card2.getColor() == card3.getColor();
    }

    private static boolean isColorAllDifferent(Card card1, Card card2, Card card3){
        return card1.getColor() != card2.getColor() && card2.getColor() != card3.getColor() && card1.getColor() != card3.getColor();
    }

    private static boolean isFillTheSame(Card card1, Card card2, Card card3){
        return card1.getFill() == card2.getFill() && card2.getFill() == card3.getFill();
    }

    private static boolean isFillAllDifferent(Card card1, Card card2, Card card3){
        return card1.getFill() != card2.getFill() && card2.getFill() != card3.getFill() && card1.getFill() != card3.getFill();
    }

    private static boolean isNumAllTheSame(Card card1, Card card2, Card card3){
        return card1.getNum() == card2.getNum() && card2.getNum() == card3.getNum();
    }

    private static boolean isNumAllDifferent(Card card1, Card card2, Card card3){
        return card1.getNum() != card2.getNum() && card2.getNum() != card3.getNum() && card2.getNum() != card3.getNum();
    }

    private static boolean isShapeAllTheSame(Card card1, Card card2, Card card3){
        return card1.getShape() == card2.getShape() && card2.getShape() == card3.getShape();
    }

    private static boolean isShapeAllDifferent(Card card1, Card card2, Card card3){
        return card1.getShape() != card2.getShape() && card2.getShape() != card3.getShape() && card2.getShape() != card3.getShape();
    }

    /**
     *
     * @param card1 first card
     * @param card2 second card
     * @param card3 third card
     * @return returning a boolean of if the three cards are a set
     */
    public static boolean isSet(Card card1, Card card2, Card card3){
     // creating a flag
        boolean flag = true;
        // if the three cards are not all the same color or all different color, flag is false
        if (!isColorTheSame(card1, card2, card3) && !isColorAllDifferent(card1, card2, card3)){
            flag = false;
        }
        // same with fill
        if (!isFillAllDifferent(card1, card2, card3) && !isFillTheSame(card1, card2, card3)) {
            flag = false;
        }
        // same with number
        if (!isNumAllDifferent(card1, card2, card3) && !isNumAllTheSame(card1, card2, card3)) {
            flag = false;
        }
        // same with shape
        if (!isShapeAllDifferent(card1, card2, card3) && !isShapeAllTheSame(card1, card2, card3)) {
            flag = false;
        }
        return flag;
    }

    /**
     *  no parameters
     * @return returning a string that has all the attributes of the card
     */
    public String toString(){
        //starting with an arraylist of the card attributes
        // initializing
        ArrayList<String> cardAttributes = new ArrayList<>();
        // getting all the different attributes for this card
        int num = this.getNum();
        int col = this.getColor();
        int fill = this.getFill();
        int shape = this.getShape();
        // creating a string for each attribute
        if (num == 0) {
            cardAttributes.add("1");
        } else if (num == 1){
            cardAttributes.add("2");
        } else {
            cardAttributes.add("3");
        }
        if (col == 0) {
            cardAttributes.add("RED");
        } else if (col == 1){
            cardAttributes.add("BLUE");
        } else {
            cardAttributes.add("PURPLE");
        }
        if (fill == 0) {
            cardAttributes.add("FILLED");
        } else if (fill == 1){
            cardAttributes.add("HATCHED");
        } else {
            cardAttributes.add("BLANK");
        }
        if (shape == 0) {
            cardAttributes.add("OVAL");
        } else if (shape == 1){
            cardAttributes.add("RECT");
        } else {
            cardAttributes.add("SQUIGGLE");
        }
        // initializing the output string
        String output = "";
        // loop to go through the attribute arraylist and create an appropriate string
        for (int i = 0; i < cardAttributes.size() - 1; i += 1){
            // adding the underscore so it is screaming snake case
            output += cardAttributes.get(i) + "_";
        }
        // adding a space at the end of the string
        output += cardAttributes.get(cardAttributes.size() - 1) + "     ";
        return output;

        // I realized after I had done this that this is a weird way to do it, but yolo, I might refactor this later
    }
}

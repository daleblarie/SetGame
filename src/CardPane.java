import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Paint;

import static javafx.scene.paint.Color.*;

public class CardPane extends HBox {
    // declaring a bunch of vars to be used later
    Paint color;
    Image bHatch, rHatch, pHatch;

    int cardShape;
    int cardColor;
    int cardNum;
    int cardFill;
    int num;
    int myRow;
    int myCol;
    Shape shape;
    private BoardSquare myBoardSquare;

    Card card;
    BoardSquare sq;

    // setting hatch variables
    public void setHatchFill(){
        try {
            // if the image exists set it to that
            bHatch = new Image("blue.jpg");

            // if it doesnt, set it to null
        } catch (IllegalArgumentException e){
            bHatch = null;
        }
        try {
            rHatch = new Image("red-1.jpg");
        } catch (IllegalArgumentException e){
            rHatch = null;
        }
        try {
            pHatch = new Image("purple.jpg");
        }catch (IllegalArgumentException e ){
            pHatch = null;
        }
    }
    public CardPane(BoardSquare sq) {
        // set hatch fill to assign the hatch vars
        setHatchFill();
        // set my board square and rows and cols
        myBoardSquare = this.sq;
        setMyCol(sq.getCol());
        setMyRow(sq.getRow());
        // center align the shape in the hbox
        this.setAlignment(Pos.CENTER);
        this.sq = sq;
        // setting the card to the card on the tile
        card = sq.getThisCard();
        // num to num on card
        num = number();
        // stylings
        this.setPrefSize(100, 100);
        this.toggleSelectedColor();
//        this.setStyle("-fx-border-width: 5;"
//                + "-fx-border-color: black;");
        // actually making the card
        for (int i = 0; i < num; i++) {
            color();
            makeShape();
            fill();
            shape.setStroke(color);
            this.getChildren().add(shape);
        }
    }
    private Paint color(){
        // gettng the card color and setting this.color to appropriate color
        cardColor = card.getColor();
        if (this.cardColor == 0){
            return this.color = RED;
        } else if (this.cardColor == 1){
            return this.color = BLUE;
        } else{
            return this.color = PURPLE;
        }
    }
    private int number(){
        // getting card num + 1
        cardNum = card.getNum() + 1;
        return card.getNum() + 1;
    }

    private void makeShape(){
        // method that actually makes the shape
        cardShape = card.getShape();
        if (this.cardShape == 0) {
            // if oval then create a circle
            shape = new Circle(10);
        } else if (this.cardShape == 1) {
            // if square then make a square
            shape = new Rectangle(20, 20);
        } else {
            // if squiggle then make a triangle
            shape = new Polygon(20, 0, 35, 30, 5, 30);
        }
    }
    private void fill(){
        // method to set the fill on the image
        cardFill = card.getFill();
        if (cardFill == 1) {
            // if fill is hatched
            if (this.color == RED) {
                try {
                    // set fill to hatch image if it exists
                    shape.setFill(new ImagePattern(rHatch, 0, 0, 1, 1, true));
                } catch (NullPointerException e){
                    // if its null, then just do a faded fill
                    Color c = new Color(1,0,0,0.25);
                    shape.setFill(c);
                }
            } else if (this.color == BLUE) {
                try {
                    shape.setFill(new ImagePattern(bHatch, 0, 0, 1, 1, true));
                } catch (NullPointerException e){
                    Color c = new Color(0,0,1,0.25);
                    shape.setFill(c);
                }
            } else if (this.color == PURPLE) {
                try {
                    shape.setFill(new ImagePattern(pHatch, 0, 0, 1, 1, true));
                } catch (NullPointerException e){
                    Color c = new Color(1,0,1,0.25);
                    shape.setFill(c);
                }            }
                // if its balnk, then fill it with white
        } else if (cardFill == 2) {
            shape.setFill(WHITE);
        } else {
            // if its filled, then fill with solid color
            shape.setFill(color);
        }
    }
    // method to toggle color of the tile
    public void toggleSelectedColor(){
        if (sq.getSelected()){
            // if its selected, use normal stlyes, but make background yellow
            this.setStyle("-fx-border-width: 5;"
                    + "-fx-border-color: black;"
                    + "-fx-background-color: yellow;");
        } else {
            // if its not selected, make bacground normal
            this.setStyle("-fx-border-width: 5;"
                    + "-fx-border-color: black;");
        }
    }
    public void toggleCheatColor(){
        // if its selected from the cheat, make the background orange
        this.setStyle("-fx-border-width: 5;"
                + "-fx-border-color: black;"
                + "-fx-background-color: orange;");
    }
    public void setMyRow(int r){
        myRow = r;
    }
    public void setMyCol(int c){
        myCol = c;
    }
    public BoardSquare getMyBoardSquare(){
        return myBoardSquare;
    }
}

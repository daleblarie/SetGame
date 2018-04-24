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
    Paint color;
    Image bHatch = new Image("blue.jpg");
    Image rHatch = new Image("red-1.jpg");
    Image pHatch = new Image("purple.jpg");
    int cardShape;
    int cardColor;
    int cardNum;
    int cardFill;
    int num;
    int myRow;
    int myCol;
    Shape shape;

    Card card;
    BoardSquare sq;
    public CardPane(BoardSquare sq) {
        setMyCol(sq.getCol());
        setMyRow(sq.getRow());
        this.setAlignment(Pos.CENTER);
        this.sq = sq;
        card = sq.getThisCard();
        num = number();
        this.setPrefSize(100, 100);
        this.toggleSelectedColor();
//        this.setStyle("-fx-border-width: 5;"
//                + "-fx-border-color: black;");
        for (int i = 0; i < num; i++) {
            color();
            makeShape();
            fill();
            shape.setStroke(color);
            this.getChildren().add(shape);
        }
    }
    private Paint color(){
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
        cardNum = card.getNum() + 1;
        return card.getNum() + 1;
    }

    private void makeShape(){
        cardShape = card.getShape();
        if (this.cardShape == 0) {
            shape = new Circle(10);
        } else if (this.cardShape == 1) {
            shape = new Rectangle(20, 20);
        } else {
            shape = new Polygon(20, 0, 35, 30, 5, 30);
        }
    }
    private void fill(){
        cardFill = card.getFill();
        if (cardFill == 1) {
            if (this.color == RED) {
                shape.setFill(new ImagePattern(rHatch, 0, 0, 1, 1, true));
            } else if (this.color == BLUE) {
                shape.setFill(new ImagePattern(bHatch, 0, 0, 1, 1, true));
            } else if (this.color == PURPLE) {
                shape.setFill(new ImagePattern(pHatch, 0, 0, 1, 1, true));
            }
        } else if (cardFill == 2) {
            shape.setFill(WHITE);
        } else {
            shape.setFill(color);
        }
    }
    public void toggleSelectedColor(){
        if (sq.getSelected()){
            this.setStyle("-fx-border-width: 5;"
                    + "-fx-border-color: black;"
                    + "-fx-background-color: yellow;");
        } else {
            this.setStyle("-fx-border-width: 5;"
                    + "-fx-border-color: black;");
        }
    }
    public void setMyRow(int r){
        myRow = r;
    }
    public void setMyCol(int c){
        myCol = c;
    }
}

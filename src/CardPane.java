import javafx.scene.layout.Pane;
import javafx.application.Application;
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
import java.util.Random;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Paint;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.PURPLE;
import static javafx.scene.paint.Color.RED;

public class CardPane extends HBox {
    Paint color;
    Image bHatch = new Image("blue.jpg");
    Image rHatch = new Image("red-1.jpg");
    Image pHatch = new Image("purple.jpg");
    int cardShape;
    int cardColor;
    int cardNum;
    int cardFill;
    int myRow;
    int myCol;
    Card card;
    BoardSquare sq;
    public CardPane(BoardSquare sq){
        this.setAlignment(Pos.CENTER);
        this.sq = sq;
        card = sq.getThisCard();
        int num = number();
        int cardCol = card.getColor();
        if (cardCol == 1){
            color = RED;
        } else if (cardCol == 2){
            color = BLUE;
        } else if (cardCol == 0){
            color = PURPLE;
        }
        this.setPrefSize(100,100);
        this.setStyle("-fx-border-width: 5;"
                +"-fx-border-color: black;");
        Shape shape = null;
        for (int i =0; i< num; i++)
            if (this.cardShape == 0){
            shape = new Circle(10);
            } else if (this.cardShape == 1){
            shape = new Rectangle(10, 10);
            } else {
            shape = new Circle(5);
            }
            shape.setFill(new ImagePattern(pHatch, 0, 0, 1, 1, true));
            this.getChildren().add(shape);
    }
    private Paint color(){
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
}

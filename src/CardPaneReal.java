import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

public class CardPaneReal extends HBox{
    Paint color;
    Image bHatch = new Image("blue.jpg");
    Image rHatch = new Image("red-1.jpg");
    Image pHatch = new Image("purple.jpg");

//    BoardSquare sq;
    Card card;
    int cardShape;
    int cardColor;
    int cardNum;
    int cardFill;

//    Shape shape;
    public CardPaneReal(BoardSquare bsq){
        this.setPrefSize(100,100);
        this.setStyle("-fx-border-width: 5;"
                +"-fx-border-color: black;");
        this.setAlignment(Pos.CENTER);

        this.card = bsq.getThisCard();
        cardGetters(this.card);
        setCol(cardColor);

        Shape bsqShape = makeShape(cardShape, color);
        for (int i =0; i < cardNum + 1; i++){
            this.getChildren().add(bsqShape);
        }

    }

    public void cardGetters(Card getCard){
        cardColor = getCard.getColor();
        cardShape = getCard.getShape();
        cardNum = getCard.getNum();
        cardFill = getCard.getFill();
    }
    public void setCol(int cardCol){
        if (cardCol == 0){
            color = RED;
        } else if (cardCol == 1){
            color = BLUE;
        } else if (cardCol == 2){
            color = PURPLE;
        }
    }
    public Shape makeShape(int shap, Paint col){
        Shape shape;
        if (shap == 0){
            shape = new Circle(10.0);
            shape.setStroke(col);
        } else if (shap == 1){
            shape = new Rectangle(20.0, 20.0);
            shape.setStroke(col);
        } else {
            shape = new Polygon(20.0, 0.0, 35.0, 30.0, 5.0, 30.0);
            shape.setStroke(col);
        }
        return shape;
    }
}

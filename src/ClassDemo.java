import javafx.application.Application;
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


public class ClassDemo extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        BorderPane pane = new BorderPane();
        GridPane squares = new GridPane();
        HBox textArea = new HBox();
//        /* add text to bottom pane */
//        Text t = new Text("Making panes");
//        t.setFont(Font.font("Ariel",24));
//        textArea.setStyle("-fx-background-color:yellow;");
//        textArea.getChildren().add(t);

        /* add stuff to grid */
        // Pane square = new Pane();

        Deck d = new Deck();
        d.shuffle();
        Card c = d.topCard();
        BoardSquare b = new BoardSquare(c, 1, 1);

        HBox square = new CardPane(b);
        square.setAlignment(Pos.CENTER);
        square.setPrefSize(100,100);
        square.setStyle("-fx-border-width: 5;"
                +"-fx-border-color: black;");
//        square.getChildren().add(new Circle(10,Color.RED));
        squares.add(square,0,0);
//        HBox circles2 = new HBox();
//        circles2.setAlignment(Pos.CENTER);
//        circles2.setPrefSize(100,100);
//
//        circles2.setStyle("-fx-border-width: 5;"
//                +"-fx-border-color: black;");
//        circles2.getChildren().add(new Circle(10,Color.BLUE));
//        squares.add(circles2,1,0);
//        HBox circles3 = new HBox();
//        circles3.setAlignment(Pos.CENTER);
//        circles3.setPrefSize(100,100);
//        circles3.setStyle("-fx-border-width: 5;"
//                +"-fx-border-color: black;");
//        circles3.getChildren().add(new Circle(10,Color.GREEN));
//        squares.add(circles3,0,1);
//        HBox circles4 = new HBox();
//        circles4.setAlignment(Pos.CENTER);
//        circles4.setPrefSize(100,100);
//        circles4.setStyle("-fx-border-width: 5;"
//                +"-fx-border-color: black;");
//        circles4.getChildren().add(new Circle(10,Color.BLACK));
//        squares.add(circles4,1,1);

        // add grid pane and hbox to the borderpane
        pane.setCenter(squares);
        pane.setBottom(textArea);

        squares.setAlignment(Pos.CENTER);// center grid pane inside center of borderpane

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String [] args)
    {
        launch(args);
    }
}
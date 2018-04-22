import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

public class GuiGame extends Application{
    Game g = new Game();
    Deck d = g.getD();
    Board b = g.getB();
    BorderPane pane = new BorderPane();
    GridPane squares = new GridPane();
    ArrayList<BoardSquare> sel= new ArrayList(0);

    @Override
    public void start(Stage primaryStage) {

//        sel.add(b.getSquare(0,0));
        HBox buttonArea = new HBox();
        Button testSet = new Button("Test Set");
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
//                sel.add(b.getSquare(0,0));
                for (int i = 0; i < b.numCols(); i += 1){
//                    b.getSquare(0, i).setSelected(true);
                    for (int j = 0; j < b.numRows(); j += 1){
//                        sel.add(b.getSquare(1,1));
                        if (b.getSquare(j, i).getSelected()){
                            sel.add(b.getSquare(j, i));
                        }
                    }
                }
                System.out.println(sel);
                System.out.println("testing");

                if (sel.size() == 3){
                    for (int i = 0; i < 3; i += 1){
                        g.addToSelected(sel.get(i));
                        g.testSelected();

                    }
                } else {
                    System.out.println("Wrong number of cards");
                }
            }
        };
        testSet.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        buttonArea.getChildren().addAll(testSet);
        pane.setBottom(buttonArea);



        for (int col = 0; col < 4; col += 1) {
            for (int r = 0; r < 3; r += 1) {
                Card c = d.topCard();
                BoardSquare bsq = new BoardSquare(c, r, col);

                HBox square = new CardPane(bsq);
                square.setAlignment(Pos.CENTER);
                square.setPrefSize(100, 100);
                square.setStyle("-fx-border-width: 5;"
                        + "-fx-border-color: black;");
                squares.add(square, col, r);


            }
        }
        pane.setCenter(squares);
        squares.setAlignment(Pos.CENTER);// center grid pane inside center of borderpane

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

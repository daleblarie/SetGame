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
    ArrayList<BoardSquare> sel = new ArrayList(0);
    ArrayList<CardPane> selectedPanes = new ArrayList<>(0);

    @Override
    public void start(Stage primaryStage) {

//        sel.add(b.getSquare(0,0));

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
//                sel.add(b.getSquare(0,0));
                CardPane clickedCardPane = (CardPane)e.getTarget();
                BoardSquare clickedBoardSquare = clickedCardPane.sq;
                if (g.getSelected().contains(clickedBoardSquare)){
                    g.getSelected().remove(clickedBoardSquare);
                    clickedBoardSquare.setSelected(false);
                    clickedCardPane.toggleSelectedColor();
                    selectedPanes.remove(clickedCardPane);
                } else {
                    selectedPanes.add(clickedCardPane);
                    g.addToSelected(clickedBoardSquare);
                    clickedBoardSquare.setSelected(true);
                    clickedCardPane.toggleSelectedColor();
                }
                System.out.println(g.getSelected());
                if (g.numSelected() == 3){
                        g.testSelected();

                        for (int i = 0; i < 3; i += 1) {
                            selectedPanes.get(i).toggleSelectedColor();
                        }
                        // whats happening is the card pane is changing because a new card is being drawn in its place (im pretty sure)
                        selectedPanes.clear();

                    }
                }
            };
        squares.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        HBox buttonArea = new HBox();
        Button add3Button = new Button("Add 3 Cards");
        Text text = new Text("There are" + d.remainingCards() + " cards remaining");
        buttonArea.getChildren().addAll(add3Button, text);
        pane.setBottom(buttonArea);

        EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.add3(d);
                for (int i = 0; i < 3; i += 1){
                    BoardSquare bsq = b.getSquare(i, i);
                    HBox square = new CardPane(bsq);
                    square.setAlignment(Pos.CENTER);
                    square.setPrefSize(100, 100);
                    square.setStyle("-fx-border-width: 5;"
                            + "-fx-border-color: black;");
                    squares.add(square, b.numCols(), i);
                }
                text.setText("There are " + d.remainingCards() + " cards remaining");
                primaryStage.show();
            }
        };
        add3Button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler1);


        for (int col = 0; col < 4; col += 1) {
            for (int r = 0; r < 3; r += 1) {
                BoardSquare bsq = b.getSquare(r, col);

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

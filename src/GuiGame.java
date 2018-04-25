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
    ArrayList<BoardSquare> selectedSquares = new ArrayList<>(0);
    ArrayList<CardPane> selectedPanes = new ArrayList<>(0);
    ArrayList<CardPane> allCardPanesOnBoard = new ArrayList<>(0);

    @Override
    public void start(Stage primaryStage) {
        HBox buttonArea = new HBox();
        Button add3Button = new Button("Add 3 Cards");
        Text text = new Text("There are" + d.remainingCards() + " cards remaining");
        Button cheatButton = new Button("Cheat");
        buttonArea.getChildren().addAll(add3Button, text, cheatButton);
        pane.setBottom(buttonArea);

//        sel.add(b.getSquare(0,0));

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
//                sel.add(b.getSquare(0,0));
                e.consume();
                CardPane clickedCardPane = (CardPane)e.getSource();

                BoardSquare clickedBoardSquare = clickedCardPane.sq;
                if (g.getSelected().contains(clickedBoardSquare)){
                    g.getSelected().remove(clickedBoardSquare);
                    clickedBoardSquare.setSelected(false);
                    clickedCardPane.toggleSelectedColor();
                    selectedSquares.remove(clickedBoardSquare);
                    selectedPanes.remove(clickedCardPane);
                } else {
                    selectedPanes.add(clickedCardPane);
                    g.addToSelected(clickedBoardSquare);
                    clickedBoardSquare.setSelected(true);
                    clickedCardPane.toggleSelectedColor();
                    selectedSquares.add(clickedBoardSquare);
                }
                System.out.println(g.getSelected());
                if (g.numSelected() == 3){
                    for (int i = 0; i < 3; i += 1) {
                        selectedPanes.get(i).sq.setSelected(false);
                        selectedPanes.get(i).toggleSelectedColor();
                    }
                    if (g.testSelected()){
                        if (!d.isEmpty()) {
                            g.replaceCards(); // do logic when the deck is empty
                            for (int i = 0; i < g.numSelected(); i += 1) {
                                int cardPaneRow = selectedPanes.get(i).myRow;
                                int cardPaneCol = selectedPanes.get(i).myCol;
                                BoardSquare squareToAdd = b.getSquare(selectedSquares.get(i).getRow(), selectedSquares.get(i).getCol());
                                CardPane newCardBeingAdded = new CardPane(squareToAdd);
                                HBox newCardToAdd = (HBox)newCardBeingAdded;
                                newCardToAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
                                squares.getChildren().remove(selectedPanes.get(i));
                                squares.add(newCardToAdd, cardPaneCol, cardPaneRow);
                                allCardPanesOnBoard.remove(selectedPanes.get(i));
                                allCardPanesOnBoard.add(newCardBeingAdded);
                            }
                        } else {
                            for (int i = 0; i < g.numSelected(); i += 1) {
                                g.replaceCards();
                                squares.getChildren().remove(selectedPanes.get(i));
                                allCardPanesOnBoard.remove(selectedPanes.get(i));
                            }

                        }

                    }else {
                        g.clearSelected();
                    }


                    // whats happening is the card pane is changing because a new card is being drawn in its place (im pretty sure)
                    selectedPanes.clear();
                    selectedSquares.clear();

                        text.setText("There are " + d.remainingCards() + " cards remaining");
                        g.getSelected().clear();
                        primaryStage.show();

                    }
                }
            };
//        squares.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


        EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int newColIndex = b.numCols();
                if (b.numCols() != 6 || d.remainingCards() == 3) {
                    b.add3(d);
                    for (int i = 0; i < 3; i += 1) {
                        BoardSquare bsq = b.getSquare(i, b.numCols() - 1);
                        HBox square = new CardPane(bsq);
                        square.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                        square.setAlignment(Pos.CENTER);
                        square.setPrefSize(100, 100);
                        square.setStyle("-fx-border-width: 5;"
                                + "-fx-border-color: black;");
                        squares.add(square, newColIndex, i);
                        allCardPanesOnBoard.add((CardPane)square);
                    }
                    text.setText("There are " + d.remainingCards() + " cards remaining");
                    primaryStage.show();
                } else {
                    System.out.println("Too many cards on the board!");
                }
            }
        };
        add3Button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler1);

        EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ArrayList<BoardSquare> cheatSquares = new ArrayList<>(0);
                for (int i = 0; i < selectedPanes.size(); i += 1) {
                    selectedPanes.get(i).sq.setSelected(false);
                    selectedPanes.get(i).toggleSelectedColor();
                }
                ArrayList<BoardSquare> check = g.cheat();
                selectedPanes.clear();
                selectedSquares.clear();
                if (!check.isEmpty()){
                    for (int i = 0; i < g.cheat().size(); i += 1){
                        cheatSquares.add(g.cheat().get(i));
                    }

                }
                for (int i = 0; i < allCardPanesOnBoard.size(); i += 1) {
                    if (!cheatSquares.isEmpty()){
                        if (cheatSquares.contains(allCardPanesOnBoard.get(i).sq)) {
                            allCardPanesOnBoard.get(i).toggleCheatColor();
                        }
                    }
                }
                System.out.println("Cheating");
                System.out.println(selectedPanes);
                primaryStage.show();
            }
        };

        cheatButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);

        for (int col = 0; col < 4; col += 1) {
            for (int r = 0; r < 3; r += 1) {
                BoardSquare bsq = b.getSquare(r, col);

                HBox square = new CardPane(bsq);
                square.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                square.setAlignment(Pos.CENTER);
                square.setPrefSize(100, 100);
                square.setStyle("-fx-border-width: 5;"
                        + "-fx-border-color: black;");
                squares.add(square, col, r);
                allCardPanesOnBoard.add((CardPane)square);


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

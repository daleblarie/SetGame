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
    //creating a new game and getting the deck and board
    Game g = new Game();
    Deck d = g.getD();
    Board b = g.getB();
    // creating the borderpane that will show up in the applet
    BorderPane pane = new BorderPane();
    // creating the gridpane of all the tiles
    GridPane squares = new GridPane();
    // creating various arraylists that will be used later
    ArrayList<BoardSquare> selectedSquares = new ArrayList<>(0);
    ArrayList<CardPane> selectedPanes = new ArrayList<>(0);
    ArrayList<CardPane> allCardPanesOnBoard = new ArrayList<>(0);

    @Override
    public void start(Stage primaryStage) {
        // creating the button area and creatiny buttons/text for that area
        HBox buttonArea = new HBox();
        Button newGameButton = new Button("New Game");
        Button add3Button = new Button("Add 3 Cards");
        Text text = new Text(d.remainingCards() + " cards in the deck");
        Text gameOverText = new Text("Game Over!");
        Button cheatButton = new Button("Cheat");
        Button quitButton = new Button("Quit");
        // adding the buttons/text to the HBox
        buttonArea.getChildren().addAll(add3Button, text, cheatButton, quitButton, newGameButton);
        // setting the button area to the bottom of the pane
        pane.setBottom(buttonArea);


        // creating an event handler for selecting/deselecting tiles
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                // getting the pane that the user clicked on
                CardPane clickedCardPane = (CardPane)e.getSource();

                // getting the boardSquare for that pane
                BoardSquare clickedBoardSquare = clickedCardPane.sq;

                // Removing the clicked pane/ clicked square from the list of selected if it has already been selected
                if (g.getSelected().contains(clickedBoardSquare)){
                    g.getSelected().remove(clickedBoardSquare);
                    // setting selected to false on the square
                    clickedBoardSquare.setSelected(false);
                    // toggling the color of the pane
                    clickedCardPane.toggleSelectedColor();
                    // removing from the arraylist of selected squares/panes
                    selectedSquares.remove(clickedBoardSquare);
                    selectedPanes.remove(clickedCardPane);
                } else {
                    // adding to the list of selected panes and selecting the tile
                    selectedPanes.add(clickedCardPane);
                    g.addToSelected(clickedBoardSquare);
                    clickedBoardSquare.setSelected(true);
                    // toggling color and adding to the list
                    clickedCardPane.toggleSelectedColor();
                    selectedSquares.add(clickedBoardSquare);
                }
                // printing the card values of the selecte card
                System.out.println(g.getSelected());
                if (g.numSelected() == 3){
                    // when there are three cards selected
                    for (int i = 0; i < 3; i += 1) {
                        // setting the individual card to unselected and changing the tile color back to normal
                        selectedPanes.get(i).sq.setSelected(false);
                        selectedPanes.get(i).toggleSelectedColor();
                    }
                    if (g.testSelected()){
                        // if the selecte cards are a set
                        if (!d.isEmpty()) {
                            // when there are more card to draw replace the card panes
                            g.replaceCards();

                            // for each selected card
                            for (int i = 0; i < g.numSelected(); i += 1) {
                                // getting the position of each card
                                int cardPaneRow = selectedPanes.get(i).myRow;
                                int cardPaneCol = selectedPanes.get(i).myCol;

                                // getting the square that is on the board now, but not on the gui board
                                BoardSquare squareToAdd = b.getSquare(selectedSquares.get(i).getRow(), selectedSquares.get(i).getCol());
                                // creating a card pane with that square
                                CardPane newCardBeingAdded = new CardPane(squareToAdd);
                                // creating an hbox for the new card to add
                                HBox newCardToAdd = newCardBeingAdded;
                                // adding this event filter to the new pane
                                newCardToAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, this);
                                // removing the old pane and replacing the new one in its place
                                squares.getChildren().remove(selectedPanes.get(i));
                                squares.add(newCardToAdd, cardPaneCol, cardPaneRow);
                                // adding and removing from the list of all cardpanes on board
                                allCardPanesOnBoard.remove(selectedPanes.get(i));
                                allCardPanesOnBoard.add(newCardBeingAdded);
                            }
                        } else {
                            // if the deck is empty
                            for (int i = 0; i < g.numSelected(); i += 1) {
                                // replacing match cards with null cards
                                g.replaceCards();
                                // removing match cards from list and gui board
                                squares.getChildren().remove(selectedPanes.get(i));
                                allCardPanesOnBoard.remove(selectedPanes.get(i));
                            }
                            // if there are no more cards in deck and no matches to be made
                           if (g.gameOver()){
                                // adding game over text to the board
                                squares.getChildren().add(gameOverText);
                           }

                        }

                        // if not a match
                    }else {
                        // clear the list of selected cards in game
                        g.clearSelected();
                    }

                    // clearing the lists of selected cards/panes
                    selectedPanes.clear();
                    selectedSquares.clear();

                    // displaying the number of cards remaining and clearing the selected cards in game
                    text.setText(d.remainingCards() + " cards in the deck");
                    g.getSelected().clear();
                    // displaying the changes
                    primaryStage.show();

                    }
                }
            };

        // new event hadler for the add3 button
        EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // creating a var for the column index for the new column
                // since the board will always be a recangle with the way its written, its ok to add the same col index for each
                int newColIndex = b.numCols();
                // if there are already six rows and at least 3 cards left
                if (b.numCols() != 6 && d.remainingCards() > 3) {
                    // add three cards to the board with the board function
                    b.add3(d);
                    // creating panes and adding the styles and stuff to them
                    for (int i = 0; i < 3; i += 1) {
                        BoardSquare bsq = b.getSquare(i, b.numCols() - 1);
                        HBox square = new CardPane(bsq);
                        square.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                        square.setAlignment(Pos.CENTER);
                        square.setPrefSize(100, 100);
                        square.setStyle("-fx-border-width: 5;"
                                + "-fx-border-color: black;");
                        // adding them to the board and list
                        squares.add(square, newColIndex, i);
                        allCardPanesOnBoard.add((CardPane)square);
                    }
                    // changing the number of cards in deck and showing the changes
                    text.setText(d.remainingCards() + " cards in the deck");
                    primaryStage.show();
                } else {
                    // telling the user there are too many cards if there are too many cards
                    System.out.println("Too many cards on the board!");
                }
            }
        };
        // adding the event to the button
        add3Button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler1);

        // creating a new event for clicking the cheat button
        EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // creating an array list of cheat squares
                ArrayList<BoardSquare> cheatSquares = new ArrayList<>(0);
                // setting every square to false and changing them to the normal color
                for (int i = 0; i < selectedPanes.size(); i += 1) {
                    selectedPanes.get(i).sq.setSelected(false);
                    selectedPanes.get(i).toggleSelectedColor();
                }
                // getting the arraylist for g.cheat() and assigning it to check
                ArrayList<BoardSquare> check = g.cheat();
                // clear selected in game and selected panes and squares
                g.clearSelected();
                selectedPanes.clear();
                selectedSquares.clear();
                // if the check is an array of cheat squares and not a blank array
                if (!check.isEmpty()){
                    // add each square to cheat squares
                    for (int i = 0; i < g.cheat().size(); i += 1){
                        cheatSquares.add(g.cheat().get(i));
                    }
                }
                // looping though every tile and toggling its color to the cheat color
                for (int i = 0; i < allCardPanesOnBoard.size(); i += 1) {
                    if (!cheatSquares.isEmpty()){
                        if (cheatSquares.contains(allCardPanesOnBoard.get(i).sq)) {
                            allCardPanesOnBoard.get(i).toggleCheatColor();
                        }
                    }
                }
                // telling the user that they are a dirty cheater
                System.out.println("Cheating");
                // showing the board
                primaryStage.show();
            }
        };

        // adding the function to the cheat button
        cheatButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);

        // event handler to exit button
        EventHandler<MouseEvent> eventHandler3 = new EventHandler<MouseEvent>() {
            @Override
            // exit the applet
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        };

        // adding quit function to quit button
        quitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler3);

        // writing new game event handler
        EventHandler<MouseEvent> eventHandler4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // for every cardpane on board, remove it from squares
                for (int i = 0; i < allCardPanesOnBoard.size(); i += 1){
                    squares.getChildren().remove(allCardPanesOnBoard.get(i));
                }

                // run startgame method and pass in the event handler for the individual cards
                startGame(eventHandler);
                // set the text
                text.setText(d.remainingCards() + " cards in the deck");
                // showthe board
                primaryStage.show();
            }
        };

        // adding the new game event to the button
        newGameButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler4);

        // run start game method to start the game
        startGame(eventHandler);
        // setting cards to the center of the pane
        pane.setCenter(squares);
        squares.setAlignment(Pos.CENTER);// center grid pane inside center of borderpane
        // creating new scene and showing it
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // creating a start game method that will take in an event handler
    public void startGame(EventHandler<MouseEvent> eventHandler){
        // new game and deck and stuff
        g = new Game();
        d = g.getD();
        b = g.getB();
        // clearing the old list of cardpanes
        allCardPanesOnBoard.clear();
        for (int col = 0; col < 4; col += 1) {
            for (int r = 0; r < 3; r += 1) {
                // getting the board squares in the new board
                BoardSquare bsq = b.getSquare(r, col);

                // creating the new card pands and stuff and setting styles/ alignment
                HBox square = new CardPane(bsq);
                square.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                square.setAlignment(Pos.CENTER);
                square.setPrefSize(100, 100);
                square.setStyle("-fx-border-width: 5;"
                        + "-fx-border-color: black;");

                // adding to squares and cardpanes list
                squares.add(square, col, r);
                allCardPanesOnBoard.add((CardPane)square);


            }
        }
    }

    // launch function
    public static void main(String[] args) {
        launch(args);
    }
}

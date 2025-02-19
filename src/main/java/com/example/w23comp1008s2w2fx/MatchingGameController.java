package com.example.w23comp1008s2w2fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class MatchingGameController implements Initializable {

    @FXML
    private ImageView cardImage0;

    @FXML
    private ImageView cardImage1;

    @FXML
    private ImageView cardImage2;

    @FXML
    private ImageView cardImage3;

    @FXML
    private ImageView cardImage4;

    @FXML
    private ImageView cardImage5;

    @FXML
    private ImageView cardImage6;

    @FXML
    private ImageView cardImage7;

    @FXML
    private Label correctLabel;

    @FXML
    private Label guessNumLabel;

    @FXML
    private Label percentCorrectLabel;

    @FXML
    private HBox row1HBox;

    @FXML
    private HBox row2HBox;

    private ArrayList<Card> cardsDealt;

    @FXML
    void playAgain(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Show the back of cards
        //This is the "long" way that calls each ImageView object directly
        Image backOfCard = new Image(Card.class.getResourceAsStream("images/back_of_card.png"));
        cardImage0.setImage(backOfCard);
        cardImage1.setImage(backOfCard);
        cardImage2.setImage(backOfCard);
        cardImage3.setImage(backOfCard);

        //We can also achieve this by looping over all the "Node's" in the HBox container
        //add a "listener" to each ImageView so that we can display the card selected
        for (Node node : row2HBox.getChildren())
        {
            //Cast the Node to be an ImageView object.  This is sort of like saying
            //It's a car versus it's a BMW
            ImageView imageView = (ImageView) node;
            imageView.setImage(backOfCard);

            //the -> {} is a "lambda expression", not required for this course, but can make things easier
            imageView.setOnMouseClicked(event -> {
                String id = imageView.getId();
                id = id.replaceAll("[a-zA-Z]*","");
                System.out.println(id);
                //concert the String into a number
                int index = Integer.parseInt(id);
                System.out.println(cardsDealt.get(index));
                imageView.setImage(cardsDealt.get(index).getImage());
            });
        }

        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        cardsDealt = new ArrayList<>();

        //deal 4 Cards and duplicate each one
        for (int i=1; i<=4 ; i++)
        {
            Card card = deck.dealTopCard();
            cardsDealt.add(card);
            cardsDealt.add(card);
        }

        Collections.shuffle(cardsDealt);



    }
}

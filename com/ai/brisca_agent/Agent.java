package com.ai.brisca_agent;

import com.ai.brisca_agent.entities.Card;
import sun.reflect.generics.tree.Tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 *
 */
public class Agent {
    private ArrayList<Card> myCards;
    private String triunfo;
    private ArrayList<Card> bastosJugados;
    private ArrayList<Card> copasJugadas;
    private ArrayList<Card> oroJugados;
    private ArrayList<Card> espadasJugadas;
    /*
    Create the agent with the first cards
     */
    public Agent(Card[] myHand,String triunfo){
        for(int i=0;i<=2;i++)
            myCards.add(myHand[i]);
        this.triunfo=triunfo;
    }
    /*
    Receive the cards in the table and select the best play
     */
    public Card playTurn(Card[] tableCards) {
        //TODO
        updateCards(tableCards);
        Card selectedCard=null;
        return selectedCard ;


    }
    public void maxValue(){
        //TODO
    }
    public void minValue(){
        //TODO
    }
    /*
    Adds the cards to the corresponding list by type
     */
    public void updateCards(Card[] cards){
        for (Card i:cards){
            switch (i.getSuit()){
                case "basto": bastosJugados.add(i);
                break;
                case "copa": copasJugadas.add(i);
                break;
                case "oro": oroJugados.add(i);
                break;
                case "espadas": espadasJugadas.add(i);
                break;
            }

        }
    }
    private void createTree(){



    }
}
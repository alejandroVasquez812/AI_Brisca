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
        Card selectedCard=null;
        return selectedCard ;


    }
    public void maxValue(){
        //TODO
    }
    public void minValue(){
        //TODO
    }

    private void createTree(){



    }
}
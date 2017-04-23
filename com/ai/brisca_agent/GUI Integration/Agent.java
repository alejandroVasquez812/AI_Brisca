package com.ai.brisca_agent.entities;

import java.util.ArrayList;

import com.ai.brisca_agent.SimManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Agent {
	
	private static ArrayList<Card> hand;
	private static ArrayList<Card> round;
	private static int score;
	
	public static void init()
	{
		hand = new ArrayList<Card>();
		round = new ArrayList<Card>();
		score = 0;
	}
	
	public static Card choosePlay()
	{
		// decide which card to play
		
		Card result = hand.get(0);
		round.add(result);
		hand.remove(result);
		
		System.out.println("Hand:");
		for (int i = 0; i < hand.size(); i++)
		{
			System.out.println(hand.get(i).getName());
		}
		System.out.println("Round:");
		for (int i = 0; i < round.size(); i++)
		{
			System.out.println(round.get(i).getName());
		}

		
		return result;
	}
	
	public static void render(SpriteBatch b)
	{
		int c = 0;
		for(Texture t: SimManager.scoreToImageArray(score + ""))
		{
			b.draw(t, 700 + (c*20), 400);
			c++;
		}
	}
	
	public static void addCardToHand(Card c)
	{
		hand.add(c);
	}
	
	public static void addCardToRound(Card c)
	{
		round.add(c);
	}
	
	public static void clearRound()
	{
		round.clear();
	}
	
	public static ArrayList<Card> getHand()
	{
		return hand;
	}
	
	public static ArrayList<Card> getRound()
	{
		return round;
	}
	
	public static void addPoints(int p)
	{
		score += p;
	}
	
	public static int getScore()
	{
		return score;
	}
	
	public static void resetScore()
	{
		score = 0;
	}

}

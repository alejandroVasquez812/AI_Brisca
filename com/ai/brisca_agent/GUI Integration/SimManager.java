package com.ai.brisca_agent;

import java.util.ArrayList;
import java.util.Collections;

import com.ai.brisca_agent.entities.Agent;
import com.ai.brisca_agent.entities.Card;
import com.ai.brisca_agent.states.SimState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class SimManager {
	
	private static String trumpSuit;
	private static Texture[] number;
	
	public static void init()
	{
		number = new Texture[10];
		
		for(int i = 0; i <= 9; i++)
		{
			number[i] = new Texture(Gdx.files.internal("numbers/" + i + ".png"));
			number[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
	
	public static void setTrumpSuit(String s)
	{
		trumpSuit = s;
	}

	public static void checkForWinner(ArrayList<Card> turnCards)
	{
		trumpSuit = "g";
		
		ArrayList<Card> significantCards = new ArrayList<Card>();

		Card winnerCard;
		
		boolean trump = false, equalCards = false;


		System.out.println("The trump suit is: " + trumpSuit);

		// CHECKS IF THERE ARE TRUMP CARDS IN THE TURN PLAYED
		for ( int i = 0; i < turnCards.size(); i++)
		{
			if(turnCards.get(i).getSuit().equals(trumpSuit))
			{
				significantCards.add(turnCards.get(i));
				trump = true;
			}
		}

		// IF TRUMP CARDS ARE FOUND THIS ORGANIZES THE CARDS AND GET THE WINNER CARD
		if(trump)
		{
			// Check for cards trump cards with value
			
			int count = 0;
			for(int j = 0; j < significantCards.size(); j++)
			{
				if(significantCards.get(j).getValue() > 0)
					count++;
			}
			
			// If more than one card with value - organize by value
			if(count >= 1)
			{
				for(int a = 0; a < significantCards.size()- 1; a++)
				{
				
					for(int i = 0; i < significantCards.size(); i++)
					{
						int indexNow = i + 1;
		
						if(significantCards.size() > indexNow)
						{
							
							if(significantCards.get(i).getValue() < significantCards.get(i+1).getValue())
							{
								Card temp = significantCards.get(i);
								significantCards.set(i, significantCards.get(indexNow));
								significantCards.set(indexNow, temp);
								winnerCard = significantCards.get(i);
							}
						}
					}
				}
				
			}
			// organize by number
			else
			{
				for(int a = 0; a < significantCards.size()- 1; a++)
				{
					for(int i = 0; i < significantCards.size(); i++)
					{
						int indexNow = i + 1;
		
						if(significantCards.size() > indexNow)
						{
							
							if(significantCards.get(i).getNumber() < significantCards.get(i+1).getNumber())
							{
								Card temp = significantCards.get(i);
								significantCards.set(i, significantCards.get(indexNow));
								significantCards.set(indexNow, temp);
								winnerCard = significantCards.get(i);
							}
						}
					}
				}
			}
			
			System.out.println("SIZE:" + significantCards.size());
			for(int i = 0; i < significantCards.size(); i++)
				System.out.println("sCard:" + i + ": " + significantCards.get(i).getName());
				
			winnerCard = significantCards.get(0);
		}

		// IF THERE ARE NO TRUMP CARDS THIS CHECKS IF 
		// THERE ARE CARDS EQUAL TO THE FIRST PLAYED
		else
		{
			significantCards.add(turnCards.get(0));

			for( int i = 0; i < turnCards.size() - 1; i++)
			{
				if(turnCards.get(0).getSuit().equals(turnCards.get(i + 1).getSuit()))
				{
					significantCards.add(turnCards.get(i+1));
					equalCards = true;
				}
			}
		}

		// IF THERE ARE EQUAL CARDS THIS ORGANIZE THEM AND GET THE WINNER CARD
		if (equalCards)
		{
			// Check for cards with same suit that have value value
			int count = 0;
			for(int j = 0; j < significantCards.size(); j++)
			{
				if(significantCards.get(j).getValue() > 0)
					count++;
			}
			
			// If more than one card with value - organize by value
			if(count >= 1)
			{
				for(int a = 0; a < significantCards.size()- 1; a++)
				{
					for(int i = 0; i < significantCards.size(); i++)
					{
						int indexNow = i + 1;
		
						if(significantCards.size() > indexNow)
						{
							if(significantCards.get(i).getValue() < significantCards.get(i+1).getValue())
							{
								Card temp = significantCards.get(i);
								significantCards.set(i, significantCards.get(indexNow));
								significantCards.set(indexNow, temp);
							}
						}
					}
				}
			}
			// organize by number
			else
			{
				for(int a = 0; a < significantCards.size()- 1; a++)
				{
					for(int i = 0; i < significantCards.size(); i++)
					{
						int indexNow = i + 1;
		
						if(significantCards.size() > indexNow)
						{
							if(significantCards.get(i).getNumber() < significantCards.get(i+1).getNumber())
							{
								Card temp = significantCards.get(i);
								significantCards.set(i, significantCards.get(indexNow));
								significantCards.set(indexNow, temp);
							}
						}
					}
				}
			}

			for(int i = 0; i < significantCards.size(); i++)
				System.out.println("sCard:" + i + ": " + significantCards.get(i).getName());
			
			winnerCard = significantCards.get(0);
		}
		else
		{
			winnerCard = significantCards.get(0);
		}
		
		String temp = winnerCard.getName();
		int index = 0;
		int pointsAdded = 0;
		boolean assigned = false;
		
		for(int i = 0; i < Agent.getRound().size(); i++)
		{
			pointsAdded += Agent.getRound().get(i).getValue();
		}
		
		for(int i = 0; i < SimState.getPlayers().size(); i++)
		{
			
			if(SimState.getPlayers().get(i).getName().equals(temp))
			{
				SimState.getPlayers().get(i).addPoints(pointsAdded);
				assigned = true;
			}
				
		}
		
		if(!assigned)
			Agent.addPoints(pointsAdded);
		

		System.out.println("The winner Card is: " + winnerCard.getSuit() + "    " + winnerCard.getNumber());
		System.out.print("Points Added: " + pointsAdded);
		
	}

	
	public static ArrayList<Texture> scoreToImageArray(String score)
	{
		ArrayList<Texture> result = new ArrayList<Texture>();
		
		while(!score.equals(""))
		{
			for(int i = 0; i <=9; i ++)
			{
				if(score.startsWith("" + i))
				{
					result.add(number[i]);
					score = score.substring(1, score.length());
				}
					
			}
		}
		
		return result;
	}
}

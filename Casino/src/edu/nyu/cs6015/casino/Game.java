package edu.nyu.cs6015.casino;

import java.util.ArrayList;

public abstract class Game 
{
	String gameName;
	ArrayList<Player> currentPlayers = new ArrayList<Player>();
	ArrayList<Player> currentRoundPlayers = new ArrayList<Player>();
	int tableSize;
	ArrayList<Deck> decksUsed = new ArrayList<Deck>();
	int currentPotMoney;
	ArrayList<Card> flop = new ArrayList<Card>();
	
	public Game()
	{
		Deck d = new Deck();
		decksUsed.add(d);
		
		System.out.println("Game object Created");
	}
	public abstract void startGame();
	public abstract void pauseGame();
	public abstract void quitGame();
	
	public abstract void updateGame();
	
	public abstract void resetGame();
	
	public void addPlayer(Player p)
	{
		this.currentPlayers.add(p);
		this.currentRoundPlayers.add(p);
	}
	
	public void removePlayer(Player p)
	{
		if(p != null && this.currentPlayers.contains(p))
		{
			this.currentPlayers.remove(p);
			this.currentRoundPlayers.remove(p);
		}
	}
	
	public void addPotMoney(int n)
	{
		this.currentPotMoney += n;
	}
	
	public int getPotMoney()
	{
		return this.currentPotMoney;
	}
	public void setTableSize(int n)
	{
		this.tableSize = n;
	}
	
	public int tableSize()
	{
		return this.tableSize;
	}
	
	
	public void createDeck()
	{
		Deck d = new Deck();
		this.decksUsed.add(d);
	}
	
	public Deck getCurrentDeck()
	{
		return this.decksUsed.get(0);
	}
	
	public ArrayList<Card> getFlop()
	{
		return this.flop;
	}
	
	public String getName()
	{
		return this.gameName;
	}
	
	public ArrayList<Player> getPlayers()
	{
		ArrayList<Player> temp = new ArrayList<Player>();
		for(Player p : this.currentPlayers)
		{
			if(p.currentPlayerStatus != PlayerMove.Fold)
			{
				temp.add(p);
			}
		}
		return temp;
	}
	
	public ArrayList<Card> getCurrentFlopCard()
	{
		return this.flop;
	}
	
}

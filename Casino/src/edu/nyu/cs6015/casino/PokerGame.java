package edu.nyu.cs6015.casino;

import java.util.ArrayList;

public class PokerGame extends Game
{
	Deck currentDeck;
	ArrayList<Card> flop = new ArrayList<Card>();
	int currentPotSize = 0;
	int currentPotRaise;
	public PokerGame()
	{
		super();
		this.gameName = "Poker";
		this.tableSize = 8;
	}
	
	@Override
	public void startGame() 
	{
		currentDeck = this.decksUsed.get(0);
		currentDeck.sortDeck();
		currentDeck.shuffleDeck();
		for(int i=0 ; i < 2;i++)
		{
			for(Player p : this.currentRoundPlayers)
			{
				p.addCard(currentDeck.getTop());
			}
		}
		this.PlayersMakeMoves();
		for(int i=0;i<3;i++)
		{
			flop.add(currentDeck.getTop());
		}
		this.PlayersMakeMoves();
		flop.add(currentDeck.getTop());
		this.PlayersMakeMoves();
		flop.add(currentDeck.getTop());
		this.PlayersMakeMoves();
	}

	@Override
	public void pauseGame() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitGame() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetGame() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void PlayersMakeMoves()
	{
		boolean raise = true;
		one: while(raise)
		{
			Move currentMove = null;
			two : for(Player p : this.currentRoundPlayers)
			{
				System.out.println("Current Pot Raise" +currentPotRaise);
				if(p.currentPlayerStatus != PlayerMove.Fold && p.currentMoneyBet == currentPotRaise )
				{
					raise = false;
					currentMove = p.makeMove();
				}
				else if(p.currentPlayerStatus != PlayerMove.Fold && currentPotRaise > p.currentMoneyBet)
				{
					currentMove = p.forceRaise(currentPotRaise - p.currentMoneyBet); 
				}
				
				if(currentMove != null && currentMove.getMove().equals(PlayerMove.Check))
				{
					continue two;
				}
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Fold))
				{
					p.setCurrentStatus(PlayerMove.Fold);
					p.setMoneyLost(p.currentMoneyBet);
				}
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Call))
				{
					continue two;
				}
				
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Raise))
				{
					int moveMoney = currentPotRaise - currentMove.getBet();
					raise = true;
					if(moveMoney > 0)
					{
						currentPotRaise += moveMoney;
					}
					else
					{
						currentPotRaise += currentMove.getBet();
					}
				}
				
			}
			currentPotRaise = 0;
			break one;
		}
	}
	
	public ArrayList<Player> getWinners()
	{
		return null;
	}
	

}

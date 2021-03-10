
public class Player
{
    private int playerNum;		// Names will follow 0,1,2,3,etc
    private int wins, losses, ties;
    
    public Player( )
    {
        playerNum = 999999;		//Needed a default value that wasn't zero. Held down 9 for a second.
        wins = 0;
        losses = 0;
        ties = 0;
    }
    public void setNum(int num)
    {
        this.playerNum = num;
    }
	public void setWins(int wins) 
	{
		this.wins = wins;
	}
	public void setLosses(int losses) 
	{
		this.losses = losses;
	}
	public void setTies(int ties) 
	{
		this.ties = ties;
	}
    public int getPlayerNum( )
    {
        return playerNum;
    }
	public int getWins()
	{
		return wins;
	}
	public int getLosses() 
	{
		return losses;
	}
	public int getTies() 
	{
		return ties;
	} 
}

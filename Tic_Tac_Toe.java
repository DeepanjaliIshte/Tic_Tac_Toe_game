import java.util.Random;
import java.util.Scanner;

public class Tic_Tac_Toe 
{
	static char[][] board;
	public Tic_Tac_Toe()
	{
		board =new char[3][3];// row(i)  and column(j)
		initalizing_Board();
	}
	void initalizing_Board()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}
	static void display_Board()
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");	// first Line(|)
			
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print( board[i][j]+" | ");	 // Second line		
			}
			System.out.println();		
			System.out.println("-------------");
			
		}
	}
	static void Place_Mark(int row,int col,char mark)
	{
		
		if(row>0 && row<=2 && col >0 && col<=2)
		{
			board[row][col]=mark;	
		}
		else
		{
			System.out.println("Invalid Position");
		}
	}
	static boolean check_Col_Win()
	{
		for(int j=0;j<=2;j++)
		{
			if (board[j][0] !=' ' &&board[0][1]==board[1][j] && board[1][j]==board[2][j])
			{
				return true;
			}
		}
		return false;
	}
	static boolean check_Row_Win()
	{
		for(int i=0;i<=2;i++)
		{
			if (board[i][0] !=' ' && board[i][0] == board[i][1] && board[i][1]==board[i][2])
			{
				return true;
			}
		}
		return false;
	}
	static boolean check_Diagnol_Win()
	{
		if(board[0][0] !=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]||board[0][2] !=' ' &&
				board[0][2]==board[1][1] && board[1][1]==board[2][2])
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	static boolean check_Draw()
	{
		for(int i=0;i<=2;i++)
		{
			for(int j=0;j<=2;j++)
			{
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
		return true;
	}
}

abstract class Player
{
	 String name;
	 char mark;
	 
	 abstract void makeMove();
	 boolean isValid_Move(int row,int col)
	 {
		 if(row >=0 && row<=2 && col >=0 && col<=2)
		 {
			 if(Tic_Tac_Toe.board[row][col]==' ')
			 {
				 return true;
			 }
		 }
		 return false;
	 }
}
class Human_Player extends Player
{
	 String name;
	 char mark;
	 public Human_Player(String name, char mark)
	 {
		this.name=name;
		this.mark=mark;
	 }
	 
	void makemove()
	 {
		 int row;
		 int col;
		Scanner s= new Scanner (System.in);
		do {
		System.out.println("Enter the row and col ");
		 row=s.nextInt();
		 col=s.nextInt();
		}
		while(!isValid_Move(row, col));
		
		Tic_Tac_Toe.Place_Mark(row,col,mark);
	 }

	@Override
	void makeMove() {} 
}

class AI_Player extends Player
{
	 String name;
	 char mark;
	 public AI_Player(String name, char mark)
	 {
				this.name=name;
				this.mark=mark;
	 }
	 
		void makemove()
	 {
		 int row;
		 int col;
		do
		{
			Random r=new Random();
			row =r.nextInt(3);
			col=r.nextInt(3);
		}
		while(!isValid_Move(row, col));
		
		Tic_Tac_Toe.Place_Mark(row,col,mark);
	 }

		@Override
		void makeMove() {}
}

class LaunchGAme{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Tic_Tac_Toe t=new Tic_Tac_Toe();
		
		t.display_Board();
		System.out.println(t.check_Row_Win());
		System.out.println(t.check_Col_Win());
		System.out.println(t.check_Diagnol_Win());
		Human_Player p1= new Human_Player("John",'X');
		//Human_Player p2= new Human_Player("Alex",'O');
		AI_Player p2=new AI_Player("AI",'O');
		
		Player cp;	// cp=current player
		cp=p1;
		
		while(true)
		{
		System.out.println(cp.name+ " Your turn ");
		cp.makeMove();
		
		Tic_Tac_Toe.display_Board();
		
		if(Tic_Tac_Toe.check_Col_Win()||Tic_Tac_Toe.check_Row_Win()
								||Tic_Tac_Toe.check_Diagnol_Win())
		{
			System.out.println(cp.name+" Has Won ");
			break;
		}
		else if(Tic_Tac_Toe.check_Draw())
		{
			System.out.println("GAme is a draw");
		}
		else
		{
			if(cp ==p1)
			{
				cp=p2;
			}
			else
			{
				cp=p1;
			}
		}
		
		}
	}
}

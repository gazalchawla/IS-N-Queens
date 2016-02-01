package assignmentTwo;

public class Formulae {
	
	//function to calculate the heuristic value
	public static int calculateHeuristic(int[] board)
	{
		int h = 0;
		int offset = 0;
		for (int i=0;i<board.length;i++)
		{
			for(int j=i+1;j<board.length;j++)
			{
				if(board[i]==board[j])
				{
					h = h+1;
				}
				
				offset = j-i;
				
				if((board[i]==board[j]-offset)||(board[i]==board[j]+offset))
				{
					h = h+1;
				}
			}
		}
		return h;
	}

}

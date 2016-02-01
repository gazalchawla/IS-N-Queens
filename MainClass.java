package assignmentTwo;

import java.util.Scanner;

public class MainClass {
	static int randomRestarts = 0;
	static int stepsOfClimbing = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		Board initBoard = new Board();
		Board solBoard = new Board();
		Board loopBoard = new Board();
		Scanner scanObj = new Scanner(System.in);
		
		//input number of queens
		System.out.println("Please enter the number of queens on the board!");
		initBoard.setNoOfQueens(scanObj.nextInt());
		loopBoard.setNoOfQueens(initBoard.getNoOfQueens());
		initBoard.randomBoard(initBoard.getNoOfQueens());
		
		int[] localNext = new int[initBoard.getNoOfQueens()];
		scanObj.close();
		localNext = initBoard.determineNextStep(initBoard.getConfig());
		
		while(true)
		{
			localNext = initBoard.determineNextStep(localNext);
			
			//to calculate the total state changes
			MainClass.stepsOfClimbing++;
			if(localNext==null)
			{
				//to calculate the number of restarts required
				MainClass.randomRestarts++;
				loopBoard.randomBoard(loopBoard.getNoOfQueens());
				localNext = loopBoard.determineNextStep(loopBoard.getConfig());
			}
			else
			{
				if(Formulae.calculateHeuristic(localNext)==0)
				{
					solBoard.setConfig(localNext);
					break;
				}
			}
		}
		
		System.out.println("The start configuration is:\n");
		int[] localInit = initBoard.getConfig();
		
		//print initial board
		for(int col=0;col<initBoard.getNoOfQueens();col++)
		{
			for(int row=0;row<initBoard.getNoOfQueens();row++)
			{
				if(localInit[col]==row)
				{
					System.out.print("Q");
				}
				else
				{
					System.out.print("*");
				}
			}
			System.out.println("\n");
		}
		
		System.out.println("The solution configuration is:\n");
		localNext = solBoard.getConfig();
		
		//print solution board
		for(int col=0;col<initBoard.getNoOfQueens();col++)
		{
			for(int row=0;row<initBoard.getNoOfQueens();row++)
			{
				if(localNext[col]==row)
				{
					System.out.print("Q");
				}
				else
				{
					System.out.print("*");
				}
			}
			System.out.println("\n");
		}
		
		System.out.println("\nThe number of random restarts required were: "+MainClass.randomRestarts);
		System.out.println("\nThe total number of state changes that happened were: "+MainClass.stepsOfClimbing);
	}
}

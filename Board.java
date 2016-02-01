package assignmentTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	private int[] config;
	private int noOfQueens;
	
	//function to generate the random configuration board
	public void randomBoard(int noQueens)
	{
		config = new int[noQueens];
		Random var = new Random();
		for(int i=0;i<noQueens;i++)
		{
			config[i] = var.nextInt(noQueens);
		}
	}
	
	public int getNoOfQueens() {
		return noOfQueens;
	}


	public void setNoOfQueens(int noOfQueens) {
		this.noOfQueens = noOfQueens;
	}


	public int[] getConfig() {
		return config;
	}


	public void setConfig(int[] config) {
		this.config = config;
	}
	
	//returns the next candidate
	public int[] determineNextStep(int[] currentConfig)
	{
		int length = (currentConfig.length)*(currentConfig.length);				//since for n queens we would have an n*n board
		int[] moves = new int[length];											//stores the hVals of the current children
		List<int[]> correspondingMoves = new ArrayList<int[]>();				//stores the configuration for that move
		int count = 0;															//to maintain indices of both the arrays
		int[] copyBoard; 														//stores temporarily copied board 
		int[] k = new int[currentConfig.length];								//used to return the next variable
		
		//for every column we are going to move the queen up/down
		for(int col=0;col<currentConfig.length;col++)
		{
			//moving the queen up/down
			for(int row=0;row<currentConfig.length;row++)
			{
				//state same as currentConfig
				if(currentConfig[col]==row)
				{
					moves[count] = Formulae.calculateHeuristic(currentConfig);
					correspondingMoves.add(count, currentConfig);
					count++;
				}
				
				else
				{
					copyBoard = new int[currentConfig.length];
					System.arraycopy(currentConfig, 0, copyBoard, 0, currentConfig.length);
					copyBoard[col] = row;
					moves[count] = Formulae.calculateHeuristic(copyBoard);
					correspondingMoves.add(count, copyBoard);
					count++;
				}
			}
		}
		
		//h value of currentConfig
		int hToBeat = Formulae.calculateHeuristic(currentConfig);
		
		//finds the minimum h value from the generated nodes, hToBeat is replaced by the new smaller value or stays same as hToBeat; 
		//in which case we have reached a local maximum
		for (int i=0;i<moves.length;i++)
		{
			if(moves[i]<hToBeat)
				hToBeat = moves[i];
		}
		
		if(hToBeat==Formulae.calculateHeuristic(currentConfig))
		{
			return null;
		}
		
		else
		{
		
		//finds the corresponding move for the h value
		for(int i=0;i<moves.length;i++)
			{
				if(moves[i]==hToBeat)
				{
					k = correspondingMoves.get(i);
				}
			}
		}
		return k;
	}
}

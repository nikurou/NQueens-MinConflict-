
/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #3
 *
 * < Description: A class that keeps track of the board to be solved, and contains the algorithm to solve the NQueen problem using MinConflict
 * 				 
 *
 * @author Dylan Chung 
 *   
 */


public class NQueenMinConflict {
	
	//Local Field Variables
	int max_steps;
	QueenBoard board;
	public float elapsedTime;
	public int numGeneration;
	public boolean isSolved;
	long start = System.currentTimeMillis();
	
	
	// Constructor method, Initializes the local field variables 
	public NQueenMinConflict(int n, int max_steps){
		board = new QueenBoard(n);
		this.max_steps = max_steps;
		isSolved = true;
		numGeneration = 0;
	}
	
	// The MinConflict Algorithm 
	public QueenBoard minConflict(){
		QueenBoard current = board;
		System.out.println("\nStarting Board: " + current + "\n");
		for(numGeneration = 0 ; numGeneration < max_steps; numGeneration++){
			if(current.getFitness() == 0){
				setElapsedTime();
				printEndResult(current);
				return current;
			}
			int var = current.getRandomConflictedQueenIndex();
			current = current.moveToLowestConflictIndex(var);
		}
		
		//If it exited the loop without solving
		if(current.getFitness() != 0){
			System.out.println("Failed to Solve in time!");
			isSolved = false;
		}
		
		setElapsedTime();
		printEndResult(current);
		return current;
	}
	
	// Prints out the Algorithm Name, the ending board, and the amount of time spent on the algorithm.
	private void printEndResult(QueenBoard current) {
		System.out.println("A "+ board.getPopulationSize() + "N Queen Solution (MinConflict): ");
		System.out.println("Solved Board:   " + current);
		System.out.println("Run Time: " + elapsedTime + " seconds \n");
	}

	// Returns the elapsed time 
	public float getElapsedTime() {
		return elapsedTime;
	}
	
	// Calculates the total elapsed time on funciton call.
	private void setElapsedTime() {
		long end = System.currentTimeMillis();
		float sec = (end - start)/1000F;
		elapsedTime = sec;
	}
}

/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #3
 *
 * < Description: Holds the main class that creates NQueenMinConflict Objects 
 *                and calls their respective functions to start the algorithms
 *                to solve the NQueenProblem using the MinConflict algorithm
 *
 * @author Dylan Chung 
 *   
 */

public class Main {
	
	public static void main(String[]args){
		//forAnalysisOfMinConflict();
		NQueenMinConflict n3 =  new NQueenMinConflict(25, 1000);
		n3.minConflict();
		
	}
	
	private static void forAnalysisOfMinConflict() {
		int avgSolved = 0;
		int avgGeneration = 0;
		float avgRunTime= 0;
		
		for(int i = 0; i<400; i++) {
			NQueenMinConflict n3 =  new NQueenMinConflict(25, 1000);
			n3.minConflict();
			
			avgSolved += n3.isSolved ? 1:0;
			avgGeneration += n3.numGeneration;
			avgRunTime += n3.elapsedTime;
		}
		
		System.out.println("\n\nA 25N Queen Solution (MinConflict) at 400 Instances");
		System.out.println("Avg Solved : " + ((double)avgSolved/400.0)* 100 + "%");
		System.out.println("Avg Generations : "  + (double)avgGeneration/400+ " generations");
		System.out.println("AvgRunTime : " + avgRunTime/400 + " seconds");
		System.out.println("TotalRunTime : " + avgRunTime + " seconds");
	}
	
}

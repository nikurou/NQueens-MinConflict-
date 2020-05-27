
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #3
 *
 * < Description: A class that holds the n x n board of n queens in an single dimensional array, the value of n, and the fitness
 *                of the board as well as various other functions to calculate, populate, and other functions for use with MinConflict
 *                algorithm.>
 *
 * @author Dylan Chung 
 *   
 */

public class QueenBoard implements Comparable<QueenBoard> {
	//Local field variables
	int n; //Queen Size
	int [] board; // Array where the value of each index represents the row the Queen belongs to, and the index represents the column it belongs to.
	int h; //Number of pairs of queens that are attacking each other.
	
	public QueenBoard(int n){
		this.n = n;
		//board = new int[n][n];
		board = new int[n];
		populateBoard();
		h = calcFitnessVal(board);
		
	}
	
	public QueenBoard(int[] childBoard) {
		n = childBoard.length;
		board = childBoard;
		h = calcFitnessVal(childBoard);
	}

	// Generate the Queens and add them to the board.
	// Only one Queen per column 
	public void populateBoard(){
		
		Random rand = new Random();
		
		//Generate random number between 0 - 25.
		for( int i = 0 ; i < n; i++){
			board[i] = rand.nextInt(n+1);	
		}
	}
	
	
	// Returns the Number of Queens that are attacking each other. 
	// The lower, the better.
	private int calcFitnessVal(int [] queens){
		int numAttackPairs = 0; 
		
		for(int i = 0; i < queens.length; i++ ){ 
			for(int j = i+1; j<queens.length; j++){
					int rise = Math.abs(i-j);
					int run = Math.abs(queens[i]-queens[j]);
					
					if(rise == run || run == 0){
						numAttackPairs++;
					}
			}
		}
		return numAttackPairs;
		
	}
	
	//Returns a random Queen index of the Board
	public int getRandomConflictedQueenIndex(){
		int index = new Random().nextInt(n); //0 - 24 
		
		// If we find no conflict on this index and the board isn't solved, find a new index.
		while(getIndividualConflict(index) == 0 && calcFitnessVal(board) != 0){
			index = new Random().nextInt(n); //0 - 24 
		}
		return index;
	}
	
	// Finds the number of conflicts in each row the queen could move to, and moves to the Queen to the row with the least conflicts.
	// IF there are multiple indexes with minimal conflicts, it will randomly select one of the least conflict indexes. 
	public QueenBoard moveToLowestConflictIndex(int column){
		int[]conflict = new int[n];
		int lowestConflict= Integer.MAX_VALUE; // For starting value
		ArrayList<Integer> lowestIndexArray = new ArrayList<Integer>(); 
		int lowestValueConflict = 0;
		int lowestIndex = 0;
		
		//System.out.println("\nOriginal Board Before Alter: " + Arrays.toString(board) + " Column to Alter " + column);
		for(int i = 0; i < n; i++){
			
			board[column] = i; //NOTE: THIS ALTERS THE ORIGINAL BOARD, BUT WE TECHNICALLY ARE CHANGING IT LATER ANYWAYS SO DONT CARE.
			int numConflict =  getIndividualConflict(column);
			conflict[i] = numConflict;
			
			
			if( numConflict < lowestConflict ){
				lowestConflict = numConflict;
				lowestValueConflict = conflict[i];
				
			}
		}
		
		//In  the case where there are multiple lowest conflict indexes with the same number, add them to a list.
		for(int i = 0; i<n; i++){
			if(conflict[i] == lowestValueConflict)
				lowestIndexArray.add(i);
		}
		
		//Get a random lowest conflict index.
		lowestIndex = lowestIndexArray.get(new Random().nextInt(lowestIndexArray.size())); 
		
		//System.out.println("Conflict Array               " + Arrays.toString(conflict) + " Lowest Index " + lowestIndex + " Lowest Value : " + lowestValueConflict);
		board[column] = lowestIndex; //Move it to lowest index
		//System.out.println("Board after Alter :          " + Arrays.toString(board) + "\n");
		
		return new QueenBoard(board);
	}
	
	// Find the amount of conflicted queens there are with any given queen. 
	private int getIndividualConflict(int column) {
		int numAttackPairs = 0;
		int row = board[column];
				
		for(int i = 0; i< board.length;i++){
			if(column != i){ //If it's not the same column (and thereby the same queen)
				int rise = Math.abs(column- i);
				int run = Math.abs(row - board[i]);
				
				if(rise == run || run == 0){
					numAttackPairs++;
				}
			}
			
		}
		return numAttackPairs;
	}

	
	public int[] getBoard(){
		return board;
	}
	
	//Return the fitness value
	public Integer getFitness(){
		return h;
	}

	@Override 
	public String toString(){
		return Arrays.toString(board) + "  Fitness: " + getFitness();
	}
	
	@Override
	public int compareTo(QueenBoard o){
		return this.getFitness().compareTo(o.getFitness());
	}

	public int getPopulationSize() {
		return n;
	}
}

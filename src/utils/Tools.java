/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package utils;

import java.util.ArrayList;


public class Tools {
	
	private ArrayList<Integer> connected;	
	
	public Tools(){
		connected = new ArrayList<Integer>();
	}
	
	public void startMove(int index){
		connected = new ArrayList<Integer>();
		connected.add(index);
	}
	
	public ArrayList<Integer> getConnected(){
		return connected;
	}
	
	public boolean alreadyFound(int index){
		int size = connected.size();
		for(int x = 0; x < size; x++){
			int found = connected.get(x);
			if(found == index){
				return true;
			}
		}
		return false;
	}
	
	public void findConnected(ArrayList<Integer> board, int index){
		//System.out.println("ACTION FOUND");
		//System.out.println("index: " + index);
		int value = board.get(index);
		if(index % 10 != 9 ){
			//System.out.println("Checking right");
			int nextRow = index + 1;
			int next = board.get(nextRow);
			if(value == next){
				if(!alreadyFound(nextRow)){
					//System.out.println("found connected: " + (index) + " - " + board.get(index) + ", " + (index + 1) + " - " + board.get(index + 1));
					connected.add(nextRow);
					findConnected(board, nextRow);
				}
			}
		}
		
		if(index % 10 != 0){
			//System.out.println("Checking left");
			int previousRow = index - 1;
			int previous = board.get(previousRow);
			if(value == previous){
				if(!alreadyFound(previousRow)){
					//System.out.println("found connected: " + (index) + " - " + board.get(index) + ", " + (index - 1) + " - " + board.get(index - 1));
					connected.add(previousRow);
					findConnected(board, previousRow);
				}
			}
		}
		
		if(index + 10 < 100){
			//System.out.println("Checking bottom");
			int nextColumn = index + 10;
			int next = board.get(nextColumn);
			if(value == next){
				if(!alreadyFound(nextColumn)){
					//System.out.println("found connected: " + (index) + " - " + board.get(index) + ", " + (index + 10) + " - " + board.get(index + 10));
					connected.add(nextColumn);
					findConnected(board, nextColumn);
				}
			}
		}
		
		if(index - 10 >= 0){
			//System.out.println("Checking top");
			int previousColumn = index - 10;
			int previous = board.get(previousColumn);
			if(value == previous){
				if(!alreadyFound(previousColumn)){
					//System.out.println("found connected: " + (index) + " - " + board.get(index) + ", " + (index - 10) + " - " + board.get(index - 10));
					connected.add(previousColumn);
					findConnected(board, previousColumn);
				}
			}
		}
	}
}

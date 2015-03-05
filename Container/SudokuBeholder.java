package Container;

import Board.Board;

public class SudokuBeholder {
	private Board head;
	private int solutionCount;
	
	public SudokuBeholder() {
		head = null;
	}
	
	public int getCount() {
		return solutionCount;
	}
	
	public void increaseCount() {
		solutionCount++;
	}
	
	public void input(Board b) {
		Board temp = head;
		
		if(temp == null) {
			head = b;
		} else {
			while(temp.getNext() != null) {
				if(temp.getNext() == null) {
					temp.setNext(b);
				}
				temp = temp.getNext();
			}
		}
	}
	
	public Board getSolution() {
		return head;
	}
	
}

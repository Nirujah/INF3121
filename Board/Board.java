package Board;

import Board.Pieces.*;
import Container.SudokuBeholder;

public class Board {
	private Square[][]  sq = null;
	private Box[] box = null;
	private Row[] row = null;
	private Column[] column = null;
	private int sizeX;
	private int sizeY;
	private Board next;
	
	public Board(int x, int y) {
		sq = new Square[x*y][y*x];
		box = new Box[x*y];
		row = new Row[x*y];
		column = new Column[x*y];
		sizeX = x;
		sizeY = y;
		next = null;
		fillBoard();
		fillArrays();
		chainSquares();
	}
	
	
	public void fillBoard() {
		for(int i = 0; i < sq.length; i++) {
			for(int j = 0; j < sq[0].length; j++) {
				sq[i][j] = new EmptySquare();
			}
		}
	}
	
	public void chainSquares() {
		for(int i = 0; i < sq.length; i++) {
			for(int j = 0; j < sq[0].length; j++) {
				if(i < sq.length && j+1 < sq[0].length)
					sq[i][j].setNext(sq[i][j+1]);
				else if(i+1 < sq.length && j < sq[0].length)
					sq[i][j].setNext(sq[i+1][0]);
			}
		}
	}
	
	public void fillArrays() {
		for(int i = 0; i < box.length; i++) {
			box[i] = new Box(box.length);
			row[i] = new Row(row.length);
			column[i] = new Column(column.length);
		}
	}
	
	public void solve(Board b, int pos, SudokuBeholder sb) {
		sq[0][0].fillInnRemainingOfBoard(b, 0, sb);
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public Box[] getBox() {
		return box;
	}
	
	public Row[] getRow() {
		return row;
	}
	
	public Column[] getColumn() {
		return column;
	}
	
	public void setNext(Board b) {
		next = b;
	}
	
	public Board getNext() {
		return next;
	}
	
	public void addFilledSquare(int posX, int posY, PrefilledSquare pfsq) {
		int pos = posX * (sizeX * sizeY) + posY;
		int posColumn = (int) Math.floor(pos % (sizeX * sizeY));
		int posRow = (int) Math.floor(pos / (sizeY * sizeX));
		int posBox = (int) (Math.floor(posRow / sizeY) * sizeX + Math.floor(posColumn / sizeX));
		
		sq[posX][posY] = pfsq;
		sq[posX][posY].insertToAll(box, row, column, posColumn, posRow, posBox);
		
		//Previous square points to this square
		if(posY == 0 && posX != 0)
			sq[posX-1][sq[0].length-1].setNext(sq[posX][posY]);
		else if((posY-1) > -1)
			sq[posX][posY-1].setNext(sq[posX][posY]);
		
		//Point to the next square
		if(posX < sq.length && posY+1 < sq[0].length)
			sq[posX][posY].setNext(sq[posX][posY+1]);
		else if(posX+1 < sq.length && posY < sq[0].length)
			sq[posX][posY].setNext(sq[posX+1][0]);
		
	}
	
	public void printBoard() {
		for(int i = 0; i < sq.length; i++) {
			for(int j = 0; j < sq[0].length; j++) {
				System.out.print(sq[i][j].getValue() + " ");
			}
			System.out.println();
		}
	}
}

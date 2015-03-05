package Board;

import Board.Pieces.*;
import Container.SudokuBeholder;

public class Square {
	protected int value;
	protected Square next = null;
	
	public Square() {
		
	}
	
	public void setNext(Square sq) {
		next = sq;
	}
	
	public Square getNext() {
		return next;
	}
	
	public int getValue() {
		return value;
	}
	
	public void fillInnRemainingOfBoard(Board b, int pos, SudokuBeholder sb) {
		int column = (int) Math.floor(pos % (b.getSizeX() * b.getSizeY()));
		int row = (int) Math.floor(pos / (b.getSizeY() * b.getSizeX()));
		int box = (int) (Math.floor(row / b.getSizeY()) * b.getSizeX() + Math.floor(column / b.getSizeX()));
		
		if(sb.getCount() < 1) { //Finner bare en løsning for å teste, men programmet kan fint finne flere løsninger
			if(this.getClass().equals(EmptySquare.class)) {
				for(int i = 1; i <= (b.getSizeX() * b.getSizeY()); i++) {
					if(!b.getColumn()[column].inArray(i) && !b.getRow()[row].inArray(i) && !b.getBox()[box].inArray(i)) {
						value = i;
						insertToAll(b.getBox(), b.getRow(), b.getColumn(), column, row, box);
						if(next != null) {
							next.fillInnRemainingOfBoard(b, ++pos, sb);
						} else {
							b.printBoard(); //<-- Printer b, b har riktige verdier
							sb.input(b); //<-- b puttes in i beholderen, men når jeg printer b fra beholderen så har den feil verdier
							sb.increaseCount();
							System.out.println();
						}
						removeFromAll(b.getBox(), b.getRow(), b.getColumn(), column, row, box);
						pos--;
					}	
				}
			} else {
				if(next != null) {
					next.fillInnRemainingOfBoard(b, ++pos, sb);
				}
				else {
					sb.input(b);
					sb.increaseCount();
					b.printBoard();
					System.out.println();
				}
			}
		}
	}
	
	public void insertToAll(Box[] b, Row[] r, Column[] c, int posColumn, int posRow, int posBox) {
		b[posBox].input(value);
		r[posRow].input(value);
		c[posColumn].input(value);
	}
	
	public void removeFromAll(Box[] b, Row[] r, Column[] c, int posColumn, int posRow, int posBox) {
		b[posBox].remove(value);
		r[posRow].remove(value);
		c[posColumn].remove(value);
	}
	
}




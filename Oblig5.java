//Programmet tar ikke input og har heller ikke GUI
//Det kan finne alle løsninger av en Sudoku av forskjellige størellser
//så lenge x = y (Jeg har miksett opp x og y ett par steder i koden, ellers hadde det funket fint viss x != y)
//Av enn eller annen merkelig grunn lagres ikke ett brett riktig i beholderen.
//Programmet kan finne løsninger for sudokuer med ferdig utfylte verdier.

import Board.*;
import Board.Pieces.*;
import Container.SudokuBeholder;

public class Oblig5 {
	public static void main(String arg[]) {
		
		Board b = new Board(2, 2);
		SudokuBeholder sb = new SudokuBeholder();
		
		//b.addFilledSquare(0, 0, new PrefilledSquare(2));
		//b.addFilledSquare(3, 3, new PrefilledSquare(3));
		//b.addFilledSquare(2, 1, new PrefilledSquare(1));
		b.solve(b, 0, sb);
		//b.printBoard();
		
		//sb.getSolution().printBoard(); <----- Printer fra beholderen, men verdiene her feil
	}
}

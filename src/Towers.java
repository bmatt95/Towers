import java.util.Scanner;

/**
 * Towers - 
 *
 * @author Matthew Ess <mess@purdue.edu>
 * @lab 814
 * @date Nov 13, 2014
 */

public class Towers {
	class Pole {
		int[] markers;
		Pole(boolean isFirst) {
			if (isFirst) {
				markers = new int[3];
				for (int i = 0; i < markers.length; i++) {
					markers[i] = i + 1;
				}
			} else {
				markers = new int[3];
			}
		}

		boolean isAligned() {
			if (markers[0] < markers[1] && markers[1] < markers[2] && markers[0] != 0 && markers[1] != 0 && markers[2] != 0) {
				return true;
			}
			return false;
		}
	}

	Pole[] board;

	Towers() {
		this.board = new Pole[3];
		board[0] = new Pole(true);
		board[1] = new Pole(false);
		board[2] = new Pole(false);
	}

	void play() {
		Scanner s = new Scanner(System.in);
		printBoard("");
		while (!hasWon()) {
			int from = s.nextInt();
			int to = s.nextInt();
			printBoard(move(from - 1, to - 1));
		}
		s.close();
		System.out.println("You win!");
	}

	boolean hasWon() {
		if (board[2].isAligned() || board[1].isAligned()) {
			return true;
		}
		return false;
	}

	String move(int from, int to) {
		if (from == to) {
			return "Move from one pole to another!\n";
		}
		int markerFrom = -1;
		int markerTo = -1;
		for (int i = 0; i < board[from].markers.length; i++) {
			if (board[from].markers[i] > 0) {
				markerFrom = i;
				break;
			}
		}
		if (board[to].markers[0] == 0) {
			for (int i = board[to].markers.length - 1; i >= 0; i--) {
				if (board[to].markers[i] == 0) {
					markerTo = i;
					break;
				}
			}
		}
		if (markerFrom < 0) {
			return "No marker to move!\n";
		} else if (markerTo < 2 && board[from].markers[markerFrom] > board[to].markers[markerTo + 1]) {
			return "Marker is too big!\n";
		}
		board[to].markers[markerTo] = board[from].markers[markerFrom];
		board[from].markers[markerFrom] = 0;
		return "";
	}

	void printBoard(String message) {
		String[] strings = {"" , "" , ""};
		for (Pole pole : board) {
			for (int i = 0; i < strings.length; i++) {
				switch(pole.markers[i]) {
				case (0):
					strings[i] += "        ";
				break;
				case (1):
					strings[i] += "   []   ";
				break;
				case (2):
					strings[i] += "  [  ]  ";
				break;
				case (3):
					strings[i] += " [    ] ";
				}
			}
		}
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
		for (String string : strings) {
			System.out.println(string);
		}
		System.out.println(" --||--  --||--  --||--");
		System.out.print(message);
	}

	public static void main(String[] args) {
		Towers t = new Towers();
		t.play();
	}
}

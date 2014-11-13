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
		Pole(boolean isFirst, int size) {
			if (isFirst) {
				markers = new int[size];
				for (int i = 0; i < markers.length; i++) {
					markers[i] = i + 1;
				}
			} else {
				markers = new int[size];
			}
		}

		boolean isAligned() {
			for (int i = 0; i < markers.length - 2; i++) {
				if (markers[i] > markers[i + 1] || markers[i] == 0 || markers [i + 1] == 0) {
					return false;
				}
			}
			return true;
		}
	}

	Pole[] board;
	static Scanner s = new Scanner(System.in);

	Towers(int size) {
		this.board = new Pole[3];
		board[0] = new Pole(true, size);
		board[1] = new Pole(false, size);
		board[2] = new Pole(false, size);
	}

	void play() {
		printBoard("");
		while (!hasWon()) {
			int from = s.nextInt();
			int to = s.nextInt();
			printBoard(move(from - 1, to - 1));
		}
		System.out.println("You win!\nPlay again? (y/n)");
		if (s.next().equals("y")) {
			reset();
		}
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
		} else if (markerTo < board[0].markers.length - 1 && board[from].markers[markerFrom] > board[to].markers[markerTo + 1]) {
			return "Marker is too big!\n";
		}
		board[to].markers[markerTo] = board[from].markers[markerFrom];
		board[from].markers[markerFrom] = 0;
		return "";
	}

	void printBoard(String message) {
		String[] strings = new String[board[0].markers.length];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = "";
		}
		for (Pole pole : board) {
			for (int i = 0; i < strings.length; i++) {
				if (pole.markers[i] == 0) {
					strings[i] += "           ";
				} else if (pole.markers[i] == 1) {
					strings[i] += String.format("    [%d]    ", pole.markers[i]);
				} else if (pole.markers[i] == 2) {
					strings[i] += String.format("   [ %d ]   ", pole.markers[i]);
				} else if (pole.markers[i] == 3) {
					strings[i] += String.format("  [  %d  ]  ", pole.markers[i]);
				} else {
					strings[i] += String.format(" [   %d   ] ", pole.markers[i]);
				} 
			}
		}
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
		for (String string : strings) {
			System.out.println(string);
		}
		System.out.println(" ---| |---  ---| |---  ---| |---");
		System.out.print(message);
	}

	void reset() {
		System.out.println("Size of tower?");
		Towers t = new Towers(s.nextInt());
		t.play();
	}

	public static void main(String[] args) {
		System.out.println("Size of tower?");
		Towers t = new Towers(s.nextInt());
		t.play();
		s.close();
	}
}

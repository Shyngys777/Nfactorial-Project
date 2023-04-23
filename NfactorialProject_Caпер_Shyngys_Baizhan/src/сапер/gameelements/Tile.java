package сапер.gameelements;

public class Tile {

	private Status status;
	private int number;
	
	private boolean wrong;
	
	public Tile() {
		status = Status.UNKNOWN;
		number = 0;
		wrong = false;
	}

	public void flag() {
		this.status=Status.FLAGGED;
	}
	

	public void unflag() {
		if(this.status == Status.FLAGGED) status = Status.UNKNOWN;
	}

	public boolean isFlagged() {
		return this.status == Status.FLAGGED;
	}

	public boolean isOpen() {
		return this.status == Status.OPEN;
	}

	public boolean hasMine() {
		return number == -1;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// Открвыть ячейку
	public void open() {
		this.status = Status.OPEN;
	}

	// Откпыть если нет бомбы внутри ячейки или если статус анноун
	public boolean canOpen() {
		return number>=0 && status==Status.UNKNOWN;
	}

	public boolean isWrong() {
		return number!=-1 && status==Status.FLAGGED && wrong==true;
	}

	public void displayWrong(boolean d) {
		this.wrong=d;
	}
	
	private enum Status {
		UNKNOWN,
		OPEN,
		FLAGGED;
	}
}

package model;

public class Snake {
	
	private char letter;
	private Node begin;
	private Node end;	
	
	public Snake(char letter, Node begin, Node end) {
		this.letter = letter;
		this.begin = begin;
		this.end = end;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public Node getBegin() {
		return begin;
	}

	public void setBegin(Node begin) {
		this.begin = begin;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}
	

}

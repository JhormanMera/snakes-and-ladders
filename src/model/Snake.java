package model;
import java.io.Serializable;

public class Snake implements Serializable {

	private static final long serialVersionUID = -3881140176055075259L;
	private char letter;
	private Node begin;
	private Node end;	
	
	public Snake(char letter) {
		this.letter=letter;
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

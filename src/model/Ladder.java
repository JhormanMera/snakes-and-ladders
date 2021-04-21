package model;

public class Ladder {
	
	private int id;
	private Node begin;
	private Node end;
	
	
	public Ladder(int id, Node begin, Node end) {
		this.id = id;
		this.begin = begin;
		this.end = end;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

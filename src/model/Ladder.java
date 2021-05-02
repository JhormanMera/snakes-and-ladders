package model;
import java.io.Serializable;

public class Ladder implements Serializable {

	private static final long serialVersionUID = -2686535498367370184L;
	private int id;
	private Node begin;
	private Node end;
	
	
	public Ladder(int id) {
		this.id = id;
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

package model;

public class Node {
	
	private Node up;
	private Node down;
	private Node pre;
	private Node post;
	private int row;
	private int col;
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node getPre() {
		return pre;
	}

	public void setPre(Node pre) {
		this.pre = pre;
	}

	public Node getPost() {
		return post;
	}

	public void setPost(Node post) {
		this.post = post;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public String toString(){
		return "[("+row+","+col+")]";
	} 
}

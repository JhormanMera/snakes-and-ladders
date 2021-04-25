package model;

public class Node {
	
	private Node up;
	private Node down;
	private Node pre;
	private Node post;
	private Ladder ladder;
	private Snake snake;
	private int row;
	private int col;
	private int id;
	private String playersID;

	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		this.id=0;
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
		return "[("+id+")]";
	}

	public String getPlayersID() {
		return playersID;
	}

	public void setPlayersID(String playersID) {
		this.playersID = playersID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ladder getLadder() {
		return ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	} 
}

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
	private Player list;

	
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
		if(id<10&&ladder==null&&snake==null) {
			return "[ "+id+" ]";
		}else if(id<10&&ladder!=null) {
			return "["+id+ladder.getId()+"]";
		}else if(id<10&&snake!=null) {
			return "["+id+snake.getLetter()+"]";
		}else {
			return "["+id+" ]";
		}
		
	}
	
	public String container() {
		if(ladder!=null && list!=null) {
			return "["+ladder.getId()+list.getPartner(list, list.getSymbol()+"")+" ]";
		}else if(snake!=null && list!=null) {
			return "["+snake.getLetter()+list.getPartner(list, list.getSymbol()+"")+" ]";
		}else if(list!=null){
			return "[ "+list.getPartner(list, list.getSymbol()+"")+" ]";
		}else {
			return "[   ]";
		}
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
	
	public Player getList() {
		return list;
	}

	public void setList(Player list) {
		this.list = list;
	}
	

}

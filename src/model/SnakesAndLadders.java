package model;

public class SnakesAndLadders {
	
	private static final String SYMBOLS="*!OX%$#+&";
	private Node first;
	private Player root;
	private Player one;
	private int rowsAmount;
	private int colsAmount;
	


	public SnakesAndLadders( int rowsAmount, int colsAmount) {
		this.rowsAmount = rowsAmount;
		this.colsAmount = colsAmount;
		createGameBoard();
	}

	public void createGameBoard() {
		first= new Node(0,0);
		createRow(0,0,first);

	}
	
	public void generatePlayers() {
		
	}
	
	public void assignPlayers() {
		
	}
	
	private void createRow(int i, int j, Node firstRow){ 
		createCol(i,j+1,firstRow);
		if(i+1 < rowsAmount){
			Node firstDownRow = new Node(i+1,j);
			firstDownRow.setUp(firstRow);
			firstRow.setDown(firstDownRow);
			createRow(i+1, j, firstDownRow);
		}
	}

	private void createCol(int i, int j, Node left){ 
		if(j<colsAmount){
			Node current = new Node(i,j);
			current.setPre(left);
			left.setPost(current);

			/*if(rowPrev != null){
	                rowPrev = rowPrev.getRight();
	                current.setTop(rowPrev);
	                rowPrev.setBottom(current);
	            }*/

			createCol(i,j+1, current);
		}
	}
	@Override
	public String toString(){
		String msg = "";
		msg = printRow(first);
		return msg;
	}

	public String printRow(Node firstRow){
		String msg = "";
		if(firstRow != null){
			msg = printCol(firstRow) + "\n";
			msg += printRow(firstRow.getDown());
		}
		return msg;
	}

	public String printCol(Node current){
		String msg = "";
		if(current != null){
			msg = current.toString();
			msg += printCol(current.getPost());
		}

		return msg;
	}    
	
	public int generateDice(){
		int dice = (int) Math.floor(Math.random()*(6-1+1)+1); 
		return dice;
	}
	
	public void addPlayer(Player player){
		if(root == null){
			root = player;
		}else{
			addPlayer(root, player);
		}
	}
	
	private void addPlayer(Player current, Player newPlayer){
		if(newPlayer.getScore() <= current.getScore()){
			if(current.getLeft() == null){
				current.setLeft(newPlayer);
				newPlayer.setParent(current);
			} else{
				addPlayer(current.getLeft(), newPlayer);
			}
		} else{
			if(current.getRight() == null){
				current.setRight(newPlayer);
				newPlayer.setParent(current);
			} else{
				addPlayer(current.getRight(), newPlayer);
			}			
		}
	}
	
	public Player searchPlayer(int score){
		return searchPlayer(root, score);
	}
	
	private Player searchPlayer(Player current, int score){
		if(current == null || current.getScore() == score){
			return current;
		}else if(current.getScore() < score){
			return searchPlayer(current.getRight(), score);
		}else{
			return searchPlayer(current.getLeft(), score);
		}
	}
	
	public void removePlayer(int score){
		Player rmvPlayer = searchPlayer(score);
		removePlayer(rmvPlayer);
	}
	
	private void removePlayer(Player rmvPlayer){
		if(rmvPlayer!=null){
			if(rmvPlayer.getLeft()==null && rmvPlayer.getRight()==null){ //Case 1
				if(rmvPlayer == root){
					root = null;
				}else if(rmvPlayer.getParent().getLeft() == rmvPlayer){
					rmvPlayer.getParent().setLeft(null);
				}else{
					rmvPlayer.getParent().setRight(null);
				}
				rmvPlayer.setParent(null);
			}else if(rmvPlayer.getLeft()==null || rmvPlayer.getRight()==null){ //Case 2
				Player onlySon;
				if(rmvPlayer.getLeft()!=null){
					onlySon = rmvPlayer.getLeft();
				}else{
					onlySon = rmvPlayer.getRight();
				}
				onlySon.setParent(rmvPlayer.getParent());
				if(rmvPlayer == root){
					root = onlySon;
				}else if(rmvPlayer.getParent().getLeft() == rmvPlayer){
					rmvPlayer.getParent().setLeft(onlySon);
				}else{
					rmvPlayer.getParent().setRight(onlySon);
				}
			}else{ //Case 3
				Player successor = min(rmvPlayer.getRight());
				rmvPlayer.setScore(successor.getScore());
				rmvPlayer.setMoves(successor.getMoves());
				rmvPlayer.setSimbol(successor.getSimbol());
				rmvPlayer.setNickName(successor.getNickName());
				removePlayer(successor);
			}
		}
	}
	
	private Player min(Player current){
		if(current.getLeft()==null){
			return current;
		}else{
			return min(current.getLeft());
		}
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public int getRowsAmount() {
		return rowsAmount;
	}

	public void setRowsAmount(int rowsAmount) {
		this.rowsAmount = rowsAmount;
	}

	public int getColsAmount() {
		return colsAmount;
	}

	public void setColsAmount(int colsAmount) {
		this.colsAmount = colsAmount;
	}

	public static String getSymbols() {
		return SYMBOLS;
	}

	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}

	public Player getOne() {
		return one;
	}

	public void setOne(Player one) {
		this.one = one;
	}
	
	

}

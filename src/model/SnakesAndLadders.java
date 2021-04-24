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
		if(one == null){
			one = player;
		}else{
			addPlayer(one, player);
		}
	}
	
	
	private void addPlayer(Player current, Player newPlayer){
		if(current.getPostPlayer() == null){
				current.setPostPlayer(newPlayer);
				newPlayer.setPrePlayer(current);
			} else{
				addPlayer(current.getPostPlayer(), newPlayer);
			}
		}
	
	public Player searchPlayer(String simbol){
		return searchPlayer(one, simbol);
	}
	
	private Player searchPlayer(Player current, String simbol){
		if(current == null || current.getSimbol() == simbol){
			return current;
		}else{
			return searchPlayer(current.getPostPlayer(), simbol);
		}
	}
	
	
//-------------------------------------------------------------------------------------------------------------	
	public void addWinner(Player player){
		if(root == null){
			root = player;
		}else{
			addWinner(root, player);
		}
	}
	
	private void addWinner(Player current, Player newWinner){
		if(newWinner.getScore() <= current.getScore()){
			if(current.getLeft() == null){
				current.setLeft(newWinner);
				newWinner.setParent(current);
			} else{
				addWinner(current.getLeft(), newWinner);
			}
		} else{
			if(current.getRight() == null){
				current.setRight(newWinner);
				newWinner.setParent(current);
			} else{
				addWinner(current.getRight(), newWinner);
			}			
		}
	}
	
	public Player searchWinner(int score){
		return searchWinner(root, score);
	}
	
	private Player searchWinner(Player current, int score){
		if(current == null || current.getScore() == score){
			return current;
		}else if(current.getScore() < score){
			return searchWinner(current.getRight(), score);
		}else{
			return searchWinner(current.getLeft(), score);
		}
	}
	
	public void removeWinner(int score){
		Player rmvWinner = searchWinner(score);
		removeWinner(rmvWinner);
	}
	
	private void removeWinner(Player rmvWinner){
		if(rmvWinner!=null){
			if(rmvWinner.getLeft()==null && rmvWinner.getRight()==null){ //Case 1
				if(rmvWinner == root){
					root = null;
				}else if(rmvWinner.getParent().getLeft() == rmvWinner){
					rmvWinner.getParent().setLeft(null);
				}else{
					rmvWinner.getParent().setRight(null);
				}
				rmvWinner.setParent(null);
			}else if(rmvWinner.getLeft()==null || rmvWinner.getRight()==null){ //Case 2
				Player onlySon;
				if(rmvWinner.getLeft()!=null){
					onlySon = rmvWinner.getLeft();
				}else{
					onlySon = rmvWinner.getRight();
				}
				onlySon.setParent(rmvWinner.getParent());
				if(rmvWinner == root){
					root = onlySon;
				}else if(rmvWinner.getParent().getLeft() == rmvWinner){
					rmvWinner.getParent().setLeft(onlySon);
				}else{
					rmvWinner.getParent().setRight(onlySon);
				}
			}else{ //Case 3
				Player successor = min(rmvWinner.getRight());
				rmvWinner.setScore(successor.getScore());
				rmvWinner.setMoves(successor.getMoves());
				rmvWinner.setSimbol(successor.getSimbol());
				rmvWinner.setNickName(successor.getNickName());
				removeWinner(successor);
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

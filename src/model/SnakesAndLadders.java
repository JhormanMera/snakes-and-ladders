package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SnakesAndLadders {
	
	private static final String INFORMATION_PATH_FILE="data/information.ptjm";
	private static final String SYMBOLS="*!OX%$#+&";
	private static final String ABC ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Node first;
	private Player root;
	private Player one;
	private int rowsAmount;
	private int colsAmount;
	
	public SnakesAndLadders() {
		try {
			loadData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// ---------------------------------------------SERIALIZABLE----------------------------------------------------------
	
	public void saveData() throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INFORMATION_PATH_FILE));
		oos.writeObject(root);
		oos.close();
	}
	
	//@SuppressWarnings("unchecked") 
	public boolean loadData() throws IOException, ClassNotFoundException {
		File f = new File(INFORMATION_PATH_FILE);
		boolean loaded = false;
		if (f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			root= (Player) ois.readObject(); 
			ois.close();
			loaded = true;
		}
		return loaded;
	}
	
//---------------------------------------------START GAME--------------------------------------------------------------------------------------------------------------
	
	/** 
	*  Create the game board <br>
	* <b> pre: The parameters must to be positive integers </b> 
	* <b> post: Create the game board size: rowsAmount*colsAmount and prints the the game board overview</b> 
	* @param rowsAmount type int
	* @param colsAmount type int
	*/	
	public void createGameBoard(int rowsAmount, int colsAmount) {
		this.rowsAmount=rowsAmount;
		this.colsAmount=colsAmount;
		first= new Node(0,0);
		createRow(0,0,first);
		matrixEnum(first);
	}
	
	/** 
	*  Create the rows and make the recursive call of 'createColums' to create the columns inside this method <br>
	* <b> pre: The object 'firstRow' must to be != null </b> 
	* <b> post: Create the rows and columns indicated by the user</b> 
	* @param i type int
	* @param j type int
	* @param firstRow type Node
	*/	
	private void createRow(int i, int j, Node firstRow){ 
		createCol(i,j+1,firstRow,firstRow.getUp());
		if(i+1 < rowsAmount){
			Node firstDownRow = new Node(i+1,j);
			firstDownRow.setUp(firstRow);
			firstRow.setDown(firstDownRow);
			createRow(i+1, j, firstDownRow);
		}
	}

	/** 
	*  Create the columns <br>
	* <b> pre: The object 'rowPrev' must to be != null </b> 
	* <b> post: Create the columns indicated by the user</b> 
	* @param i type int
	* @param j type int
	* @param rowPrev type Node
	*/	
	private void createCol(int i, int j, Node left, Node rowPrev){ 
		if(j<colsAmount){
			Node current = new Node(i,j);
			current.setPre(left);
			left.setPost(current);

			if(rowPrev != null){
				rowPrev = rowPrev.getPost();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			createCol(i,j+1, current, rowPrev);
		}
	}

	/** 
	* Transform columns and rows to string <br>
	* <b> pre: The complete game board must have been created before </b> 
	* @return Return the initial content of the game board inside a String
	*/	
	@Override
	public String toString(){
		String msg = "";
		msg = printRow(first);
		return msg;
	}

	/** 
	* The id are put to each of the nodes of the matrix <br>
	* <b> pre: The complete game board must have been created before </b> 
	* @param i type int
	* @return Return the initial content of the game board inside a String
	*/	
	public void matrixEnum(Node firstNode) {
		matrixFirstRow(firstNode);
	}

	/** 
	* Look for the initial node to put the id 1, and also recursively call the 'matrixRightRow' method in order to assign the id to the rest of the nodes within this method <br>
	* <b> pre: The complete game board must have been created before </b> 
	* <b> post: Assigns the id to the initial node and assigns the id to the other nodes by the recursive call </b>
	* @param fistRow type Node
	*/	
	public void matrixFirstRow(Node firstRow) {
		if (firstRow.getDown() != null) {
			matrixFirstRow(firstRow.getDown());
		} else {
			firstRow.setId(1);
			matrixRightRow(firstRow);
		}
	}

	public void matrixRightRow(Node rightRow) {
		if (rightRow.getPost() != null) {
			rightRow.getPost().setId(rightRow.getId() + 1);
			matrixRightRow(rightRow.getPost());
		} else if (rightRow.getUp() != null) {
			rightRow.getUp().setId(rightRow.getId() + 1);
			matrixLeftRow(rightRow.getUp());
		}
	}

	public void matrixLeftRow(Node leftRow) {
		if (leftRow.getPre() != null) {
			leftRow.getPre().setId(leftRow.getId() + 1);
			matrixLeftRow(leftRow.getPre());
		} else if (leftRow.getUp() != null) {
			leftRow.getUp().setId(leftRow.getId() + 1);
			matrixRightRow(leftRow.getUp());
		}
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
	
//-------------------------------------------GAME ITEMS--------------------------------------------------------------
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
	
//-----------------------------------------PLAYERS-----------------------------------------------------------------------
	
	public void generatePlayers(int start,int num) {
		if(start<num) {
			char a = SYMBOLS.charAt(start);
			Player newPlayer = new Player(a);
			addPlayer(newPlayer);
			generatePlayers(start+1,num);
		}
	}

	public void assignPlayers(int start,String sym) {
		if (sym.length()<=SYMBOLS.length()&&start<sym.length()) {
			Player newPlayer = new Player(sym.charAt(start));	
			addPlayer(newPlayer);
			assignPlayers(start+1,sym);
		}
	}

	public void playerMove(Player player,int dice) {

	}
	
//----------------------------------------------------NODE--------------------------------------------------------------------
  
	
	public Node searchNode(int id){
		return searchNode(first, id);
	}

	private Node searchNode(Node current, int id){
		if(current.getPost()!=null && current.getId()<id){
			return searchNode(current.getPost(),id);
		}else if(current.getPost()==null && current.getUp()!=null && current.getId()<id) {
			return searchNode(current.getUp(),id);
		}else{
			return current;
		}
	}
	
//-----------------------------------------------DICE------------------------------------------------------------------------------------------------------------------------------
	
	public int generateDice(){
		int dice = (int) Math.floor(Math.random()*(6-1+1)+1); 
		return dice;
	}
	
//-----------------------------------------------SNAKES--------------------------------------------------------------------------------------------------------------------
	
	public void setSnakes(int currentSnakes,int createdSnakes){
		if(currentSnakes>createdSnakes){
			Snake newSnake=new Snake(ABC.charAt(createdSnakes));
			setHead(newSnake);
			setTail(newSnake);
			setSnakes(currentSnakes,createdSnakes+1);
		}
	}

	private void setHead(Snake newSnake){
		int range = ((rowsAmount*colsAmount)-1)+1;
		int position = (int) Math.floor(Math.random()*range+1); 
		if(searchNode(position).getLadder()==null&&
				searchNode(position).getSnake()==null&&
				position!=(rowsAmount*colsAmount)&&
				position>colsAmount) {

			searchNode(position).setSnake(newSnake);
			newSnake.setBegin(searchNode(position));
		}else {
			setHead(newSnake);
		}

	}

	private void setTail(Snake newSnake){
		int range = ((rowsAmount*colsAmount)-1)+1;
		int position = (int) Math.floor(Math.random()*range+1); 
		if(searchNode(position).getLadder()==null&&
				searchNode(position).getSnake()==null&&
				position<newSnake.getBegin().getId()-colsAmount) {

			searchNode(position).setSnake(newSnake);
			newSnake.setEnd(searchNode(position));
		}else {
			setTail(newSnake);
		}
	}
	 
//----------------------------------------------LADDERS--------------------------------------------------------------------------------------------------------------------------------------------
	   
	 public void setLadders(int currentLadders, int createdLadders){
		 if(currentLadders>createdLadders){
			 Ladder newLadder = new Ladder (createdLadders+1);
			 setBase(newLadder);
			 setTop(newLadder);
			 setLadders(currentLadders,createdLadders+1);
		 }

	 }

	 private void setBase(Ladder newLadder){
		 int range = ((rowsAmount*colsAmount)-1)+1;
		 int position = (int) Math.floor(Math.random()*range+1);
		 if(searchNode(position).getLadder()==null&&
				 searchNode(position).getSnake()==null&&
				 position>1&&
				 position<(rowsAmount*colsAmount)-colsAmount) {
			 newLadder.setBegin(searchNode(position));
			 searchNode(position).setLadder(newLadder);
		 }else {
			 setBase(newLadder);
		 }
	 }

	 private void setTop(Ladder newLadder){
		 int range = ((rowsAmount*colsAmount)-1)+1;
		 int position = (int) Math.floor(Math.random()*range+1);
		 if(searchNode(position).getLadder()==null&&
				 searchNode(position).getSnake()==null&&
				 position>(newLadder.getBegin().getId()+colsAmount)) {
			 newLadder.setEnd(searchNode(position));
			 searchNode(position).setLadder(newLadder);
		 }else {
			 setTop(newLadder);
		 }
	 }

//--------------------------------------------LINKED LIST------------------------------------------------------------------------
	
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

	 public Player searchPlayer(char simbol){
		 return searchPlayer(one, simbol);
	 }

	 private Player searchPlayer(Player current, char simbol){
		 if(current == null || current.getSymbol() == simbol){
			 return current;
		 }else{
			 return searchPlayer(current.getPostPlayer(), simbol);
		 }
	 }


//-----------------------------------------BINARY SEARCH TREE---------------------------------------------------------------------------------------------	
	
	 public void addWinner(Player player) throws ClassNotFoundException, IOException{
		 if(root == null){
			 root = player;
		 }else{
			 addWinner(root, player);
		 }
		 saveData();
	 }

	 private void addWinner(Player current, Player newWinner) throws ClassNotFoundException, IOException{
		 if(newWinner.getScore() <= current.getScore()){
			 if(current.getLeft() == null){
				 current.setLeft(newWinner);
				 newWinner.setParent(current);
				 saveData();
			 } else{
				 addWinner(current.getLeft(), newWinner);
			 }
		 } else{
			 if(current.getRight() == null){
				 current.setRight(newWinner);
				 newWinner.setParent(current);
				 saveData();
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
				 rmvWinner.setSymbol(successor.getSymbol());
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

	 public void printWinners(){
		 printWinners(root);
	 }

	 private void printWinners(Player player){
		 if(player == null) {
			 System.out.println("There aren't winners yet"+"\n");             
		 }else {
			 printWinners(player.getLeft());
			 System.out.println(player.toString());
			 printWinners(player.getRight());
		 }
	 }	

}

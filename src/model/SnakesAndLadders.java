package model;

public class SnakesAndLadders {
	
	private static final String SYMBOL_1="*";
	private static final String SYMBOL_2="!";
	private static final String SYMBOL_3="O";
	private static final String SYMBOL_4="X";
	private static final String SYMBOL_5="%";
	private static final String SYMBOL_6="$";
	private static final String SYMBOL_7="#";
	private static final String SYMBOL_8="+";
	private static final String SYMBOL_9="&";
	private Node first;
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
	
	

}

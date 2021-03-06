package model;
import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = -7185338699268148624L;
	private Player parent;
	private Player right;
	private Player left;
	private Player prePlayer;
	private Player postPlayer;
	private Player postInNode;
	private Node current;
	private String nickName;
	private char symbol;
	private int moves;
	private int score;
	
	public Player(char symbol) {
		this.nickName = "Default";
		this.symbol = symbol;
		this.moves = 0;
		this.score=0;
	}	

	
	public Player getParent() {
		return parent;
	}

	public void setParent(Player parent) {
		this.parent = parent;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getMoves() {
		return moves;
	}
	
	public void setMoves(int moves) {
		this.moves = moves;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player getPrePlayer() {
		return prePlayer;
	}

	public void setPrePlayer(Player prePlayer) {
		this.prePlayer = prePlayer;
	}

	public Player getPostPlayer() {
		return postPlayer;
	}

	public void setPostPlayer(Player postPlayer) {
		this.postPlayer = postPlayer;
	}
	
	/** 
	 *  Shows the player with his nickname, symbol and his score <br>
	 * <b> pre: the player must have been created with their respective nickname, symbol and score </b> 
	 * @return a string showing the player with his nickname, symbol and his score
	 */
	@Override
	public String toString() {
		return "Player: "+nickName+"\n"+"Symbol: "+symbol+"\n"+"Score: "+score;
	}
	
	/** 
	 *  Get player symbols on nodes <br>
	 * <b> pre: players must have been created with their respective symbols </b> 
	 * @return a string showing the player with his symbol
	 */
	public String getPartner(Player first, String msg) {
        if (first.getPostInNode() != null) {
            msg += ""+ first.getPostInNode().getSymbol();
            return getPartner(first.getPostInNode(), msg);
        } else {
            return msg;
        }
    }


	public Node getCurrent() {
		return current;
	}


	public void setCurrent(Node current) {
		this.current = current;
	}


	public Player getPostInNode() {
		return postInNode;
	}


	public void setPostInNode(Player postInNode) {
		this.postInNode = postInNode;
	}
	
	/** 
	 *  Remove the player from a specific node <br>
	 * <b> pre: the player must have been created in a respective node </b>
	 * @param player type Player 
	 * @return a boolean true if player was removed
	 */
	public boolean removePlayerNode(Player player) {
		boolean rmv =false;
		if(postInNode==player&&postInNode.getPostInNode()!=null) {
			postInNode=postInNode.getPostInNode();
			rmv=true;
		}else if(postInNode==player&&postInNode.getPostInNode()==null) {
			postInNode=null;
			rmv=true;
		}else if(postInNode!=player&&postInNode.getPostInNode()!=null) {
			removePlayerNode(postInNode.getPostInNode());
		}
		return rmv;
	}
	

}

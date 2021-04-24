package model;

public class Player {
	private Player parent;
	private Player right;
	private Player left;
	private Player prePlayer;
	private Player posPlayer;
	private String nickName;
	private String simbol;
	private int moves;
	private int score;
	
	public Player(String simbol) {
		this.nickName = "Default";
		this.simbol = simbol;
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

	public String getSimbol() {
		return simbol;
	}

	public void setSimbol(String simbol) {
		this.simbol = simbol;
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

	public Player getPosPlayer() {
		return posPlayer;
	}

	public void setPosPlayer(Player posPlayer) {
		this.posPlayer = posPlayer;
	}

	

}

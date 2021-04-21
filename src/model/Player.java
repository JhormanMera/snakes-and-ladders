package model;

public class Player {
	private String nickName;
	private String simbol;
	private int moves;
	
	public Player(String simbol) {
		this.nickName = "Default";
		this.simbol = simbol;
		this.moves = 0;
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

	

}

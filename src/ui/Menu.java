package ui;


import java.util.Scanner;
import model.SnakesAndLadders;


public class Menu {

	private Scanner sc = new Scanner(System.in);

	private static final String SPACE =" ";
	private SnakesAndLadders game;
	public Menu() {
		game = new SnakesAndLadders();
	}
	
	public void showMenu() {
		System.out.println("Welcome to Snakes And Ladders");
		System.out.println("Choose an option");
		System.out.println("(1) Play Snakes and Ladders");
		System.out.println("(2) Watch the ScoreBoard");
		System.out.println("(3) Exit");
	}
	

	public void createGame() {
		System.out.println("Write the game's parameters as follow: ");
		System.out.println("On the same line separated with spaces the amount of rows, columns, snakes, ladders and finally the player's amount or the player's symbols (The symbols must to be together)(Minimum 1 and Maximum 9 Players)");
		System.out.println("The allowed symbols are: * ! O X % $ # + &");
		String parametros = sc.nextLine();
		createWorld(parametros);
	}

	public void createWorld(String parametros){
		String[] parts = parametros.split(SPACE);
		game.createGameBoard(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		try {
			int a = Integer.parseInt(parts[4]);
			game.generatePlayers(0,a);
		}catch(NumberFormatException ex){	
			game.assignPlayers(0,parts[4]);
		}
		System.out.println(game);
	}

	public void doOperation(int option) {
		switch (option) {
		case 1:
			createGame();
			break;
		case 2:
			game.printWinners();
			break;
		default:
			System.out.println("Invalid option, try again");
		}
	}

	public int readOption() {
		int option = sc.nextInt();
		sc.nextLine();
		return option;
	}

	public void startProgram() {
		showMenu();
		int option = readOption();

		if (option == 3){
			System.out.println("Thanks for using this game :)");
		}else{
			doOperation(option);
			startProgram();
		}

	}
}

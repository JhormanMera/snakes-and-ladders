package ui;

import java.util.Scanner;
import model.SnakesAndLadders;


public class Menu {
	private Scanner sc = new Scanner(System.in);

	private static final String SPACE =" ";
	private SnakesAndLadders sL;
	
	public Menu() {
	}

	public void showMenu() {
		System.out.println("Welcome to Snakes And Ladders");
		System.out.println("Choose an option");
		System.out.println("(1) Play Snakes and Ladders");
		System.out.println("(2) Watch the ScoreBoard");
		System.out.println("(3) Exit");
	}
	public void gameChooser() {
		System.out.println(" ");
		System.out.println(" ");
		int choosed = sc.nextInt();
		sc.nextLine();
		switch(choosed){
		case 1:
			createGameAutomatically();
			break;
		case 2:
			createGameManual();
			break;
		default:
			System.out.println("Invalid option, try again");			
		}
	}
	public void createGameAutomatically() {
		System.out.println("Write the game's parameters as follow: ");
		System.out.println("On the same line separated with spaces the amount of rows, columns, snakes, ladders and players (Minimum 1 and Maximum 9 Players)");
		String parametros = sc.nextLine();
		sc.nextLine();
		createWorld(parametros); 
	}
	
	public void createGameManual() {
		System.out.println("Write the game's parameters as follow: ");
		System.out.println("On the same line separated with spaces the amount of rows, columns, snakes, ladders and finally the player's symbols (The symbols must to be together)(Minimum 1 and Maximum 9 Players)");
		System.out.println("The allowed symbols are: * ! O X % $ # + &");
		String parametros = sc.nextLine();
		sc.nextLine();
		createWorld(parametros);
	}

	public void createWorld(String parametros){
		String[] parts = parametros.split(SPACE);
		sL = new SnakesAndLadders(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		System.out.println(sL);
	}

	public void doOperation(int option) {
		switch (option) {
		case 1:
			gameChooser();
			break;

		case 2:
			;
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
			System.out.println("Thanks for using this app");
		}else{
			doOperation(option);
			startProgram();
		}

	}
}

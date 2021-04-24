package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import model.Player;
import model.SnakesAndLadders;


public class Menu {
	private static final String INFORMATION_PATH_FILE="data/information.ptjm";
	private Scanner sc = new Scanner(System.in);

	private static final String SPACE =" ";
	private SnakesAndLadders game;
	
	public Menu() {
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
	
	@SuppressWarnings("unchecked") 
		public boolean loadData() throws IOException, ClassNotFoundException {
			File f = new File(INFORMATION_PATH_FILE);
			boolean loaded = false;
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

				game.setRoot((Player) ois.readObject()); 
				ois.close();
				loaded = true;
			}
			return loaded;
		}

	public void showMenu() {
		System.out.println("Welcome to Snakes And Ladders");
		System.out.println("Choose an option");
		System.out.println("(1) Play Snakes and Ladders");
		System.out.println("(2) Watch the ScoreBoard");
		System.out.println("(3) Exit");
	}
	
	public void gameChooser() {
		System.out.println("Choose an option");
		System.out.println("Assign player's symbols (1) manually or (2) automatically ");
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
		game.generatePlayers();
	}
	
	public void createGameManual() {
		System.out.println("Write the game's parameters as follow: ");
		System.out.println("On the same line separated with spaces the amount of rows, columns, snakes, ladders and finally the player's symbols (The symbols must to be together)(Minimum 1 and Maximum 9 Players)");
		System.out.println("The allowed symbols are: * ! O X % $ # + &");
		String parametros = sc.nextLine();
		sc.nextLine();
		createWorld(parametros);
		game.assignPlayers();
	}

	public void createWorld(String parametros){
		String[] parts = parametros.split(SPACE);
		game = new SnakesAndLadders(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		System.out.println(game);
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

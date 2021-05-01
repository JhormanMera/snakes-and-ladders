package ui;


import java.io.IOException;
import java.util.Scanner;
import model.SnakesAndLadders;


public class Menu {

	private Scanner sc = new Scanner(System.in);

	private static final String SPACE =" ";
	private SnakesAndLadders game;
	public Menu() {
		game = new SnakesAndLadders();
	}

	public void initializeGame(boolean render) {
		game.setVisibility(false);
		if (render == false) {
			System.out.println("Press enter to continue");
			String jump = sc.nextLine();
			if (jump.equals("")) {
				System.out.println(game.generateDice());
				System.out.println(game);
				initializeGame(game.getContPlaying());
			} else if (jump.equals("simul")) {
				System.out.println("Simulation mode has started");
				gameSimulation();
			} else if (jump.equals("menu")) {
				System.out.println("Back to the main menu");
				return;
			} else if (jump.equals("num")) {
				game.setVisibility(true);
				System.out.println(game);
				initializeGame(render);
			} else {
				System.out.println("You must enter a valid option");
				initializeGame(render);
			}
		} else {
			System.out.println("The player "+game.getTemp().getSymbol()+" has won the game");
			calculateWinner();
		}
	}
	
	private void calculateWinner() {
		System.out.println("Enter a NickName");
		game.getTemp().setNickName(sc.nextLine());
		game.calculateScoreWinner();
		try {
			game.addWinner(game.getTemp());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gameSimulation() {
		if (game.getContPlaying()==false) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.generateDice();
			System.out.println(game);
			gameSimulation();
		} else {
			calculateWinner();
		}
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
		game.setOne(null);
		game.createGameBoard(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		int snakes = Integer.parseInt(parts[2]);
		int ladders = Integer.parseInt(parts[3]);
		//game.setSnakes(snakes, 0);
		//game.setLadders(ladders, 0);
		try {
			int a = Integer.parseInt(parts[4]);
			game.setPlayersAmount(a);
			game.generatePlayers(0,a);
		}catch(NumberFormatException ex){
			game.setPlayersAmount(parts[4].length());
			game.assignPlayers(0,parts[4]);
		}
		System.out.println(game);
		initializeGame(false);
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

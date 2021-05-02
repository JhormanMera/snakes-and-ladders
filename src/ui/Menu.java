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

	/** 
	 * Read the game options <br>
	 * <b> pre: The user can't choose an option that's not on the options</b> 
	 * <b> post: if the input is a line break, the game continues </b> 
	 * <b> post: if the input was the word 'simul' then the 'simulation' mode is entered and the program runs automatically </b>
	 * <b> post: if the input was the word 'menu' then it returns to the menu </b>  
	 * <b> post:if the input is different than expected, the program will do nothing and will ask the user to enter a valid option again </b> 
	 * <b> post: if the input was the word 'num' it shows the initial table with the id of the nodes and the position of the stairs and snakes </b> 
	 * <b> post: if someone won the current game, the program will ask you to enter your nickname and will calculate your score to add you to the winners list </b>  
	 */
	public void initializeGame() {
		if (game.getContPlaying() == false) {
			System.out.println("Press enter to continue");
			String jump = sc.nextLine();
			if (jump.equals("")) {
				System.out.println(game.generateDice());
				System.out.println(game);
				initializeGame();
			} else if (jump.equalsIgnoreCase("simul")) {
				System.out.println("Simulation mode has started");
				gameSimulation();
			} else if (jump.equalsIgnoreCase("menu")) {
				System.out.println("Back to the main menu"+"\n");
				return;
			} else if (jump.equalsIgnoreCase("num")) {
				game.setVisibility(true);
				System.out.println(game);
				game.setVisibility(false);
				initializeGame();
			} else {
				System.out.println("You must enter a valid option");
				initializeGame();
			}
		} else {
			System.out.println("The player "+game.getTemp().getSymbol()+" has won the game");
			calculateWinner();
		}
	}

	/** 
	 * Save the winner in the winners list with a nickname and their respective score <br>
	 * <b> pre: the player must have won a game </b> 
	 * <b> post: the winner is saved with his nickname and score in the winners list </b> 
	 */
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

	/** 
	 * Run the entire game automatically without waiting for a player line break <br>
	 * <b> pre: the players must have been created previously </b> 
	 * <b> pre: the game board must have been created previously </b> 
	 * <b> post: the winner is saved with his nickname and score in the winners list </b> 
	 */
	public void gameSimulation() {
		if (game.getContPlaying()==false) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(game.generateDice());
			System.out.println(game);
			gameSimulation();
		} else {
			System.out.println("The player "+game.getTemp().getSymbol()+" has won the game");
			calculateWinner();
		}
	}

	/** 
	 * Main menu of the application<br>
	 * <b> pre: The user can't choose an option that's not on the menu</b> 
	 * <b> post: The user choose an option</b> 
	 */	
	public void showMenu() {
		System.out.println("Welcome to Snakes And Ladders");
		System.out.println("Choose an option");
		System.out.println("(1) Play Snakes and Ladders");
		System.out.println("(2) Watch the ScoreBoard");
		System.out.println("(3) Exit");
	}

	/** 
	 * User enters game parameters <br>
	 * <b> pre: the number of rows and columns has to be greater than 2 </b> 
	 * <b> pre: the sum of the number of snakes and ladders must not be greater than half the rows on the game board </b> 
	 * <b> post: Tthe game board is created according to the parameters entered by the user </b> 
	 */	
	public void createGame() {
		System.out.println("Write the game's parameters as follow: ");
		System.out.println("On the same line separated with spaces the amount of rows, columns, snakes, ladders and finally the player's amount or the player's symbols (The symbols must to be together)(Minimum 1 and Maximum 9 Players)");
		System.out.println("The allowed symbols are: * ! O X % $ # + &");
		String parametros = sc.nextLine();
		createWorld(parametros);
	}

	/** 
	 * The game board is created and the game is initialized<br>
	 * <b> pre: parameters have to be separated by spaces </b> 
	 * <b> pre: you must have an entry for each parameter, none can be left empty </b> 
	 * <b> post: the game table is created and the game is initialized </b> 
	 */	
	public void createWorld(String parametros){
		String[] parts = parametros.split(SPACE);
		game.setOne(null);
		game.createGameBoard(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		int snakes = Integer.parseInt(parts[2]);
		int ladders = Integer.parseInt(parts[3]);
		game.setSnakes(snakes, 0);
		game.setLadders(ladders, 0);
		try {
			int a = Integer.parseInt(parts[4]);
			game.setPlayersAmount(a);
			game.generatePlayers(0,a);
		}catch(NumberFormatException ex){
			game.setPlayersAmount(parts[4].length());
			game.assignPlayers(0,parts[4]);
		}
		System.out.println(game);
		game.setVisibility(false);
		System.out.println(game);
		initializeGame();
	}

	/** 
	 *  Call the method that run the option chose by the user<br>
	 * <b> pre: The choice has to be an integer</b> 
	 */	
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

	/** 
	 *  Read and save an option that the user chose<br>
	 * <b> pre: The option has to be representated by an integer</b> 
	 * <b> post: Return the integer choice</b> 
	 */
	public int readOption() {
		int option = sc.nextInt();
		sc.nextLine();
		return option;
	}

	/** 
	 *  Run the whole application<br>
	 * <b> pre: The option has to be an integer</b> 
	 * <b> post: Run the program</b> 
	 */
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

import java.util.Scanner;
//Main Class for App
public class RentalApp{
	//main method
	public static void main(String [] args){
		boolean running = true;
		Scanner input = new Scanner(System.in);
		Menu menu = new Menu();
		//runs while running is true
		while(running){
			//calls function for Menu UI
			menu.runMenu();
			System.out.print("Would you like to exit ([Y]es/[N]o): ");
			String exit = input.nextLine();
			//will terminate when user wants to exit
			if(exit.charAt(0)=='Y' || exit.charAt(0)=='y'){
				running = false;
			}
		}
	}
}

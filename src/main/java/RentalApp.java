import java.util.Scanner;

public class RentalApp{
	public static void main(String [] args){
		boolean running = true;
		Scanner input = new Scanner(System.in);
		Menu menu = new Menu();
		
		while(running){
			menu.runMenu();
			System.out.print("Would you like to exit ([Y]es/[N]o): ");
			String exit = input.nextLine();
			if(exit.charAt(0)=='Y' || exit.charAt(0)=='y'){
				running = false;
			}
		}
	}
}
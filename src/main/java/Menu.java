import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
public class Menu{
	private ToolList tools;
	private ChargeList charges;
	private String toolCode;
	private String date;
	int days;
	int discount;
	
	public Menu(){
		tools = new ToolList();
		charges = new ChargeList();
		tools.buildList();
		charges.buildList();
		toolCode = "";
		date = "";
		days = 0;
		discount = 0;
		
	}
	public void runMenu(){
		Scanner input = new Scanner(System.in);
		boolean dateFormat = false;
		boolean choiceFormat = false;
		boolean daysFormat = false;
		boolean discountFormat = false;
		int chc = 0;
		while(!choiceFormat){
		displayTools();
		System.out.print("Please select one of the tools above with the appropriate number (1-"+tools.size()+"): ");
		try{
		chc = input.nextInt();
		choiceFormat = true;
		}
		catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // Clear the invalid input
            }
		}
		toolCode = getCode(chc);
		input.nextLine();
		while(!dateFormat){
		System.out.print("Please enter date in mm/dd/yy format: ");
		date = input.nextLine();
		dateFormat=isValidDate(date);
		}
		while(!daysFormat){
		System.out.print("How many days do you want to rent this tool: ");
		try{
		days = input.nextInt();
		daysFormat = true;
		}
		catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // Clear the invalid input
            }
		}
		while (!discountFormat){
		System.out.print("Clerk\n  please enter a discount (0-100): ");
		try{
		discount = input.nextInt();
		discountFormat = true;
		}
		catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // Clear the invalid input
            }
		}
		input.nextLine();
		RentalAgreement agreement = new RentalAgreement(toolCode,date,days,discount);
		agreement.print();
		if(agreement.isAccepted()){
	    System.out.println("Do you approve the agreement above ((Y)es/ (N)o): ");
		String accept = "";
		accept = input.nextLine();
		if(accept.charAt(0)=='Y'||accept.charAt(0)=='y'){
			System.out.println("Thank you for your business.");
		}
		else{
			System.out.println("If you are unsatisfied with your selection please try again.");
		}
		}
		
	}
	public void displayTools() {
    // Define column widths
    int indexWidth = 5;
    int toolWidth = 15;
    int brandWidth = 15;
    int codeWidth = 10;
    int dailyChargeWidth = 15;
    int weekdayChargeWidth = 18;
    int weekendChargeWidth = 18;
    int holidayChargeWidth = 18;

    // Print the table header with fixed-width columns
    System.out.printf("%-" + indexWidth + "s%-" + toolWidth + "s%-" + brandWidth + "s%-" + codeWidth + "s%-" + dailyChargeWidth + "s%-" + weekdayChargeWidth + "s%-" + weekendChargeWidth + "s%-" + holidayChargeWidth + "s%n",
            "", "Tool", "Brand", "Code", "Daily Charge", "Weekday Charge?", "Weekend Charge?", "Holiday Charge?");

    // Print the table rows
    for (int i = 0; i < tools.size(); i++) {
        Tool tl = tools.getItem(i);
        Charge chrg = charges.get(tl.getType());
        String daily = formatAsCurrency(chrg.getDaily());
        String wkdy = (chrg.getWeekday()) ? "Yes" : "No";
        String wknd = (chrg.getWeekend()) ? "Yes" : "No";
        String holi = (chrg.getHoliday()) ? "Yes" : "No";
        System.out.printf("%-" + indexWidth + "s%-" + toolWidth + "s%-" + brandWidth + "s%-" + codeWidth + "s%-" + dailyChargeWidth + "s%-" + weekdayChargeWidth + "s%-" + weekendChargeWidth + "s%-" + holidayChargeWidth + "s%n",(i + 1) + ".", tl.getType(), tl.getBrand(), tl.getCode(), daily, wkdy, wknd, holi);
    }
}
	public String getCode(int index){
		Tool tl = tools.getItem(index-1);
		return tl.getCode();
	}
	private static boolean isValidDate(String date) {
        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        sdf.setLenient(false); // Set lenient to false to strictly parse the date

        try {
            // Parse the date
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            // If parsing fails, the date format is incorrect
            return false;
        }
	}
	private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	private static String formatAsCurrency(float number) {
        return String.format("$%.2f", number);
    }
}
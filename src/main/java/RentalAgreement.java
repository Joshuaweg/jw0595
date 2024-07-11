import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//Generates the Agreement between the customer and clerk
//takes in parameters item code, date, days to rent, and discount for constructor
public class RentalAgreement{

	String code;
	String type;
	String brand;
	int days;
	LocalDate checkoutDate;
	LocalDate dueDate;
	float dailyCharge;
	int chargeDays;
	int discount;
	float preDiscount;
	float discountAmount;
	float finalCharge;
	boolean accepted;
	
	

	public RentalAgreement(){
		code="";
		type="";
		brand="";
		days=0;
		chargeDays=0;
		checkoutDate=null;
		dueDate=null;
		dailyCharge=0;
		discount=0;
		preDiscount=0;
		discountAmount=0;
		finalCharge = 0;
		accepted = false;
		
	}
	//Constructor that matches input parameters from test
	public RentalAgreement(String code,String date, int days, int discount){
		ToolList tools = new ToolList();
		tools.buildList();
		ChargeList charges = new ChargeList();
		charges.buildList();
		this.code=code;
		this.days=days;
		this.discount=discount;
		checkoutDate = processDate(date);
		dueDate = checkoutDate.plusDays(days);
		//accepted will track whether any entry is invalid based on known criteria
		//days must be greater than 0
		//discount must be between 0-100
		accepted =!(discount<0||discount>100||days<1);
		chargeDays = calculateChargeDays(tools, charges);
		dailyCharge = getDailyCharge(tools, charges);
		preDiscount = chargeDays*dailyCharge;
		discountAmount = calculateDiscount();
		finalCharge =preDiscount-discountAmount;	
	}
	//Converts date string to LocalDate object
	 public LocalDate processDate(String date){
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		 LocalDate dt = LocalDate.parse(date,formatter);
		 return dt;
	 }
	//Calculates the actual charge days based on tools and charges objects
	 private int calculateChargeDays(ToolList tools, ChargeList charges){
		 int daysDeducted = 0;
		 Tool current = tools.get(code);
		 String name = current.getType();
		 Charge chrg = charges.get(name);
		 boolean weekday = chrg.getWeekday();
		 boolean weekend = chrg.getWeekend();
		 boolean holiday = chrg.getHoliday();
		 //holidays deducted if holidays not charged
		 if(!holiday){
			 daysDeducted+=deductHoliday();
		 }
		 //weekends deducted when weekends not charged
		 if(!weekend){
			 daysDeducted+=deductWeekend();
		 }
		 return days-daysDeducted;
	 }
	//Deducts days based on whether day is holiday
	 private int deductHoliday(){
		int day = 0;
		boolean isJuly = checkoutDate.getMonth()==Month.JULY||dueDate.getMonth()==Month.JULY;
		boolean isSeptember = checkoutDate.getMonth()==Month.SEPTEMBER||dueDate.getMonth()==Month.SEPTEMBER;
		if(isJuly){
			for(LocalDate dt = checkoutDate; !dt.isAfter(dueDate); dt=dt.plusDays(1)){
				day+=(dt.getDayOfMonth()==4)?1:0;
			}
		}
			
		if(isSeptember){
			
			for(LocalDate dt = checkoutDate; !dt.isAfter(dueDate); dt=dt.plusDays(1)){
				day+=(dt.getDayOfWeek()== DayOfWeek.MONDAY && dt.getDayOfMonth()<=7)?1:0;
			}
		}
		
		return day;
	 }
	 //deducts days when day is weekend
	 private int deductWeekend(){
		int day = 0;
		for(LocalDate dt = checkoutDate; !dt.isAfter(dueDate); dt=dt.plusDays(1)){
				day+=(dt.getDayOfWeek()==DayOfWeek.SATURDAY || dt.getDayOfWeek()==DayOfWeek.SUNDAY)?1:0;
			}
		return day;
	 }
	 public boolean isAccepted(){
		 return accepted;
	 }
	 public float getPreDiscount(){
		 return preDiscount;
	 }
	 public float getFinalCharge(){
		 return finalCharge;
	 }
	//Prints out agreement or prints out message that agreement parameters were invalid
	 public void print(){
		 if(accepted){
		 NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		 NumberFormat percentFormatter = NumberFormat.getPercentInstance();
		 percentFormatter.setMinimumFractionDigits(0);
        percentFormatter.setMaximumFractionDigits(0);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		 System.out.println("Tool code: "+code);
		 System.out.println("Tool type: "+type);
		 System.out.println("Tool brand: "+brand);
		 System.out.println("Rental days: "+days);
		 System.out.println("Check out date: "+checkoutDate.format(formatter));
		 System.out.println("Due date: "+dueDate.format(formatter));
		 System.out.println("Daily rental charge: "+ currencyFormatter.format(dailyCharge));
		 System.out.println("Charge days: "+chargeDays);
		 System.out.println("Pre-discount charge: "+currencyFormatter.format(preDiscount));
		 System.out.println("Discount percent: "+percentFormatter.format((float)discount/100));
		 System.out.println("Discount amount: "+currencyFormatter.format(discountAmount));
		 System.out.println("Final charge: "+currencyFormatter.format(finalCharge));
		 }
		 else{
			 //message if days is less than 1
			 if(days<1){
				System.out.println("Days to rent is less than 1. Please try again"); 
			 }
		        //message if discount is not in range 0-100
			 else{
				 System.out.println("Discount percentage is not within range of 0-100. Please try again");
			 }
		 }
		 
	 }
	 public int getChargeDays(){
		 return chargeDays;
	 }
	 private float getDailyCharge(ToolList tools, ChargeList charges){
		Tool current = tools.get(code);
		Charge chrg = charges.get(current.getType());
		return chrg.getDaily();	
	 }
	 private float calculateDiscount(){
		 float percent = (float)discount/100;
		 return preDiscount*percent;
	 }
}

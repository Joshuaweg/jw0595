import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Builds to records from charges.tsv
public class ChargeList{
	final static String CHARGESPATH = "src/main/charges.tsv";
	private Map<String, Charge> chargeList;
	private List<String> toolNames;
	public ChargeList (){
		chargeList = new HashMap<>();
		toolNames = new ArrayList<>();
	}
	//Driving Method that builds all charge entries
	public void buildList(){

		try(Scanner scanner = new Scanner(new File(CHARGESPATH))){
			if(scanner.hasNextLine()){
				scanner.nextLine();
			}
		
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] cols = line.split("\t");

			if(cols.length == 5){
			String tool = cols[0];
			float daily = Float.parseFloat(cols[1]);
			boolean weekday = cols[2].equals("Yes");
			boolean weekend = cols[3].equals("Yes");
			boolean holiday = cols[4].equals("Yes");
			Charge charge = new Charge(tool,daily,weekday,weekend,holiday);
			chargeList.put(tool,charge);
			toolNames.add(tool);
			}
		}
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }


	}
	//method that returns specific charge
	public Charge get(String type){
		return chargeList.get(type);
	}


	
}
